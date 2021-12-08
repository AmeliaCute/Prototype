package fr.vx.rpg;

import fr.vx.rpg.classes.Debug.spawn;
import fr.vx.rpg.classes.House.impl.Houses;
import org.bukkit.plugin.java.JavaPlugin;

import fr.vx.rpg.classes.Item.impl.Items;

public final class RPG extends JavaPlugin {

    @Override
    public void onEnable() {
    	
    	Items.register();
        Houses.Register();
        this.getCommand("spawn").setExecutor(new spawn());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
