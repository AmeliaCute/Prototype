package fr.vx.rpg.classes.Player;

import fr.vx.rpg.utils.Math;
import fr.vx.rpg.utils.MySql;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Coins
{
    public static void addPlayer(Player player)
    {
        try {
            PreparedStatement sts = MySql.getConnection().prepareStatement("SELECT `balance` FROM `player` WHERE `uuid`=?");
            sts.setString(1, player.getUniqueId().toString());
            ResultSet rs = sts.executeQuery();
            if(!rs.next()) {
                sts.close();
                sts = MySql.getConnection().prepareStatement("INSERT INTO `player` (uuid, name) VALUES (?, ?)");
                sts.setString(1, player.getUniqueId().toString());
                sts.setString(2, player.getDisplayName().toString());
                sts.executeUpdate();
                sts.close();
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static void add(Player player, double amount)
    {
        try
        {
            PreparedStatement sts = MySql.getConnection().prepareStatement("UPDATE `player` SET `balance` = ? WHERE `uuid`=?");
            sts.setString(2, player.getUniqueId().toString());
            sts.setDouble(1, Math.DoubleAddition(getBalance(player), amount));
            if(Math.DoubleAddition(getBalance(player), amount) > 999999999)
            {
                sts.close();
                player.sendMessage(ChatColor.RED+"Vos poches sont pleine, vous devriez allez a la banque");
                return;
            }
            sts.executeUpdate();
            sts.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void remove(Player player, double amount)
    {
        try
        {
            PreparedStatement sts = MySql.getConnection().prepareStatement("UPDATE `player` SET `balance` = ? WHERE `uuid`=?");
            sts.setString(2, player.getUniqueId().toString());
            sts.setDouble(1, Math.DoubleSubstraction(getBalance(player), amount));
            if(Math.DoubleSubstraction(getBalance(player), amount) < 0)
            {
                sts.close();
                player.sendMessage(ChatColor.RED+"Operation impossible.");
                return;
            }
            sts.executeUpdate();
            sts.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static double getBalance(Player player)
    {
        double x = 0.0;

        try
        {
            PreparedStatement sts = MySql.getConnection().prepareStatement("SELECT `balance` FROM `player` WHERE `uuid` = ?");
            sts.setString(1, player.getUniqueId().toString());
            ResultSet rs = sts.executeQuery();
            if(rs.next())
            {
                x = rs.getDouble("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return x;
    }
}
