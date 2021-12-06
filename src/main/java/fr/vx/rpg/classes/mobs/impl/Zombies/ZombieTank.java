package fr.vx.rpg.classes.mobs.impl.Zombies;

import fr.vx.rpg.classes.Item.impl.Items;
import fr.vx.rpg.classes.mobs.Mob;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.LivingEntity;

public class ZombieTank extends EntityZombie // <- Classe a tester
{

    public ZombieTank(Location location)
    {
        super(EntityTypes.HUSK, ((CraftWorld) location.getWorld()).getHandle());
        this.setLocation(location.getX(),location.getY(),location.getZ(),-90,0);
        LivingEntity entity = (LivingEntity) this.getBukkitEntity();
        Mob.setData(entity, "Zombie Renforcé", 100, Items.ZOMBIE_TANK);
    }

    @Override
    public void initPathfinder()
    {
       this.goalSelector.a(0, new PathfinderGoalFloat(this));

       this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, 1.0D, true));
       this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 0.2D));
       this.goalSelector.a(6, new PathfinderGoalMoveThroughVillage(this, 0.2D, false, 1, null));
       this.goalSelector.a(7, new PathfinderGoalRandomStroll(this, 0.2D));
       this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
       this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));

       this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this));
       this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<EntityHuman>(this, EntityHuman.class, true));
    }
}
