package fr.vx.rpg.classes.mobs;

import net.minecraft.server.v1_16_R3.ChatComponentText;
import net.minecraft.server.v1_16_R3.EntityCreature;
import net.minecraft.server.v1_16_R3.EntityTypes;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EntityEquipment;

public class Mob extends EntityCreature implements Listener
{

    public Mob(EntityTypes entitytypes, Location location) {
        super(entitytypes, ((CraftWorld) location.getWorld()).getHandle());
        this.setLocation(location.getX(), location.getY(), location.getZ(), -90, 0);
    }

    public static void setData(LivingEntity entity, String name, float heart, MobEquipment mobEquipment)
    {
        entity.setCustomName(String.valueOf(new ChatComponentText(name)));
        entity.setCustomNameVisible(true);

        EntityEquipment equip = entity.getEquipment();
        equip.setHelmet(mobEquipment.getHelmet());
        equip.setChestplate(mobEquipment.getChestplate());
        equip.setLeggings(mobEquipment.getLeggings());
        equip.setBoots(mobEquipment.getBoots());
        equip.setItemInMainHand(mobEquipment.getTool());

        AttributeInstance healthAttribute = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        healthAttribute.setBaseValue(heart*2);
        entity.setHealth(heart*2);
    }


}