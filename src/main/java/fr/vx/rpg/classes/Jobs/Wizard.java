package fr.vx.rpg.classes.Jobs;

import fr.vx.rpg.utils.MySql;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Wizard
{

    public static void CreateWizardAccount(Player player)
    {
        try {
            PreparedStatement sts = MySql.getConnection().prepareStatement("SELECT `level` FROM `wizard` WHERE `uuid`='" + player.getUniqueId().toString() + "'");
            ResultSet rs = sts.executeQuery();
            if(!rs.next()) {
                sts.close();
                sts = MySql.getConnection().prepareStatement("INSERT INTO `wizard` (uuid, level) VALUES ('"+ player.getUniqueId().toString() + "', '0')");
                sts.executeUpdate();
                sts.close();
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static void setLvl(Player player, JobEnum jobEnum)
    {
        try {
            PreparedStatement sts = MySql.getConnection().prepareStatement("UPDATE `wizard` SET `level`= '" + jobEnum.getLvlId() + "' WHERE `player_uuid`='" + player.getUniqueId().toString() +"'");
            sts.executeUpdate();
            sts.close();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static int getLvl(Player player)
    {
        int joblvl = 0;
        try
        {
            PreparedStatement sts = MySql.getConnection().prepareStatement("SELECT `level` FROM `wizard` WHERE `uuid`= '" + player.getUniqueId().toString() + "' ");
            ResultSet rs = sts.executeQuery();
            if(rs.next()) {
                joblvl = JobEnum.getFromID(rs.getInt("level"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return joblvl;
    }
}
