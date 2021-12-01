package fr.vx.rpg.classes.mobs;

import net.minecraft.server.v1_16_R3.ChatComponentText;
import net.minecraft.server.v1_16_R3.EntityCreature;
import net.minecraft.server.v1_16_R3.EntityTypes;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class Mob extends EntityCreature implements Listener
{

    public Mob(EntityTypes entitytypes, Location location, String name, float hearth, MobEquipment mobEquipment, ItemStack drop) {
        super(entitytypes, ((CraftWorld) location.getWorld()).getHandle());
        this.setLocation(location.getX(), location.getY(), location.getZ(), -90, 0);
        this.setCustomName(new ChatComponentText(name));
        this.setCustomNameVisible(true);
    }


}