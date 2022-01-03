package fr.vx.rpg.classes.Item.Tools;

import fr.vx.rpg.classes.Item.Attributes;
import fr.vx.rpg.classes.Item.Item;
import fr.vx.rpg.classes.Item.Rarity;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Helmet
{
    private Material material;
    private String name;
    private Rarity rarity;
    private List<Attributes> attributes;

    public Helmet(Material material, String name, Rarity rarity, List<Attributes> attributes)
    {
        this.attributes = attributes;
        this.material=material;
        this.name=name;
        this.rarity=rarity;
    }

    public ItemStack getItemStack()
    {
        ItemStack itemstack = new ItemStack(this.material);
        ItemMeta itemMeta = itemstack.getItemMeta();
        itemMeta.setDisplayName(rarity.getColor()+name);
        for (int i = 0; i < attributes.size(); i++) {
            itemMeta.addAttributeModifier(attributes.get(i).getAttribute(), new AttributeModifier(UUID.randomUUID(), "a", attributes.get(i).getNumber(), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
        }
        itemMeta.setLore(Arrays.asList("",rarity.getDescription()));
        itemstack.setItemMeta(itemMeta);
        return itemstack;
    }
}
