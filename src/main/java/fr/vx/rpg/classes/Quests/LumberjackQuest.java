package fr.vx.rpg.classes.Quests;

import fr.vx.rpg.RPG;
import fr.vx.rpg.classes.Jobs.Lumberjack;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class LumberjackQuest implements Listener
{
    public int QuestId;
    public String name;
    public int expEarned;
    public Material blockToBreak;

    public int MaxCount;
    public boolean hasCount;

    public LumberjackQuest(int QuestId, String name, int expEarned ,Material blockToBreak)
    {
        this.QuestId = QuestId;
        this.name = name;
        this.expEarned = expEarned;
        this.blockToBreak = blockToBreak;
        Bukkit.getPluginManager().registerEvents(this, RPG.getPlugin(RPG.class));
    }

    public LumberjackQuest(int QuestId,int MaxCount ,String name, int expEarned ,Material blockToBreak)
    {
        this.hasCount = true;
        this.QuestId = QuestId;
        this.MaxCount = MaxCount;
        this.name = name;
        this.expEarned = expEarned;
        this.blockToBreak = blockToBreak;
        Bukkit.getPluginManager().registerEvents(this, RPG.getPlugin(RPG.class));
    }

    @EventHandler
    public void OnBlockBreak(BlockBreakEvent event)
    {
        if(event.getBlock().getType().equals(this.blockToBreak))
        {
            if(hasCount)
            {
                int a = Quest.getAdvancement(QuestId, event.getPlayer());
                if (a == MaxCount){return;}
                if (a == MaxCount-1)
                {
                    Quest.addAdvancement(QuestId, event.getPlayer());
                    event.getPlayer().sendMessage(ChatColor.DARK_PURPLE+"◆ "+ChatColor.LIGHT_PURPLE+"Quête terminer ! "+ChatColor.WHITE+"("+name+")");
                    event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 3.0F, 0.5F);
                    Quest.setQuest(QuestId, event.getPlayer(), 1);
                    Lumberjack.addXp(event.getPlayer(), expEarned);
                }else {Quest.addAdvancement(QuestId, event.getPlayer());}
            }else
            {
                boolean b = Quest.getQuest(QuestId, event.getPlayer());
                if(b == true){return;}
                else
                {
                    event.getPlayer().sendMessage(ChatColor.DARK_PURPLE+"◆ "+ChatColor.LIGHT_PURPLE+"Quête terminer ! "+ChatColor.WHITE+"("+name+")");
                    event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 3.0F, 0.5F);
                    Quest.setQuest(QuestId, event.getPlayer(), 1);
                }
            }
        }
    }
}
