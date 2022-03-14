package fr.vx.rpg.classes.Debug;

import fr.vx.rpg.botania.Botania;
import fr.vx.rpg.classes.Item.impl.Items;
import fr.vx.rpg.classes.mobs.impl.Larbin;
import net.minecraft.server.v1_16_R3.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class RpgCmd implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(sender instanceof Player)
        {
            Player player = ((Player) sender).getPlayer();
            if(args.length == 1)
            {
                if(args[0].equalsIgnoreCase("help"))
                {
                    player.sendMessage("Jsp");
                    return true;
                }
                if(args[0].equalsIgnoreCase("mob"))
                {
                    player.sendMessage("Argument invalide.");
                    player.sendMessage("Arg: LARBIN");
                    return true;
                }
                if(args[0].equalsIgnoreCase("give"))
                {
                    Inventory a = Bukkit.createInventory(null, 6*9);

                    for(int i = 0; i < Items.items.size(); i++)
                    {
                        a.addItem(Items.items.get(i));

                    }
                    //TODO à modif
                    a.addItem(Botania.DAISY.getItem().getItemStack());
                    player.openInventory(a);
                    return true;
                }
                if(args[0].equalsIgnoreCase("version"))
                {
                    player.sendMessage("Version: 1.3.6.1");
                    player.sendMessage("Date du build: 5/2/2022 a 1:41");
                }
            }
            if(args.length == 2)
            {
                if(args[0].equalsIgnoreCase("mob"))
                {
                    if(args[1].equalsIgnoreCase("LARBIN"))
                    {
                        WorldServer world = ((CraftWorld) player.getWorld()).getHandle();
                        world.addEntity(new Larbin(player.getLocation()));
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
