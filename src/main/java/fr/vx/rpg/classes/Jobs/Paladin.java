package fr.vx.rpg.classes.Jobs;

import fr.vx.rpg.utils.MySql;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Paladin
{

    public static void setLvl(Player player, JobRank jobRank)
    {
        try {
            PreparedStatement sts = MySql.getConnection().prepareStatement("UPDATE `jobs` SET `level_paladin`= '?' WHERE `uuid`='?'");
            sts.setInt(1, jobRank.getLvlId());
            sts.setString(2, player.getUniqueId().toString());
            sts.executeUpdate();
            sts.close();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static int getLvl(Player player)
    {
        int joblvl = 0;
        try
        {
            PreparedStatement sts = MySql.getConnection().prepareStatement("SELECT `level_paladin` FROM `jobs` WHERE `uuid`= '?'");
            sts.setString(1, player.getUniqueId().toString());
            ResultSet rs = sts.executeQuery();
            if(rs.next()) {
                joblvl = JobRank.getFromNumber(rs.getInt("level_paladin"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return joblvl;
    }

    public static void addXp(Player player, int xp)
    {
        int result = xp+getXp(player);
        try
        {
            PreparedStatement sts = MySql.getConnection().prepareStatement("UPDATE `jobs` SET `exp_paladin`= '?' WHERE `uuid`='?'");
            sts.setInt(1, result);
            sts.setString(2, player.getUniqueId().toString());
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
            PreparedStatement sts = MySql.getConnection().prepareStatement("SELECT `exp_paladin` FROM `jobs` WHERE `uuid`= '?' ");
            ResultSet rs = sts.executeQuery();
            sts.setString(1, player.getUniqueId().toString());
            if(rs.next())
            {
                xp = rs.getInt("exp_paladin");
            }
        }
        catch (SQLException e) {e.printStackTrace();}
        return xp;
    }
}
