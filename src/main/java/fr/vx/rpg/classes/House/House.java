package fr.vx.rpg.classes.House;

import fr.vx.rpg.RPG;
import fr.vx.rpg.classes.Gui.GuiHouse;
import fr.vx.rpg.classes.Player.Coins;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class House implements Listener
{
    private int id;
    private String name;
    private Location DoorLocation;
    private Location HomeLocation;
    private Location PlayerSpawn;
    private Type Housetype;
    private int price;

    public House(int id, String name, Location DoorLocation, Location HomeLocation,Location PlayerSpawn, Type type, int price)
    {
        this.id = id;
        this.name = name;
        this.DoorLocation = DoorLocation;
        this.HomeLocation = HomeLocation;
        this.PlayerSpawn = PlayerSpawn;
        this.Housetype = type;
        this.price = price;
        HouseDataBase.add(id, name);
        Bukkit.getPluginManager().registerEvents(this, RPG.getPlugin(RPG.class));
    }

    public String getName() {return name;}
    public Location getDoorLocation() {return DoorLocation;}
    public Location getHomeLocation() {return HomeLocation;}
    public Location getPlayerSpawn() {return PlayerSpawn;}

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
                if (Housetype == Type.PlayerHouse)
                {
                    if (HouseDataBase.isOwned(this.id))
                    {
                        if (!HouseDataBase.getOwner(id).equals(player.getUniqueId().toString())) {player.sendMessage(player.getName() + ": Ce n'est pas ma maison..");return;}
                        player.teleport(PlayerSpawn);
                        player.playSound(player.getLocation(), Sound.BLOCK_WOODEN_TRAPDOOR_CLOSE, 3.0F, 0.5F);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 35, 5));
                        return;
                    }
                    else if(!HouseDataBase.isOwned(this.id))
                    {
                        GuiHouse.getInventory(player,this.id,this.price);
                        return;
                    }
                }
            }
            if(location.equals(this.HomeLocation))
            {
                e.setCancelled(true);
                Location a = new Location(DoorLocation.getWorld(), DoorLocation.getX(), DoorLocation.getY() -1, DoorLocation.getZ());
                player.teleport(a);
                player.playSound(player.getLocation(), Sound.BLOCK_WOODEN_TRAPDOOR_CLOSE, 3.0F, 0.5F);
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 35, 5));
                return;
            }
        }
        else return;
    }

    @EventHandler
    public void getClickInInventory(InventoryClickEvent click)
    {
        Player player = (Player) click.getWhoClicked();

        if(click.getView().getTitle().toString().equals(this.id))
        {
            if(click.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN+"Acheter cette maison ?"))
            {
                click.setCancelled(true);
                if(HouseDataBase.isOwned(this.id))
                {
                    player.closeInventory();
                    player.sendMessage(ChatColor.RED+"Cette maison appartient deja a "+HouseDataBase.getOwner(this.id));
                    return;
                }
                if(Coins.getBalance(player) < this.price)
                {
                    player.closeInventory();
                    player.sendMessage(ChatColor.RED+"⪧ Pas assez d'argent.");
                    return;
                }
                Coins.remove(player, this.price);
                player.closeInventory();
                HouseDataBase.setOwned(Integer.parseInt(click.getView().getTitle()));
                HouseDataBase.setOwner(Integer.parseInt(click.getView().getTitle()), player);
                player.sendMessage(ChatColor.GREEN+"⪧ Vous avez acheté une maison !");
                player.sendMessage(ChatColor.DARK_GRAY+"⪧ La maison \""+this.name+"\" vous appartient.");
            }
        }
    }
}