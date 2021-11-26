package fr.vx.rpg.classes.mobs;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;

import net.minecraft.server.v1_16_R3.ChatComponentText;
import net.minecraft.server.v1_16_R3.EntityCreature;
import net.minecraft.server.v1_16_R3.EntityTypes;

public class Mob extends EntityCreature {

	public Mob(EntityTypes<? extends EntityCreature> entitytypes, Location location, String name, float health) {
		super(entitytypes, ((CraftWorld) location.getWorld()).getHandle());
		this.setLocation(location.getX(), location.getY(), location.getZ(), -90, 0);
		this.heal(health);
		this.setCustomName(new ChatComponentText(name));
		this.setCustomNameVisible(true);
	}
	

}
