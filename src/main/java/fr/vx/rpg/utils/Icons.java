package fr.vx.rpg.utils;

import org.bukkit.ChatColor;

public enum Icons
{
    FIRE("🔥", ChatColor.RED),
    WATER("💧", ChatColor.BLUE),
    WATER_WAVE("🌊", ChatColor.BLUE),
    ICE("❄", ChatColor.AQUA),
    CYCLONE("🌀", ChatColor.WHITE),
    STAR("✨", ChatColor.GOLD),
    HEART("❤", ChatColor.GREEN),
    FIREBALL("💥", ChatColor.RED),
    ;
    private final String icon;
    private final ChatColor color;
    Icons(String icon, ChatColor color)
    {
        this.icon = icon;
        this.color = color;
    }

    public String icon() {return icon;}
    public ChatColor color() {return color;}
}
