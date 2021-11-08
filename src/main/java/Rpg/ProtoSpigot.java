package Rpg;

import Rpg.Events.OnBreakBlock;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ProtoSpigot extends JavaPlugin {

    @Override
    public void onEnable() {
        Events();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void Events()
    {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new OnBreakBlock(), this);
    }
}
