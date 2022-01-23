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
            PreparedStatement sts = MySql.getConnection().prepareStatement("SELECT `name` FROM `jobs` WHERE `uuid`=?");
            sts.setString(1, player.getUniqueId().toString());
            ResultSet rs = sts.executeQuery();
            if(!rs.next()) {
                sts.close();
                sts = MySql.getConnection().prepareStatement("INSERT INTO `jobs` (uuid, name) VALUES (?, ?)");
                sts.setString(1, player.getUniqueId().toString());
                sts.setString(2, player.getName());
                sts.executeUpdate();
                sts.close();
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
