package fr.vx.rpg.classes.mobs;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;

import net.minecraft.server.v1_16_R3.ChatComponentText;
import net.minecraft.server.v1_16_R3.EntityCreature;
import net.minecraft.server.v1_16_R3.EntityTypes;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

public class Mob extends EntityCreature {

	public Mob(EntityTypes<? extends EntityCreature> entitytypes, Location location, String name, float health) {
		super(entitytypes, ((CraftWorld) location.getWorld()).getHandle());
		this.setLocation(location.getX(), location.getY(), location.getZ(), -90, 0);
		this.heal(health);
		this.setCustomName(new ChatComponentText(name));
		this.setCustomNameVisible(true);
	}

	public Mob(EntityTypes<? extends EntityCreature> entitytypes, Location location, String name, float health, ItemStack tools)
	{
		super(entitytypes, ((CraftWorld) location.getWorld()).getHandle());
		this.setLocation(location.getX(), location.getY(), location.getZ(), -90, 0);
		this.heal(health);
		this.setCustomName(new ChatComponentText(name));
		this.setCustomNameVisible(true);
		LivingEntity en = (LivingEntity) this.getBukkitEntity();
		EntityEquipment equip = en.getEquipment();
		equip.setItemInMainHand(tools);
	}

}
