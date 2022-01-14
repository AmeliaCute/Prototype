package fr.vx.rpg.classes.Npc.impl;

import fr.vx.rpg.classes.Npc.npc;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class citizens
{
    public static npc diana;

    public static void register()
    {
        diana = new npc("Diana", "https://i.namemc-static.com/49c184c0da4e68f0.png", new Location(Bukkit.getWorld("world"), 13.5, 75, -27.5));
    }
}
