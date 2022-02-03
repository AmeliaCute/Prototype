package fr.vx.rpg.classes.Item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Tool extends Item
{
    private final Material material;
    private final String name;
    private final Rarity rarity;
    private final float basePrice;

    private List<Attributes> attributesList;

    private final List<String> description;

    private List<Enchantment> enchantmentList;
    private List<Integer> enchantmentLvl;

    private boolean hasAttributes;
    private boolean hasEnchantment;

    public Tool(Material material, String name, Rarity rarity,List<String> description, float basePrice)
    {
        super(material, name, rarity, basePrice);
        this.material = material;
        this.name = name;
        this.rarity = rarity;
        this.basePrice = basePrice;
        this.description = description;
    }

    public Tool(Material material, String name, List<Attributes> attributesList, Rarity rarity,List<String> description, float basePrice)
    {
        super(material, name, rarity, basePrice);
        this.material = material;
        this.name = name;
        this.rarity = rarity;
        this.basePrice = basePrice;
        this.attributesList = attributesList;
        this.hasAttributes = true;
        this.description = description;
    }

    public Tool(Material material, String name, List<Enchantment> EnchantmentList,List<Integer> Enchantmentlevel, Rarity rarity,List<String> description, float basePrice)
    {
        super(material, name, rarity, basePrice);
        this.material = material;
        this.name = name;
        this.rarity = rarity;
        this.basePrice = basePrice;
        this.enchantmentList = EnchantmentList;
        this.enchantmentLvl = Enchantmentlevel;
        this.hasEnchantment = true;
        this.description = description;
    }

    public Tool(Material material, String name, List<Enchantment> EnchantmentList,List<Integer> Enchantmentlevel,List<Attributes> attributesList, Rarity rarity,List<String> description ,float basePrice)
    {
        super(material, name, rarity, basePrice);
        this.material = material;
        this.name = name;
        this.rarity = rarity;
        this.basePrice = basePrice;
        this.enchantmentList = EnchantmentList;
        this.enchantmentLvl = Enchantmentlevel;
        this.attributesList = attributesList;
        this.hasEnchantment = true;
        this.hasAttributes = true;
        this.description = description;
    }


    @Override
    public ItemStack getItemStack()
    {
        ItemStack a = new ItemStack(material);
        ItemMeta b = a.getItemMeta();
        List<String> c = new ArrayList<String>();
        c.add(null);
        c.addAll(description);
        c.add(null);
        c.add(ChatColor.GOLD+""+basePrice+" pieces");
        c.add(rarity.getDescription());


        b.setDisplayName(rarity.getColor() + name);
        b.setLore(c);

        if(hasAttributes)
        {
            for(int i=0; i < attributesList.size(); i++)
            {
                b.addAttributeModifier(attributesList.get(i).getAttribute(), new AttributeModifier(UUID.randomUUID(), "a", attributesList.get(i).getNumber(), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
            }
        }
        if(hasEnchantment)
        {
            for (int i = 0; i < enchantmentList.size(); i++) {

                b.addEnchant(enchantmentList.get(i), enchantmentLvl.get(i), true);

            }
        }
        a.setItemMeta(b);
        return a;
    }
}
