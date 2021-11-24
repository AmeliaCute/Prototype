package fr.vx.rpg.classes;

import fr.vx.rpg.RPG;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;

public class House
{
    private String name;
    private Location DoorLocation;
    private Location HomeLocation;

    public House(String name, Location DoorLocation, Location HomeLocation)
    {
        this.name = name;
        this.DoorLocation = DoorLocation;
        this.HomeLocation = HomeLocation;
    }

    public String getName() {return name;}
    public Location getDoorLocation() {return DoorLocation;}
    public Location getHomeLocation() {return HomeLocation;}

    public static class GetHouse extends House implements Listener {

        private Location getDoorLocation;
        private Location getHomeLocation;

        public GetHouse(String name, Location DoorLocation, Location HomeLocation)
        {
            super(name, DoorLocation, HomeLocation);
            this.getDoorLocation = DoorLocation;
            this.getHomeLocation = HomeLocation;
            Bukkit.getPluginManager().registerEvents(this, RPG.getPlugin(RPG.class));
        }
        
        @EventHandler
        public void getDoor(PlayerInteractEvent e)
        {
            Material material = e.getClickedBlock().getType();
            Location location = e.getClickedBlock().getLocation();
            Player player = e.getPlayer();
            
            if(material == Material.SPRUCE_DOOR && location == getDoorLocation)
            {
                e.setCancelled(true);
                player.teleport(getHomeLocation);
            }
        }
    }
}
