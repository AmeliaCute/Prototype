package fr.vx.rpg.classes.Player.Chats;

import fr.vx.rpg.classes.teams.Coop;
import fr.vx.rpg.utils.MySql;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Chat implements Listener
{
    @EventHandler
    public void onTalking(PlayerChatEvent event)
    {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if(getChatMode(player) == 1)
        {
            event.setCancelled(true);
            Coop.coopMessages(player, message);
            return;
        }
    }


    public static int getChatMode(Player player)
    {
        int chatMode = 0;
        try
        {
            PreparedStatement sts = MySql.getConnection().prepareStatement("SELECT `Chat` FROM `player` WHERE `uuid`= ?");
            sts.setString(1, player.getUniqueId().toString());
            ResultSet rs = sts.executeQuery();
            if (rs.next()) {
                chatMode = rs.getInt("Chat");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chatMode;
    }

    public static void setChatMod(int mode, Player player)
    {
        try
        {
            PreparedStatement sts = MySql.getConnection().prepareStatement("UPDATE `player` SET `Chat`= ? WHERE `uuid`=?");
            sts.setInt(1, mode);
            sts.setString(2, player.getUniqueId().toString());
            sts.executeUpdate();
            sts.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
