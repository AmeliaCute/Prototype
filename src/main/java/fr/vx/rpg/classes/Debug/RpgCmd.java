package fr.vx.rpg.classes.Debug;

import fr.vx.rpg.classes.Item.impl.Items;
import fr.vx.rpg.classes.mobs.impl.Larbin;
import net.minecraft.server.v1_16_R3.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RpgCmd implements CommandExecutor {

    private Player player;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(sender instanceof Player)
        {
            Player player = (Player) sender;
            this.player = player;
            switch(args[0])
            {
                case "Mobs":
                    WorldServer world = ((CraftWorld) player.getWorld()).getHandle();
                    world.addEntity(new Larbin(player.getLocation()));
                    break;
                case "Give":
                    Gui(1);
                    break;
                case "give":
                    Gui(1);
                    break;
                default:
                    player.sendMessage(ChatColor.RED+"Vous avez besoin d'un argument. (Mobs / Give)");
                    break;
            }
        }
        return false;
    }

    private void Gui(int id)
    {
        Inventory a = Bukkit.createInventory(null, 6*9);
        switch(id)
        {
            case 1:
                a.addItem(Items.test.getItemStack());
                a.addItem(Items.test2.getItemStack());
                a.addItem(Items.test3.getItemStack());
                player.openInventory(a);
                break;
            default:
                break;
        }
    }
}
