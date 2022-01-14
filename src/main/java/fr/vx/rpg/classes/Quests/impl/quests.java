package fr.vx.rpg.classes.Quests.impl;

import fr.vx.rpg.classes.Quests.Paladin;
import org.bukkit.ChatColor;

public class quests
{
    public static Paladin test1;

    public static void register()
    {
        test1 = new Paladin("firstlarbinkilled","Le premier mais pas le dernier!", ChatColor.WHITE+"Larbin", 50);
    }
}
