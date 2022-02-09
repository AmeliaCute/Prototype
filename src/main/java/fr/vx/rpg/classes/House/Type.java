package fr.vx.rpg.classes.House;

public enum Type
{
    Bar("Bar", false),
    Guild("Guild", false),
    PlayerHouse("Maison", true),
    Shop("Boutique", false),
    FactionHouse("Maison de Faction", true)
    ;

    private String NameType;
    private boolean Buyable;

    private Type(String NameType, boolean Buyable)
    {
        this.NameType = NameType;
        this.Buyable = Buyable;
    }

    public String getNameType() { return NameType;}
    public Boolean getBuyable() { return Buyable;}
}
