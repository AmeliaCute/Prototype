package fr.vx.rpg.classes.Debug;

import fr.vx.rpg.classes.mobs.impl.Zombies.Larbin;
import net.minecraft.server.v1_16_R3.WorldServer;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.Player;

public class spawn implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        WorldServer world = ((CraftWorld) player.getWorld()).getHandle();
        Location location = player.getLocation();
        world.addEntity(new Larbin(location));
        return false;
    }
}
