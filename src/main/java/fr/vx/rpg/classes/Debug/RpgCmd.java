package fr.vx.rpg.classes.Debug;

import fr.vx.rpg.classes.Item.impl.Items;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

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
                    break;
                case "Give":
                    Gui(1);
                    break;
                case "":
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
                a.setItem(0, Items.test.getItemStack());
                player.openInventory(a);
                break;
            default:
                break;
        }
    }
}
