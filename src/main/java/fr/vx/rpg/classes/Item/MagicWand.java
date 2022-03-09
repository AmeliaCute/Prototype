package fr.vx.rpg.classes.Item;

import fr.vx.rpg.classes.Spell.Spell;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MagicWand extends ItemStack
{
    private final Material material;
    private final String name;
    private final Rarity rarity;
    private final List<String> description;
    private final double basePrice;
    private final int MaxMana;
    private final List<Spell> spell;

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
     */
    public MagicWand(Material material, String name, Rarity rarity, List<String> description, float basePrice,int MaxMana, List<Spell> spell)
    {
        this.material = material;
        this.name = name;
        this.rarity = rarity;
        this.description = description;
        this.basePrice =  basePrice;
        this.MaxMana = MaxMana;
        this.spell = spell;
        this.Mana = 0;
    }

    public ItemStack getItemstack()
    {
        ItemStack Wand = new ItemStack(material);
        ItemMeta WandMeta = Wand.getItemMeta();

        WandMeta.setDisplayName(rarity.getColor()+name);

        List<String> itemDesc = new ArrayList<String>();
        itemDesc.add(null);
        itemDesc.add(ChatColor.GOLD+"Mana: "+Mana+"/"+MaxMana);
        itemDesc.add(null);
        if(!(spell == null))
        {
            itemDesc.add(ChatColor.DARK_GRAY+"Sorts:");
            for(int i = 0; i < spell.size(); i++)
            {
                itemDesc.add(ChatColor.DARK_GRAY+"⪧ "+spell.get(i).getIcon().color()+spell.get(i).getIcon().icon()+" "+spell.get(i).getName());
            }
            itemDesc.add(null);
        }
        itemDesc.addAll(description);
        itemDesc.add(null);
        itemDesc.add(rarity.getColor()+rarity.getDescription());
        WandMeta.setLore(itemDesc);

        Wand.setItemMeta(WandMeta);

        return Wand;
    }

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
                description.set(2, ChatColor.GOLD+"Mana: "+manax+"/"+MaxMana);
            }
            else
            {
                description.set(2, ChatColor.GOLD+"Mana: 0/"+MaxMana);
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
                description.set(2, ChatColor.GOLD+"Mana: "+manax+"/"+MaxMana);
            }
            else
            {
                description.set(2, ChatColor.GOLD+"Mana: "+MaxMana+"/"+MaxMana);
            }
            wand.getItemMeta().setLore(description);
        }
    }

}
