package fr.vx.rpg.classes.teams;

import fr.vx.rpg.utils.Icons;
import fr.vx.rpg.utils.MySql;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class coopCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(sender instanceof Player)
        {
            if(args.length == 2)
            {
                if(args[0].equals("create"))
                {
                    Coop.Create(((Player) sender).getPlayer(), args[1]);
                }
                if(args[0].equals("invite"))
                {
                    Player player = Bukkit.getPlayer(args[1]);
                    Coop.Invite(((Player) sender).getPlayer(), player);
                }
                if(args[0].equals("accept"))
                {
                    Player player = Bukkit.getPlayer(args[1]);
                    String coop = Coop.getCoop(player);
                    if(Coop.getPendingInvite(((Player) sender).getPlayer()).equals(coop))
                    {
                        try {
                            PreparedStatement sts = MySql.getConnection().prepareStatement("UPDATE `player` SET `coopPending`= 'NULL' WHERE `uuid`=?");
                            sts.setString(1, ((Player) sender).getPlayer().getUniqueId().toString());
                            sts.executeUpdate();
                            sts.close();

                            PreparedStatement sts1 = MySql.getConnection().prepareStatement("UPDATE `player` SET `coop`= ? WHERE `uuid`=?");
                            sts1.setString(1, coop);
                            sts1.setString(2, ((Player) sender).getPlayer().getUniqueId().toString());
                            sts1.executeUpdate();
                            sts1.close();

                            sender.sendMessage(ChatColor.LIGHT_PURPLE+"⪧ Vous avez rejoin "+Coop.getCoopName(coop));
                            player.sendMessage(ChatColor.LIGHT_PURPLE+"⪧ "+ChatColor.GREEN+ ((Player) sender).getPlayer().getName()+ChatColor.LIGHT_PURPLE+" a rejoin la team.");
                            ((Player) sender).getPlayer().setPlayerListName(ChatColor.AQUA+""+ Icons.GUILD.icon()+" §r"+sender.getName());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }
        return false;
    }
}
