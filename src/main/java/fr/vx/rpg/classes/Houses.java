package fr.vx.rpg.classes;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Houses
{
    public static House.GetHouse MyHouse;

    public static void Register()
    {
        MyHouse = new House.GetHouse("MyHouse", new Location(Bukkit.getWorld("world"), -85, 12, -215), new Location(Bukkit.getWorld("world"),-90.5, 6, -232.5));
    }

}
