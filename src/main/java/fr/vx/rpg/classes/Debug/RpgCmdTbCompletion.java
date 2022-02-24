package fr.vx.rpg.classes.Debug;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import java.util.Arrays;
import java.util.List;

public class RpgCmdTbCompletion implements TabCompleter
{
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args)
    {
        if(args.length == 1)
        {
            return Arrays.asList("give","mob","stats","version");
        }
        return null;
    }
}
