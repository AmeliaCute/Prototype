package fr.vx.rpg.classes.mobs;

import net.minecraft.server.v1_16_R3.*;
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

    public void setData(LivingEntity entity, String name, float heart, MobEquipment mobEquipment, Tiers tiers)
    {
        entity.setCustomName(tiers.getColor()+name);
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

    public void setData(LivingEntity entity, String name, float heart, Tiers tiers)
    {
        entity.setCustomName(tiers.getColor()+name);
        entity.setCustomNameVisible(true);

        AttributeInstance healthAttribute = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        healthAttribute.setBaseValue(heart*2);
        entity.setHealth(heart*2);
    }

    public void initBaseIa()
    {
        this.goalSelector.a(0, new PathfinderGoalFloat(this));

        this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, 1.0D, true));
        this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 1.0D));
        this.goalSelector.a(7, new PathfinderGoalRandomStroll(this, 1.0D));
        this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
        this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));

        this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this));
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<EntityHuman>(this, EntityHuman.class, true));
    }
}