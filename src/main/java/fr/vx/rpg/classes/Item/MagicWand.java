package fr.vx.rpg.classes.Item;

import com.sun.istack.internal.NotNull;
import fr.vx.rpg.classes.Spell.Spell;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MagicWand
{
    private final Material material;
    private final String name;
    private final Rarity rarity;
    private final List<String> description;
    private final double basePrice;
    private final int MaxMana;
    private final List<Spell> spell;


    public MagicWand(Material material, String name, Rarity rarity, List<String> description, float basePrice,int MaxMana, @NotNull List<Spell> spell)
    {
        this.material = material;
        this.name = name;
        this.rarity = rarity;
        this.description = description;
        this.basePrice =  basePrice;
        this.MaxMana = MaxMana;
        this.spell = spell;
    }

    public ItemStack getItemstack()
    {
        ItemStack Wand = new ItemStack(Material.STICK);
        ItemMeta WandMeta = Wand.getItemMeta();

        WandMeta.setDisplayName(rarity.getColor()+name);

        List<String> itemDesc = new ArrayList<String>();
        itemDesc.add(null);
        itemDesc.add(ChatColor.GOLD+"Mana: "+MaxMana+"/"+MaxMana);
        itemDesc.add(null);
        itemDesc.add(ChatColor.DARK_GRAY+"Sorts:");
        for(int i = 0; i < spell.size(); i++)
        {
            itemDesc.add(ChatColor.DARK_GRAY+"⪧ "+spell.get(i).getIcon().color()+spell.get(i).getIcon().icon()+" "+spell.get(i).getName());
        }
        itemDesc.add(null);
        itemDesc.addAll(description);
        itemDesc.add(null);
        itemDesc.add(rarity.getColor()+rarity.getDescription());

        Wand.setItemMeta(WandMeta);

        return Wand;
    }

    //TODO Faire les getX
}
