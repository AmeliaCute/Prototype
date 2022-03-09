package fr.vx.rpg.utils;

import org.bukkit.ChatColor;

public enum Icons
{
    FIRE("☀", ChatColor.RED),
    WATER("◕｡", ChatColor.BLUE),
    ICE("❄", ChatColor.AQUA),
    CLOUD("☁", ChatColor.WHITE),
    STAR("✨", ChatColor.GOLD),
    HEART("❤", ChatColor.GREEN),
    FIREBALL("☼", ChatColor.RED),
    GUILD("⚜", ChatColor.AQUA),
    MUSIC("♬", ChatColor.GREEN),
    ARROW("➹", ChatColor.WHITE),
    TINYHEART("♥", ChatColor.RED),

    //GUILD RANK:
    GUILD_CHIEF("♛", ChatColor.GOLD),
    GUILD_KNIGHT("❈", ChatColor.GREEN),
    GUILD_MEMBER("✦", ChatColor.RED),

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

