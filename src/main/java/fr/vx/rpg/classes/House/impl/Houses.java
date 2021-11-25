package fr.vx.rpg.classes.House.impl;

import fr.vx.rpg.classes.House.House;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Houses
{
    public static House MyHouse;

    public static void Register()
    {
        MyHouse = new House("MyHouse", new Location(Bukkit.getWorld("world"), 0, 5, 0), new Location(Bukkit.getWorld("world"),-90.5, 6, -232.5));
    }

}
