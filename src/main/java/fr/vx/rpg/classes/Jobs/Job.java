package fr.vx.rpg.classes.Jobs;

import fr.vx.rpg.utils.MySql;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Job
{
    public static void CreateAccount(Player player)
    {
        try {
            PreparedStatement sts = MySql.getConnection().prepareStatement("SELECT `name` FROM `jobs` WHERE `uuid`='" + player.getUniqueId().toString() + "'");
            ResultSet rs = sts.executeQuery();
            if(!rs.next()) {
                sts.close();
                sts = MySql.getConnection().prepareStatement("INSERT INTO `jobs` (uuid, name) VALUES ('"+ player.getUniqueId().toString() + "', '"+player.getName()+"')");
                sts.executeUpdate();
                sts.close();
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
