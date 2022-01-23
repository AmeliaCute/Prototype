package fr.vx.rpg.classes.Jobs;

import fr.vx.rpg.utils.MySql;
import fr.vx.rpg.utils.Math;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Miner
{
    public static void setLvl(Player player, JobRank jobRank)
    {
        try {
            PreparedStatement sts = MySql.getConnection().prepareStatement("UPDATE `jobs` SET `level_miner`= ? WHERE `uuid`=?");
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
            PreparedStatement sts = MySql.getConnection().prepareStatement("SELECT `level_miner` FROM `jobs` WHERE `uuid`= ?");
            ResultSet rs = sts.executeQuery();
            sts.setString(1, player.getUniqueId().toString());
            if(rs.next()) {
                joblvl = JobRank.getFromNumber(rs.getInt("level_miner"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return joblvl;
    }

    public static void addXp(Player player, int xp)
    {
        int result = Math.Addition(xp,getXp(player));
        try
        {
            PreparedStatement sts = MySql.getConnection().prepareStatement("UPDATE `jobs` SET `exp_miner`= ? WHERE `uuid`=?");
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
            PreparedStatement sts = MySql.getConnection().prepareStatement("SELECT `exp_miner` FROM `jobs` WHERE `uuid`= ?");
            ResultSet rs = sts.executeQuery();
            sts.setString(1, player.getUniqueId().toString());
            if(rs.next())
            {
                xp = rs.getInt("exp_miner");
            }
        }
        catch (SQLException e) {e.printStackTrace();}
        return xp;
    }
}
