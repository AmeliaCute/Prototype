package fr.vx.rpg.classes.Jobs;

import fr.vx.rpg.utils.MySql;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Lumberjack
{
    public static void setLvl(Player player, JobRank jobRank)
    {
        try {
            PreparedStatement sts = MySql.getConnection().prepareStatement("UPDATE `jobs` SET `level_lumberjack`= '" + jobRank.getLvlId() + "' WHERE `uuid`='" + player.getUniqueId().toString() +"'");
            sts.executeUpdate();
            sts.close();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static int getLvl(Player player)
    {
        int joblvl = 0;
        try
        {
            PreparedStatement sts = MySql.getConnection().prepareStatement("SELECT `level_lumberjack` FROM `jobs` WHERE `uuid`= '" + player.getUniqueId().toString() + "' ");
            ResultSet rs = sts.executeQuery();
            if(rs.next()) {
                joblvl = JobRank.getFromNumber(rs.getInt("level_lumberjack"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return joblvl;
    }

    public static void addXp(Player player, int xp)
    {
        int result = xp+getXp(player);
        try
        {
            PreparedStatement sts = MySql.getConnection().prepareStatement("UPDATE `jobs` SET `exp_lumberjack`= '" +result+"' WHERE `uuid`='" + player.getUniqueId().toString() +"'");
            sts.executeUpdate();
            sts.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static int getXp(Player player)
    {
        int xp = 0;
        try
        {
            PreparedStatement sts = MySql.getConnection().prepareStatement("SELECT `exp_lumberjack` FROM `jobs` WHERE `uuid`= '" + player.getUniqueId().toString() + "' ");
            ResultSet rs = sts.executeQuery();
            if(rs.next())
            {
                xp = rs.getInt("exp_lumberjack");
            }
        }
        catch (SQLException e) {e.printStackTrace();}
        return xp;
    }
}
