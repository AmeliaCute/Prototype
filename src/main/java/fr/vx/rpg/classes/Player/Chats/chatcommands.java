package fr.vx.rpg.classes.Player.Chats;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class chatcommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(sender instanceof Player)
        {
            Player player = ((Player) sender).getPlayer();
            if(args.length == 0 || args.length > 1)
            {
                player.sendMessage("/chat ALL/COOP");
            }
            else
            {
                if(args[0].equalsIgnoreCase("all"))
                {
                    player.sendMessage(ChatColor.AQUA+"✉ §r Chat mis en publique.");
                    Chat.setChatMod(0, player);
                }
                if(args[0].equalsIgnoreCase("coop"))
                {
                    player.sendMessage(ChatColor.AQUA+"✉ §r Chat mis en coop only.");
                    Chat.setChatMod(1, player);
                }
            }
        }
        return false;
    }
}
