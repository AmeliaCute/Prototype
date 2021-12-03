package fr.vx.rpg.classes.mobs.impl.Zombies;

import fr.vx.rpg.classes.Item.impl.Items;
import fr.vx.rpg.classes.mobs.Mob;
import net.minecraft.server.v1_16_R3.EntityTypes;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class ZombieTank extends Mob
{

    public ZombieTank(Location location)
    {
        super(EntityTypes.HUSK, location);
        this.setLocation(location.getX(),location.getY(),location.getZ(),-90,0);
        LivingEntity entity = (LivingEntity) this.getBukkitEntity();
        Mob.setData(entity, "Zombie Renforcé", 100, Items.ZOMBIE_TANK);
    }
}
