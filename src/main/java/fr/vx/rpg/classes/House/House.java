package fr.vx.rpg.classes.House;

import fr.vx.rpg.RPG;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class House implements Listener
{
    private String name;
    private Location DoorLocation;
    private Location HomeLocation;

    public House(String name, Location DoorLocation, Location HomeLocation)
    {
        this.name = name;
        this.DoorLocation = DoorLocation;
        this.HomeLocation = HomeLocation;
        Bukkit.getPluginManager().registerEvents(this, RPG.getPlugin(RPG.class));
    }

    public String getName() {return name;}
    public Location getDoorLocation() {return DoorLocation;}
    public Location getHomeLocation() {return HomeLocation;}

    @EventHandler
    public void getDoor(PlayerInteractEvent e)
    {
        //suicide.
    }
}