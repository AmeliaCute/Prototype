package fr.vx.rpg.classes.Npc.impl.interactions;

import fr.vx.rpg.classes.House.History.HistoryBranch;
import fr.vx.rpg.classes.Npc.NpcInteraction;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class DianaInteraction extends NpcInteraction
{

    @Override
    public int HistoryLevel() {
        return 0;
    }

    @Override
    public HistoryBranch HistoryBranch() {
        return HistoryBranch.DISCOVERING_THE_WORLD;
    }

    @Override
    public String NameOfNpc() {
        return ChatColor.BOLD+"Diana";
    }

    @Override
    protected int action(Player player)
    {
        player.sendMessage(NameOfNpc()+ChatColor.RESET+" Salut!");
        return 0;
    }

}
