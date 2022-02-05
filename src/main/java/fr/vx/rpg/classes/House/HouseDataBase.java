package fr.vx.rpg.classes.House;

import fr.vx.rpg.utils.MySql;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HouseDataBase
{
    public static void add(int id, String name)
    {
        try {
            PreparedStatement sts = MySql.getConnection().prepareStatement("SELECT `owner` FROM `house` WHERE `id`=?");
            sts.setInt(1, id);
            ResultSet rs = sts.executeQuery();
            if(!rs.next()) {
                sts.close();
                sts = MySql.getConnection().prepareStatement("INSERT INTO `house` (id, name) VALUES (?, ?)");
                sts.setInt(1, id);
                sts.setString(2, name);
                sts.executeUpdate();
                sts.close();
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static boolean isOwned(int id)
    {
        boolean x = false;
        try
        {
            PreparedStatement sts = MySql.getConnection().prepareStatement("SELECT `owned` FROM `house` WHERE `id` = ?");
            sts.setInt(1, id);
            ResultSet rs = sts.executeQuery();
            if(rs.next())
            {
                x = rs.getBoolean("owned");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return x;
    }

    public static String getOwner(int id)
    {
        String x = null;
        try
        {
            PreparedStatement sts = MySql.getConnection().prepareStatement("SELECT `owner` FROM `house` WHERE `id` = ?");
            sts.setInt(1, id);
            ResultSet rs = sts.executeQuery();
            if(rs.next())
            {
                x = rs.getString("owner");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return x;
    }

    public static void setOwner(int id, Player player)
    {
        try
        {
            PreparedStatement sts = MySql.getConnection().prepareStatement("UPDATE `house` SET `owner` = ? WHERE `id`=?");
            sts.setString(1, player.getUniqueId().toString());
            sts.setInt(2, id);
            sts.executeUpdate();
            sts.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void setOwned(int id)
    {
        try
        {
            PreparedStatement sts = MySql.getConnection().prepareStatement("UPDATE `house` SET `owned` = 1 WHERE `id`=?");
            sts.setInt(1, id);
            sts.executeUpdate();
            sts.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
