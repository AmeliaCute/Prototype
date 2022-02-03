package fr.vx.rpg.classes.Item;

import com.sun.istack.internal.NotNull;
import fr.vx.rpg.RPG;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Food implements Listener
{

    private final String name;
    private final Material icon;
    private final Rarity rarity;
    private final double basePrice;

    private final double HeartRegenerated;
    private final double DamageTaked;
    private final double HeartGived;

    private boolean regen=false;
    private boolean damage=false;
    private boolean hgive=false;

    public Food(@NotNull String name, @NotNull Material icon, Rarity rarity,double basePrice,double HeartRegenerated, double DamageTaked, double HeartGived) {
        this.name = name;
        this.icon = icon;
        this.rarity = rarity;
        this.basePrice = basePrice;
        if (HeartRegenerated <= 0) {this.HeartRegenerated = 0;}
        else {
            this.HeartRegenerated = HeartRegenerated;
            this.regen = true;
        }
        if (DamageTaked <= 0) {this.DamageTaked = 0;}
        else {
            this.DamageTaked = DamageTaked;
            this.damage = true;
        }
        if (HeartGived <= 0) {this.HeartGived = 0;}
        else {
            this.HeartGived = HeartGived;
            this.hgive = true;
        }
        Bukkit.getPluginManager().registerEvents(this, RPG.getPlugin(RPG.class));
    }

    public ItemStack getItemStack()
    {
        ItemStack a = new ItemStack(icon);
        ItemMeta b = a.getItemMeta();

        List<String> description = new ArrayList<String>();
        if(this.regen){description.add(ChatColor.RED+"Recuperation de coeurs: "+ChatColor.GREEN+"+"+this.HeartRegenerated+" ❤");}
        if(this.damage){description.add(ChatColor.RED+"Coeurs perdu: -"+this.DamageTaked+" ❤");}
        if(this.hgive){description.add(ChatColor.RED+"Gain de coeurs: "+ChatColor.GREEN+"+"+this.HeartGived+" ❤");}
        description.add(null);
        description.add(rarity.getDescription());
        description.add(ChatColor.GOLD+""+basePrice+" Pieces.");

        b.setDisplayName(name);
        b.setLore(description);
        b.setUnbreakable(true);

        a.setItemMeta(b);
        return a;
    }

    @EventHandler
    public void onClickOnFood(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR))
        {
            if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(this.name));
            {
                player.getInventory().getItemInMainHand().setAmount(0);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_BURP, 1.5F, 3.0F);
                if(regen) {double hearts = (int) (player.getMaxHealth() - player.getHealth());if(hearts < this.HeartRegenerated){player.setHealth(player.getMaxHealth());}else{player.setHealth(player.getHealth()+this.HeartRegenerated);}}

                if(damage) {if(player.getHealth() <= this.DamageTaked){player.setHealth(0);}else player.setHealth(player.getHealth()-this.DamageTaked);}

                if(hgive) {player.setMaxHealth(player.getMaxHealth()+HeartGived);}
            }
            return;
        }else return;
    }
}