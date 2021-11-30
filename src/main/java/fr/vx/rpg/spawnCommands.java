package fr.vx.rpg;

import fr.vx.rpg.classes.mobs.impl.Mobs;
import net.minecraft.server.v1_16_R3.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.Player;

public class spawnCommands implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        Player player = (Player) sender;
        Bukkit.broadcastMessage("1");
        WorldServer world = ((CraftWorld) player.getWorld()).getHandle();
        Bukkit.broadcastMessage("2");
        world.addEntity(Mobs.TEST);
        Bukkit.broadcastMessage("3");
        return false;
    }
}
