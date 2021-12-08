package fr.vx.rpg.classes.mobs.impl.Zombies;

import fr.vx.rpg.classes.Item.impl.Items;
import fr.vx.rpg.classes.mobs.Mob;
import net.minecraft.server.v1_16_R3.EntityTypes;
import org.bukkit.Location;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;

public class ZombieAssassin extends Mob
{
    public ZombieAssassin( Location location)
    {
        super(EntityTypes.DROWNED, location);
        this.setLocation(location.getX(),location.getY(),location.getZ(),-90,0);
        LivingEntity entity = (LivingEntity) this.getBukkitEntity();
        this.setData(entity, "Zombie Assassin", 50, Items.ZOMBIE_ASSASSIN);
        double moveSpeed = ((Attributable)entity).getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue();
        entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(moveSpeed+0.2);
        this.initBaseIa();
    }
}
