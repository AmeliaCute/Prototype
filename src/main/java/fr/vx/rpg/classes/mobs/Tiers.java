package fr.vx.rpg.classes.mobs;

import org.bukkit.ChatColor;

public enum Tiers
{
    One(ChatColor.WHITE),
    Two(ChatColor.GREEN),
    Three(ChatColor.YELLOW),
    Four(ChatColor.RED),
    Five(ChatColor.LIGHT_PURPLE),
    ;

    private final ChatColor color;
    private Tiers (ChatColor color)
    { this.color = color; }
    public ChatColor getColor(){return color;}
}
