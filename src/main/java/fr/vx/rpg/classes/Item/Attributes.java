package fr.vx.rpg.classes.Item;

import org.bukkit.attribute.Attribute;

public class Attributes
{

    private final Type type;
    private final double numb;

    /**
     *
     * @param type set a Attributes of Attributes.java.Type
     * @param numb set the level
     *
     */
    public Attributes(Type type, double numb)
    {
        this.numb = numb;
        this.type = type;
    }

    public Attribute getAttribute() { return type.getA(); }
    public double getNumber() { return numb; }

    public enum Type
    {
        ARMOR(Attribute.GENERIC_ARMOR),
        ARMOR_DURA(Attribute.GENERIC_ARMOR_TOUGHNESS),
        ATT_DAMA(Attribute.GENERIC_ATTACK_DAMAGE),
        ATT_KNOCKB(Attribute.GENERIC_ATTACK_KNOCKBACK),
        ATT_SPEED(Attribute.GENERIC_ATTACK_SPEED),
        ENT_KNO_RES(Attribute.GENERIC_KNOCKBACK_RESISTANCE),
        ENT_LUCK(Attribute.GENERIC_LUCK),
        ENT_MAX_HEALTH(Attribute.GENERIC_MAX_HEALTH),
        ENT_SPEED(Attribute.GENERIC_MOVEMENT_SPEED),
        ;
        private Attribute a;
        org.bukkit.attribute.Attribute attr;
        private Type(Attribute a)
        { this.a = a; }

        public Attribute getA()
        {
            return a;
        }
    }
}
