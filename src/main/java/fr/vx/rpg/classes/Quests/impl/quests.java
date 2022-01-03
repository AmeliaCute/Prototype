package fr.vx.rpg.classes.Quests.impl;

import fr.vx.rpg.classes.Quests.LumberjackQuest;
import fr.vx.rpg.classes.Quests.PaladinQuest;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class quests
{
    public static PaladinQuest firstKill;
    public static LumberjackQuest firstWood;

    public static void register()
    {
        firstKill = new PaladinQuest(2, "Un Larbin, un !", 100, ChatColor.WHITE+"Larbin");
        firstWood = new LumberjackQuest(3, 5, "Le debut d'un bucheron !", 500, Material.OAK_LOG);
    }
}
