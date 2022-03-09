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
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.List;

public class Food implements Listener
{

    private final String name;
    private final Material icon;
    private final Rarity rarity;
    private final double basePrice;
    private final double foodRegeneration;
    private final double saturation;

    private final double HeartRegenerated;
    private final double DamageTaked;
    private final double HeartGived;

    private List<PotionEffect> PotionEffect;

    private boolean regen=false;
    private boolean damage=false;
    private boolean hgive=false;
    private boolean hasEffects=false;
    private boolean hasAttribut=false;

    public Food(@NotNull String name, @NotNull Material icon, @NotNull Rarity rarity,@NotNull int FoodRegeneration,int saturation,@NotNull double basePrice,@NotNull double HeartRegenerated,@NotNull double DamageTaked,@NotNull double HeartGived) {
        this.name = name;
        this.icon = icon;
        this.rarity = rarity;
        this.basePrice = basePrice;
        this.foodRegeneration = FoodRegeneration;
        this.saturation = saturation;
        if (HeartRegenerated <= 0) {this.HeartRegenerated = 0;}
        else {
            this.HeartRegenerated = HeartRegenerated;
            this.regen = true;
            this.hasAttribut = true;
        }
        if (DamageTaked <= 0) {this.DamageTaked = 0;}
        else {
            this.DamageTaked = DamageTaked;
            this.damage = true;
            this.hasAttribut = true;
        }
        if (HeartGived <= 0) {this.HeartGived = 0;}
        else {
            this.HeartGived = HeartGived;
            this.hgive = true;
            this.hasAttribut = true;
        }
        Bukkit.getPluginManager().registerEvents(this, RPG.getPlugin(RPG.class));
    }

    public Food(@NotNull String name, @NotNull Material icon, @NotNull Rarity rarity,@NotNull int FoodRegeneration,int saturation,@NotNull double basePrice,@NotNull double HeartRegenerated,@NotNull double DamageTaked,@NotNull double HeartGived, List<PotionEffect> potionEffect) {
        this.name = name;
        this.icon = icon;
        this.rarity = rarity;
        this.basePrice = basePrice;
        this.foodRegeneration = FoodRegeneration;
        this.saturation = saturation;
        this.PotionEffect = potionEffect;
        this.hasEffects = true;
        if (HeartRegenerated <= 0) {this.HeartRegenerated = 0;}
        else {
            this.HeartRegenerated = HeartRegenerated;
            this.regen = true;
            this.hasAttribut = true;
        }
        if (DamageTaked <= 0) {this.DamageTaked = 0;}
        else {
            this.DamageTaked = DamageTaked;
            this.damage = true;
            this.hasAttribut = true;
        }
        if (HeartGived <= 0) {this.HeartGived = 0;}
        else {
            this.HeartGived = HeartGived;
            this.hgive = true;
            this.hasAttribut = true;
        }
        Bukkit.getPluginManager().registerEvents(this, RPG.getPlugin(RPG.class));
    }

    public ItemStack getItemStack()
    {
        ItemStack a = new ItemStack(icon);
        ItemMeta b = a.getItemMeta();

        List<String> description = new ArrayList<String>();
        if(hasAttribut){description.add(null);}
        if(this.regen){description.add(ChatColor.RED+"⪧ Recuperation de coeurs: "+ChatColor.GREEN+"+"+this.HeartRegenerated/2+" ❤");}
        if(this.damage){description.add(ChatColor.RED+"⪧ Degats recu: -"+this.DamageTaked/2+" ❤");}
        if(this.hgive){description.add(ChatColor.RED+"⪧ Gain de coeurs: "+ChatColor.GREEN+"+"+this.HeartGived/2+" ❤");}
        if(hasEffects)
        {
            description.add(null);
            description.add(ChatColor.LIGHT_PURPLE +"⪧ Effets :");
            for(int i = 0; i < this.PotionEffect.size(); i++)
            {
                String effect = PotionEffect.get(i).getType().getName() +" "+ PotionEffect.get(i).getAmplifier() + " ("+PotionEffect.get(i).getDuration()/1000+"s)";
                description.add(ChatColor.DARK_GRAY+"  ⪧"+effect);
            }
        }
        description.add(null);
        description.add(ChatColor.GOLD+""+basePrice+" Pieces.");
        description.add(rarity.getDescription());

        b.setDisplayName(rarity.getColor()+name);
        b.setLore(description);
        b.setUnbreakable(true);

        a.setItemMeta(b);
        return a;
    }

    @EventHandler
    public void onClickOnFood(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        if(event.getAction().equals(Action.RIGHT_CLICK_AIR))
        {
            if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(this.rarity.getColor()+this.name))
            {
                player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount()-1);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_BURP, 1.5F, 3.0F);
                if(regen) {double hearts = (int) (player.getMaxHealth() - player.getHealth());if(hearts < this.HeartRegenerated){player.setHealth(player.getMaxHealth());}else{player.setHealth(player.getHealth()+this.HeartRegenerated);}}

                if(damage) {if(player.getHealth() <= this.DamageTaked){player.setHealth(0);}else player.setHealth(player.getHealth()-this.DamageTaked);}

                if(hgive) {player.setMaxHealth(player.getMaxHealth()+HeartGived);}

                int foodlevel = 20 - player.getFoodLevel();
                if(foodlevel <= foodRegeneration){player.setFoodLevel(20);}else player.setFoodLevel((int) (player.getFoodLevel()+foodRegeneration));

                player.setSaturation((float) saturation);
                
                if(!(PotionEffect == null))
                {
                    for(int i = 0; i < PotionEffect.size(); i++)
                    {
                        player.addPotionEffect(PotionEffect.get(i));
                    }
                }
                return;
            }
            else return;
        }else return;
    }
}