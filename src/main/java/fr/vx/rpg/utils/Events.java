package fr.vx.rpg.utils;

import fr.vx.rpg.RPG;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class Events
{
    public void register() {Bukkit.getPluginManager().registerEvents((Listener) this, RPG.getPlugin(RPG.class));}
}
