package fr.vx.rpg.classes.mobs;

import fr.vx.rpg.classes.mobs.impl.MobCollection;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import net.minecraft.server.v1_16_R3.ChatComponentText;
import net.minecraft.server.v1_16_R3.EntityCreature;
import net.minecraft.server.v1_16_R3.EntityTypes;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

public class Mob extends EntityCreature {

	public Mob(EntityTypes<? extends EntityCreature> entitytypes, Location location, String name, float hearth) {
		super(entitytypes, ((CraftWorld) location.getWorld()).getHandle());
		this.setLocation(location.getX(), location.getY(), location.getZ(), -90, 0);
		this.setCustomName(new ChatComponentText(name));
		LivingEntity entity = (LivingEntity) this.getBukkitEntity();
		setHearth(entity, hearth);

		this.setCustomNameVisible(true);
	}

	public Mob(EntityTypes<? extends EntityCreature> entitytypes, Location location, String name, float hearth, ItemStack tools)
	{
		super(entitytypes, ((CraftWorld) location.getWorld()).getHandle());
		this.setLocation(location.getX(), location.getY(), location.getZ(), -90, 0);
		this.setCustomName(new ChatComponentText(name));
		this.setCustomNameVisible(true);
		LivingEntity entity = (LivingEntity) this.getBukkitEntity();
		EntityEquipment equip = entity.getEquipment();
		equip.setItemInMainHand(tools);
		setHearth(entity, hearth);

	}


	public Mob(MobCollection.MobData data, Location location) {
		super(data.getEntityType(), ((CraftWorld) location.getWorld()).getHandle());
		this.setLocation(location.getX(), location.getY(), location.getZ(), -90, 0);
		this.setAggressive(data.getIsAgressive());
		this.navigation.o();
		this.setCustomName(new ChatComponentText(data.getName()));
		this.setCustomNameVisible(true);
		LivingEntity entity = (LivingEntity) this.getBukkitEntity();
		setHearth(entity, data.getHealthPoints());
		entity.setSilent(false);
	}

	private void setHearth(LivingEntity entity, float heart)
	{
		AttributeInstance healthAttribute = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH);
		healthAttribute.setBaseValue(heart*2);
		entity.setHealth(heart*2);
	}
}
