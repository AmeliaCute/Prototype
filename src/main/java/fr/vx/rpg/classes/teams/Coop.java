package fr.vx.rpg.classes.teams;

import fr.vx.rpg.utils.Icons;
import fr.vx.rpg.utils.MySql;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Coop {
    public static void Create(Player player, String coopName) {
        if (getCoop(player).equals("false")) {
            UUID uuid = UUID.randomUUID();
            String owner_uuid = player.getUniqueId().toString();
            try {
                PreparedStatement sts = MySql.getConnection().prepareStatement("SELECT `Owner` FROM `coop` WHERE `coop_uuid`=?");
                PreparedStatement sts1 = MySql.getConnection().prepareStatement("UPDATE `player` SET `coop`= ? WHERE `uuid`=?");
                sts1.setString(1, uuid.toString());
                sts1.setString(2, player.getUniqueId().toString());
                sts1.executeUpdate();
                sts1.close();
                sts.setString(1, owner_uuid);
                ResultSet rs = sts.executeQuery();
                if (!rs.next()) {
                    sts.close();
                    sts = MySql.getConnection().prepareStatement("INSERT INTO `coop` (coop_uuid, owner_name, Owner, name) VALUES (?, ?, ?, ?)");
                    sts.setString(1, uuid.toString());
                    sts.setString(2, player.getName());
                    sts.setString(3, player.getUniqueId().toString());
                    sts.setString(4, coopName);
                    sts.executeUpdate();
                    sts.close();
                    player.sendMessage(ChatColor.LIGHT_PURPLE + "⪧ Nouvelle team cree : " + coopName + "!");
                    player.setPlayerListName(ChatColor.AQUA+""+ Icons.GUILD.icon()+" §r"+player.getName());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return;
        }
        player.sendMessage("Tu es deja dans une team");
    }

    public static void Invite(Player Owner, Player playerInvited) {
        if (getOwner(getCoop(Owner)).equals(Owner.getUniqueId().toString())) {
            Owner.sendMessage(ChatColor.LIGHT_PURPLE + "⪧ Invitation envoyer a " + ChatColor.GREEN + playerInvited.getName());
            if (getCoop(playerInvited).equals("false")) {
                try
                {
                    PreparedStatement sts = MySql.getConnection().prepareStatement("UPDATE `player` SET `coopPending`= ? WHERE `uuid`=?");
                    sts.setString(1, getCoop(Owner));
                    sts.setString(2, playerInvited.getUniqueId().toString());
                    sts.executeUpdate();
                    sts.close();
                    playerInvited.sendMessage(ChatColor.LIGHT_PURPLE+"⪧ Invitation recu de "+Owner.getName()+".");
                    playerInvited.sendMessage(ChatColor.LIGHT_PURPLE+"⪧ Fait /coop accept "+Owner.getName()+" pour accepter.");
                    return;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (getCoop(playerInvited).equals(getCoop(Owner))) {
                Owner.sendMessage(ChatColor.LIGHT_PURPLE + "⪧ " + playerInvited.getName() + " est deja dans la team.");
                return;
            }
            if (!getCoop(playerInvited).equals("false")) {
                Owner.sendMessage(ChatColor.LIGHT_PURPLE + "⪧ " + playerInvited.getName() + " est deja dans une autre team.");
                return;
            }
        }
        else
        {
            Owner.sendMessage("Impossible");
        }
    }

    public static void Kick(Player Owner, Player playerKick) {
        if (getCoop(Owner).equals(getCoop(playerKick)) && getOwner(getCoop(Owner)).equals(Owner.getUniqueId().toString())) {

        }
    }

    public static String getCoop(Player player) {

        String x = "false";
        try {
            PreparedStatement sts = MySql.getConnection().prepareStatement("SELECT `coop` FROM `player` WHERE `uuid`= ?");
            sts.setString(1, player.getUniqueId().toString());
            ResultSet rs = sts.executeQuery();
            if (rs.next()) {
                x = rs.getString("coop");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return x;
    }

    public static String getOwner(String Coopuuid) {
        String owner = null;
        try {
            PreparedStatement sts = MySql.getConnection().prepareStatement("SELECT `Owner` FROM `coop` WHERE `coop_uuid`= ?");
            sts.setString(1, Coopuuid);
            ResultSet rs = sts.executeQuery();
            if (rs.next()) {
                owner = rs.getString("Owner");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return owner;
    }

    public static String getCoopName(String CoopUUID) {
        String name = null;
        try {
            PreparedStatement sts = MySql.getConnection().prepareStatement("SELECT `name` FROM `coop` WHERE `coop_uuid`= ?");
            sts.setString(1, CoopUUID);
            ResultSet rs = sts.executeQuery();
            if (rs.next()) {
                name = rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    public static String getPendingInvite(Player player)
    {
        String x = null;
        try {
            PreparedStatement sts = MySql.getConnection().prepareStatement("SELECT `coopPending` FROM `player` WHERE `uuid`= ?");
            sts.setString(1, player.getUniqueId().toString());
            ResultSet rs = sts.executeQuery();
            if (rs.next()) {
                x = rs.getString("coopPending");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return x;
    }

    public static void coopMessages(Player sender, String message)
    {
        List<Player> player = new ArrayList<Player>();
        int i = 0;
        while(i == Bukkit.getOnlinePlayers().size())
        {
            try
            {
                PreparedStatement sts = MySql.getConnection().prepareStatement("SELECT `uuid` FROM `player` WHERE `coop`= ?");
                sts.setString(1, getCoop(sender));
                ResultSet rs = sts.executeQuery();
                if (rs.next()) {
                    player.add(Bukkit.getPlayer(UUID.fromString(rs.getString("uuid"))));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            i++;
        }
        for(int x = 0; x < player.size(); i++)
        {
            player.get(i).sendMessage(ChatColor.LIGHT_PURPLE+"⪧ ✉ "+ChatColor.GREEN+sender.getName()+": "+message);
        }
        return;
    }
}
