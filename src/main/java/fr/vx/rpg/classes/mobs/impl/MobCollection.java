package fr.vx.rpg.classes.mobs.impl;

import fr.vx.rpg.classes.mobs.Mob;
import net.minecraft.server.v1_16_R3.EntityTypes;
import org.bukkit.Location;

public class MobCollection extends Mob
{
    public MobCollection(MobData data, Location location) {
        super(data, location);
    }

    public enum MobData  {
        Larbin(EntityTypes.ZOMBIE, "Larbin", 10.0F, true),
        Zombie(EntityTypes.ZOMBIE, "Zombie", 25.0F, true),
        Chef(EntityTypes.ZOMBIE_VILLAGER, "Chef", 200.0F, true);

        private EntityTypes entity;
        private String name;
        private float hp;
        private boolean isAgressive;

        /**
         * @param entity Choisir le mob.
         * @param name Choisir le nom.
         * @param hp Choisir le nombre de coeurs.
         * @param isAgressive Choisir si le mob attaquera les joueurs ou non.
         */
        private MobData(EntityTypes entity, String name, float hp, boolean isAgressive)
        {
            this.entity = entity;
            this.name = name;
            this.hp = hp;
            this.isAgressive = isAgressive;
        }

        public EntityTypes getEntityType() { return entity; }
        public String getName() { return name; }
        public float getHealthPoints() { return hp; }
        public boolean getIsAgressive() {return isAgressive; }
    }
}
