package fr.vx.rpg.classes.House.impl;

import fr.vx.rpg.classes.House.House;
import fr.vx.rpg.classes.House.Type;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Houses
{
    public static House MyHouse;

    public static void Register()
    {
        MyHouse = new House(
                1,
                "MyHouse",
                new Location(Bukkit.getWorld("world"), 14, 74, -16),
                new Location(Bukkit.getWorld("world"), 10, 74, -16),
                new Location(Bukkit.getWorld("world"), 12, 74, -16),
                Type.PlayerHouse,
                150000
        );
    }

}