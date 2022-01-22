package fr.vx.rpg.classes.House.History;

public enum HistoryBranch
{
    MAIN("Histoire Principale","main", 3),
    DISCOVERING_THE_WORLD("Decouverte du monde", "discoveringtheworld", 1),
    ;

    private final String a;
    private final String b;
    private final int c;
    HistoryBranch(String name, String sqlName, int Etapes)
    {
        this.a = name;
        this.b = sqlName;
        this.c = Etapes;
    }

    public String getName() {return a;}
    public String getSql() {return b;}
    public int getMaxSteps() {return c;}
}