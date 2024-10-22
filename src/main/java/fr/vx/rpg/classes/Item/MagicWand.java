package fr.vx.rpg.classes.Item;

import fr.vx.rpg.RPG;
import fr.vx.rpg.classes.Spell.Spell;
import fr.vx.rpg.classes.Spell.SpellEvents;
import fr.vx.rpg.classes.Spell.impl.Spells;
import fr.vx.rpg.utils.Maths;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MagicWand extends ItemStack implements Listener
{
    private final Material material;
    private final int id;
    private final String name;
    private final Rarity rarity;
    private final List<String> description;
    private final double basePrice;
    private final int MaxMana;
    private final List<Spell> spell;
    private final String identifier;

    private int Mana;

    /**
     *
     * @param material set a Material of org.bukkit.Material
     * @param name give a name to your item
     * @param rarity set the rarity of the item
     * @param description set the description
     * @param basePrice set the price
     * @param MaxMana set the MaxMana when you got the Wand
     * @param spell set default spells
     * @param id set id of custom models
     */
    public MagicWand(Material material,int id, String name, Rarity rarity, List<String> description, float basePrice,int MaxMana, List<Spell> spell)
    {
        this.material = material;
        this.id = id;
        this.name = name;
        this.rarity = rarity;
        this.description = description;
        this.basePrice =  basePrice;
        this.MaxMana = MaxMana;
        this.spell = spell;
        this.Mana = 0;
        this.identifier = String.valueOf(Maths.toAsciiInteger(name).bitCount());
        Bukkit.getPluginManager().registerEvents(this, RPG.getPlugin(RPG.class));
        RPG.LOGGER.info("registering MagicWand "+name+" with indentifier "+Maths.toAsciiInteger(name).bitCount());
    }

    public static List<Spell> getItemSpells(ItemStack itemStack)
    {
        List<String> lore = itemStack.getItemMeta().getLore();
        List<Spell> x = new ArrayList<>();

        if(lore.get(3).equals(ChatColor.DARK_GRAY+"Sorts:"))
        {
            for(int i = 4; i < 9; i++)
            {
                String y = lore.get(i).replace(ChatColor.GRAY+"⪧ ", "").toString();
                if(!y.equals("vide"))
                {
                    x.add(Spells.get(y));
                }
            }
        }
        return x;
    }

    public String getIdentifier() {return this.identifier;}

    public int getMana()
    {
        return Mana;
    }

    public int getMaxMana()
    {
        return MaxMana;
    }

    public void removeItemMana(int mana, Player player)
    {
        if(player.getInventory().getItemInMainHand() instanceof MagicWand)
        {
            ItemStack wand = player.getInventory().getItemInMainHand();
            List<String> description = wand.getItemMeta().getLore();
            if(!(getMana() - mana < 0))
            {
                int manax = getMana() - mana;
                description.set(1, ChatColor.GOLD+"Mana: "+manax+"/"+MaxMana);
            }
            else
            {
                description.set(1, ChatColor.GOLD+"Mana: 0/"+MaxMana);
            }
            wand.getItemMeta().setLore(description);
        }
    }

    public void addItemMana(int mana, Player player)
    {
        if(player.getInventory().getItemInMainHand() instanceof MagicWand)
        {
            ItemStack wand = player.getInventory().getItemInMainHand();
            List<String> description = wand.getItemMeta().getLore();
            if(!(getMana() - mana > MaxMana))
            {
                int manax = getMana() + mana;
                description.set(1, ChatColor.GOLD+"Mana: "+manax+"/"+MaxMana);
            }
            else
            {
                description.set(1, ChatColor.GOLD+"Mana: "+MaxMana+"/"+MaxMana);
            }
            wand.getItemMeta().setLore(description);
        }
    }

    public ItemStack getItemstack()
    {
        ItemStack Wand = new ItemStack(material);
        ItemMeta WandMeta = Wand.getItemMeta();

        WandMeta.setDisplayName(rarity.getColor()+name);
        WandMeta.setCustomModelData(Maths.toAsciiInteger(name).bitCount());

        List<String> itemDesc = new ArrayList<String>();
        itemDesc.add(null);
        itemDesc.add(ChatColor.GOLD+"Mana: "+Mana+"/"+MaxMana);
        itemDesc.add(null);
        if(!(spell == null))
        {
            itemDesc.add(ChatColor.DARK_GRAY+"Sorts:");
            for(int i = 0; i < 5; i++)
            {
                if(i < spell.size())
                {
                    itemDesc.add(ChatColor.GRAY+"⪧ "+spell.get(i).getIcon().color()+spell.get(i).getIcon().icon()+" "+Spells.name(spell.get(i)));
                }
                else
                {
                    itemDesc.add(ChatColor.GRAY+"⪧ vide");
                }
            }
            itemDesc.add(null);
        }
        itemDesc.addAll(description);
        itemDesc.add(null);
        itemDesc.add(rarity.getColor()+rarity.getDescription());
        WandMeta.setLore(itemDesc);
        Wand.setItemMeta(WandMeta);

        net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(Wand);
        NBTTagCompound itemCompound = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
        itemCompound.setString("custom_id", this.identifier);
        nmsItem.setTag(itemCompound);
        Wand = CraftItemStack.asBukkitCopy(nmsItem);

        return Wand;
    }

    @EventHandler
    public void onWandClick(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
        {
           if(isInstance(this, player.getInventory().getItemInMainHand()))
           {
               SpellEvents.sendSpellByItem(getItemSpells(player.getInventory().getItemInMainHand()).get(0), player);
           }else return;
        }
        else return;
    }

    public boolean isInstance(MagicWand wand, ItemStack itemStack)
    {
        net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);
        if (nmsItem.hasTag()) {
            NBTTagCompound itemCompound = nmsItem.getTag();
            if (itemCompound.getString("custom_id").equalsIgnoreCase(wand.getIdentifier())) {
                return true;
            }
        }
        return false;
    }

}
