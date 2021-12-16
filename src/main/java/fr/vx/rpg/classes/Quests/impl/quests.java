package fr.vx.rpg.classes.Quests.impl;

import fr.vx.rpg.classes.Quests.PaladinQuest;
import org.bukkit.ChatColor;

public class quests
{
    public static PaladinQuest test;

    public static void register()
    {
        test = new PaladinQuest(1, "test", 100, ChatColor.WHITE+"Larbin");
    }
}
