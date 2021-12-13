package fr.vx.rpg.classes.Jobs;

import java.util.HashMap;
import java.util.Map;

public enum JobEnum
{
    Novice(0, "§aNovice"),
    Neophyte(1, "§2Néophyte"),
    Initie(2, "§eInitié"),
    Maitre(3, "§6Maître"),
    Legende(4, "§aLégende"),
    Dieux(5, "§4Dieux"),
    ;

    private final int lvlind;
    private final String name;
    private static Map<Integer, JobEnum> ID_MAP = new HashMap<>();

    private JobEnum(int lvlid, String name) { this.lvlind = lvlid;this.name = name; }
    static { for(JobEnum id : values()) { ID_MAP.put(id.lvlind, id); } }
    public static int getFromID(int id) {
        JobEnum i = ID_MAP.get(id); return i.getLvlId(); }

    public int getLvlId(){return lvlind;}
    public String getName(){return name;}
}
