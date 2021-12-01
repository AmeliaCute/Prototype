package fr.vx.rpg.classes.mobs;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

public class MobEquipment
{
    private ItemStack helmet;
    private ItemStack chestplate;
    private ItemStack Leggings;
    private ItemStack Boots;
    private ItemStack tool;

    public MobEquipment(ItemStack helmet, ItemStack chestplate, ItemStack Leggings, ItemStack Boots, ItemStack tool)
    {
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.Leggings = Leggings;
        this.Boots = Boots;
        if(tool == null){ this.tool = new ItemStack(Material.AIR);}
        else {this.tool = tool;}
    }

    public ItemStack getHelmet(){return helmet;}
    public ItemStack getChestplate(){return chestplate;}
    public ItemStack getLeggings(){return Leggings;}
    public ItemStack getBoots(){return Boots;}
    public ItemStack getTool(){return tool;}

    public static void setHearth(LivingEntity entity, float heart)
    {
        AttributeInstance healthAttribute = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        healthAttribute.setBaseValue(heart*2);
        entity.setHealth(heart*2);
    }

    public static void setEquipment(LivingEntity entity, MobEquipment mobEquipment)
    {
        EntityEquipment equip = entity.getEquipment();
        equip.setHelmet(mobEquipment.getHelmet());
        equip.setChestplate(mobEquipment.getChestplate());
        equip.setLeggings(mobEquipment.getLeggings());
        equip.setBoots(mobEquipment.getBoots());
        equip.setItemInMainHand(mobEquipment.getTool());
    }
}
