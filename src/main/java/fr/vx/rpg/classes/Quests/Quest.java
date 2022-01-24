package fr.vx.rpg.classes.Quests;

import fr.vx.rpg.utils.MySql;
import fr.vx.rpg.utils.Math;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Quest
{
    public static void CreateAccount(Player player)
    {
        try
        {
            PreparedStatement sts = MySql.getConnection().prepareStatement("SELECT `welcome` FROM `quest` WHERE `uuid`=?");
            sts.setString(1, player.getUniqueId().toString());
            ResultSet rs = sts.executeQuery();
            if(!rs.next())
            {
                sts.close();
                sts = MySql.getConnection().prepareStatement("INSERT INTO `quest` (uuid, welcome) VALUES (?, 1)");
                sts.setString(1, player.getUniqueId().toString());
                System.out.println("CREATED NEW ACCOUNT.");
                sts.executeUpdate();
                sts.close();
            }
        }
        catch (SQLException e){ e.printStackTrace();}
    }

    public static int getQuest(String id, Player player)
    {
        int res = 0;
        try
        {
            PreparedStatement sts = MySql.getConnection().prepareStatement("SELECT ? FROM `quest` WHERE `uuid`=?");
            sts.setString(1, id);
            sts.setString(2, player.getUniqueId().toString());
            ResultSet rs = sts.executeQuery();
            if(rs.next())
            {
                res = rs.getInt(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void finishQuest(String id, Player player)
    {
        try
        {
            PreparedStatement sts = MySql.getConnection().prepareStatement("UPDATE `quest` SET ?= 1 WHERE `uuid`= ?");
            sts.setString(1, id);
            sts.setString(2, player.getUniqueId().toString());
            sts.executeUpdate();
            sts.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addAdvancement(String id, Player player)
    {
        try
        {
            PreparedStatement sts = MySql.getConnection().prepareStatement("UPDATE `quest` SET ? = "+ Math.Addition(getAdvancement(id, player), 1) +" WHERE `uuid`= ?");
            sts.setString(1, id);
            sts.setString(2, player.getUniqueId().toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getAdvancement(String id, Player player)
    {
        int res = 0;
        try {
            PreparedStatement sts = MySql.getConnection().prepareStatement("SELECT ? FROM `quest` WHERE `uuid`=?");
            sts.setString(1, id);
            sts.setString(2, player.getUniqueId().toString());
            ResultSet rs = sts.executeQuery();
            if(rs.next())
            {
                res = rs.getInt(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //TODO faire une fonction pour savoir si une quete est lock / pour unlock une quete.

    //Cosmetiques

    public static void finishedQuestMessage(String questName, Player player)
    {
        player.sendMessage(ChatColor.LIGHT_PURPLE+"◆"+ChatColor.WHITE+"Quete terminer ! ("+questName+")");
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.5F, 3F);
    }
}
