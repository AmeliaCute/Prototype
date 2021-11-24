package fr.vx.rpg;

import fr.vx.rpg.classes.Houses;
import org.bukkit.plugin.java.JavaPlugin;

import fr.vx.rpg.classes.Items;

public final class RPG extends JavaPlugin {

    @Override
    public void onEnable() {
    	
    	Items.register();
        Houses.Register();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
