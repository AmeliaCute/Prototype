package fr.vx.rpg.classes.mobs.impl.Spiders;

import fr.vx.rpg.classes.mobs.Mob;
import fr.vx.rpg.classes.mobs.Tiers;
import net.minecraft.server.v1_16_R3.EntityTypes;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class Spiders extends Mob
{

    public Spiders(Location location)
    {
        super(EntityTypes.SPIDER, location);
        this.setLocation(location.getX(), location.getY(),location.getZ(),-90,0);
        LivingEntity entity = (LivingEntity) this.getBukkitEntity();
        this.setData(entity, "Araignée", 20, Tiers.One);
        this.initBaseIa();
    }
}
