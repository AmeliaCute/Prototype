package fr.vx.rpg.utils;

import org.bukkit.ChatColor;

public class logger
{
    public final String MODID;
    public logger(String MODID)
    {
        this.MODID = MODID;
    }

    public void error(String message)
    {
        System.err.println(ChatColor.RED+"["+this.MODID+"] "+message);
    }

    public void info(String message)
    {
        System.out.println(ChatColor.GREEN+"["+this.MODID+"] "+message);
    }
}
