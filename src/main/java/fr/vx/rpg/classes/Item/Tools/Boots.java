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

public class Boots extends Item
{
    private Material material;
    private String name;
    private Rarity rarity;
    private float protection;
    private float heart = 0;
    private float speed;

    public Boots(Material material, String name, Rarity rarity, float protection, float speed) {
        super(material, name, rarity, protection);
        this.material=material;
        this.name=name;
        this.rarity=rarity;
        this.protection=protection;
        this.speed=speed;
    }

    public Boots(Material material, String name, Rarity rarity, float protection, float heart, float speed) {
        super(material, name, rarity, protection);
        this.material=material;
        this.name=name;
        this.rarity=rarity;
        this.protection=protection;
        this.heart=heart;
        this.speed=speed;
    }

    @Override
    public ItemStack getItemStack()
    {
        ItemStack itemstack = new ItemStack(this.material);
        ItemMeta itemMeta = itemstack.getItemMeta();
        itemMeta.setDisplayName(rarity.getColor()+name);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", protection, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));
        itemMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.movementSpeed", speed, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));
        if(heart > 0){itemMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.maxHealth", heart, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));}
        itemMeta.setLore(Arrays.asList("",rarity.getDescription()));
        itemstack.setItemMeta(itemMeta);
        return itemstack;
    }
}
