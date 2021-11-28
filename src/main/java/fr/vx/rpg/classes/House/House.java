package fr.vx.rpg.classes.House;

import fr.vx.rpg.RPG;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class House implements Listener
{
    private String name;
    private Location DoorLocation;
    private Location HomeLocation;
    private Type HouseType;

    public House(String name, Location DoorLocation, Location HomeLocation, Type type)
    {
        this.name = name;
          this.DoorLocation = DoorLocation;
        this.HomeLocation = HomeLocation;
        this.HouseType = type;
        Bukkit.getPluginManager().registerEvents(this, RPG.getPlugin(RPG.class));
    }

    public String getName() {return name;}
    public Location getDoorLocation() {return DoorLocation;}
    public Location getHomeLocation() {return HomeLocation;}

    @EventHandler
    public void getDoor(PlayerInteractEvent e)
    {
        if(e.getClickedBlock() != null)
        {
            Location location = e.getClickedBlock().getLocation();
            Player player = e.getPlayer();
            if(location.equals(this.DoorLocation))
            {
                e.setCancelled(true);
                player.teleport(HomeLocation);
                player.playSound(player.getLocation(), Sound.BLOCK_WOODEN_TRAPDOOR_CLOSE, 3.0F, 0.5F);
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 35, 5));
            }
        }
        else return;
    }
}