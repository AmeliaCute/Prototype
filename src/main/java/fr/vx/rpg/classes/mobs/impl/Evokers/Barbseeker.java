package fr.vx.rpg.classes.mobs.impl.Evokers;

import fr.vx.rpg.classes.Item.impl.Items;
import fr.vx.rpg.classes.mobs.Mob;
import fr.vx.rpg.classes.mobs.Tiers;
import net.minecraft.server.v1_16_R3.EntityTypes;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class Barbseeker extends Mob
{
    public Barbseeker(Location location)
    {
        super(EntityTypes.EVOKER, location);
        this.setLocation(location.getX(), location.getY(),location.getZ(),-90,0);
        LivingEntity entity = (LivingEntity) this.getBukkitEntity();
        this.setData(entity, "Barbare", 250, Items.BARBSEEKER, Tiers.Two);
        this.initBaseIa();
    }
}
