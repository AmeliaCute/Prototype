package fr.vx.rpg.classes.mobs.impl.Zombies;

import fr.vx.rpg.classes.Item.impl.Items;
import fr.vx.rpg.classes.mobs.Mob;
import net.minecraft.server.v1_16_R3.EntityTypes;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class Larbin extends Mob
{
    public Larbin(Location location)
    {
        super(EntityTypes.ZOMBIE, location);
        this.setLocation(location.getX(), location.getY(),location.getZ(),-90,0);
        LivingEntity entity = (LivingEntity) this.getBukkitEntity();
        this.setData(entity, "Larbin", 10, Items.LARBIN);
        this.initBaseIa();
    }
}