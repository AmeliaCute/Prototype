package Rpg.Commands;

import Rpg.Materials;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ProtoGive implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(sender instanceof Player){
            Player player = ((Player) sender).getPlayer();
            Inventory inv = Bukkit.createInventory(null, 9, ("Give"));
            inv.setItem(0, Materials.Customize(1,false));
            inv.setItem(1, Materials.Customize(2,false));
            inv.setItem(2, Materials.Customize(3,false));
            inv.setItem(3, Materials.Customize(4,false));

            player.updateInventory();
            player.openInventory(inv);
        }
        return false;
    }
}
