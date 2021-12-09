package fr.vx.rpg.classes.House.impl;

import fr.vx.rpg.classes.House.House;
import fr.vx.rpg.classes.House.Type;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Houses
{
    public static House MyHouse;
    public static House BarInventeurs;
    public static House GuildeDesMages;

    public static void Register()
    {
        MyHouse = new House("MyHouse", new Location(Bukkit.getWorld("world"), -85, 12, -215), new Location(Bukkit.getWorld("world"),-6.5, 18, 15.5, 90, 0), Type.PlayerHouse);
        MyHouse = new House("MyHouse", new Location(Bukkit.getWorld("world"),-6, 19, 15), new Location(Bukkit.getWorld("world"), -85.5 , 11, -215.5, 151, 0), Type.PlayerHouse);

        BarInventeurs = new House("Bar Des inventeurs", new Location(Bukkit.getWorld("world"),  -121, 16, -214), new Location(Bukkit.getWorld("world"), -12.5, 18,37.5, 0,0), Type.Bar);
        BarInventeurs = new House("Bar Des inventeurs", new Location(Bukkit.getWorld("world"), -12, 19,37), new Location(Bukkit.getWorld("world"),  -120.5, 16, -213.5, -55, 0), Type.Bar);

        GuildeDesMages = new House("Guilde des mages", new Location(Bukkit.getWorld("world"),-101, 16, -197), new Location(Bukkit.getWorld("world"), -4.5, 18, -4.5, 90, 0) ,Type.Guild);
        GuildeDesMages = new House("Guilde des mages",new Location(Bukkit.getWorld("world"), -5, 19, -5) ,new Location(Bukkit.getWorld("world"),-100.5, 15, -196.5, 180, 0) ,Type.Guild);

    }

}