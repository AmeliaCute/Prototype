package Rpg.Commands;

import Rpg.Materials;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static Rpg.Materials.SetAttack;
import static Rpg.Materials.Customize;

public class ProtoGive implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(sender instanceof Player){
            Player player = ((Player) sender).getPlayer();
            Inventory inv = Bukkit.createInventory(null, 9, ("Give"));
            inv.setItem(0, Customize(1,false));
            inv.setItem(1, Customize(2,false));
            inv.setItem(2, Customize(3,false));
            inv.setItem(3, Customize(4,false));
            inv.setItem(4, Customize(5,true));

            ItemStack AmetixSword = Customize(6, true);
            ItemStack AmetixSword2 = SetAttack(AmetixSword, 10);

            inv.setItem(5,AmetixSword2);

            player.updateInventory();
            player.openInventory(inv);
        }
        return false;
    }
}
