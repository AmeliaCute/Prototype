package fr.vx.rpg.classes.Item.Tools;

import fr.vx.rpg.classes.Item.Item;
import fr.vx.rpg.classes.Item.Rarity;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.UUID;

public class Leggings extends Item
{
    private Material material;
    private String name;
    private Rarity rarity;
    private float protection;
    private float heart = 0;

    public Leggings(Material material, String name, Rarity rarity, float protection) {
        super(material, name, rarity, protection);
        this.material=material;
        this.name=name;
        this.rarity=rarity;
        this.protection=protection;
    }
    public Leggings(Material material, String name, Rarity rarity, float protection, float heart) {
        super(material, name, rarity, protection);
        this.material=material;
        this.name=name;
        this.rarity=rarity;
        this.protection=protection;
        this.heart=heart;
    }

    @Override
    public ItemStack getItemStack()
    {
        ItemStack itemstack = new ItemStack(this.material);
        ItemMeta itemMeta = itemstack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "generic.armor", protection, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
        if(heart > 0){itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.maxHealth", heart, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));}
        itemMeta.setLore(Arrays.asList("",rarity.getDescription()));
        itemstack.setItemMeta(itemMeta);
        return itemstack;
    }
}
