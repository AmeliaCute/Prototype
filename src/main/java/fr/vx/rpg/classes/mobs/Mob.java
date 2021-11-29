package fr.vx.rpg.classes.mobs;

import fr.vx.rpg.RPG;
import net.minecraft.server.v1_16_R3.ChatComponentText;
import net.minecraft.server.v1_16_R3.EntityCreature;
import net.minecraft.server.v1_16_R3.EntityTypes;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

public class Mob extends EntityCreature implements Listener
{
    private float hearth;
    private String name;
    private Location location;
    private MobEquipment mobEquipment;
    private ItemStack drop;

    public Mob(EntityTypes entitytypes, Location location, String name, float hearth, MobEquipment mobEquipment, ItemStack drop) {
        super(entitytypes, ((CraftWorld)location.getWorld()).getHandle());
        this.hearth = hearth;
        this.name = name;
        this.location = location;
        this.mobEquipment = mobEquipment;
        this.drop = drop;
        LivingEntity entity = (LivingEntity) this.getBukkitEntity();
        this.setCustomName(new ChatComponentText(name));
        this.setCustomNameVisible(true);
        setHearth(entity,hearth);
        setEquipment(entity, mobEquipment);
        Bukkit.getPluginManager().registerEvents(this, RPG.getPlugin(RPG.class));
    }

    private void setHearth(LivingEntity entity, float heart)
    {
        AttributeInstance healthAttribute = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        healthAttribute.setBaseValue(heart*2);
        entity.setHealth(heart*2);
    }

    private void setEquipment(LivingEntity entity, MobEquipment mobEquipment)
    {
        EntityEquipment equip = entity.getEquipment();
        equip.setHelmet(mobEquipment.getHelmet());
        equip.setChestplate(mobEquipment.getChestplate());
        equip.setLeggings(mobEquipment.getLeggings());
        equip.setBoots(mobEquipment.getBoots());
        equip.setItemInMainHand(mobEquipment.getTool());
    }

    @EventHandler
    public void OnMobKill(EntityDeathEvent deathEvent)
    {
        String entityName = deathEvent.getEntity().getCustomName();
        if(entityName == this.name)
        {
            deathEvent.getDrops().clear();
            if(this.drop == null){return;}
            deathEvent.getDrops().add(this.drop);
        }
    }
}