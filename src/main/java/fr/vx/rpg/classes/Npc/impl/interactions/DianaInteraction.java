package fr.vx.rpg.classes.Npc.impl.interactions;

import fr.vx.rpg.RPG;
import fr.vx.rpg.classes.History.HistoryBranch;
import fr.vx.rpg.classes.Item.impl.Items;
import fr.vx.rpg.classes.Npc.NpcInteraction;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class DianaInteraction extends NpcInteraction
{

    @Override
    public int MinimumHistoryLevel() { return 0; }

    @Override
    public boolean PlayerDontHaveHlMessage() {
        return false;
    }

    @Override
    public HistoryBranch HistoryBranch() {
        return HistoryBranch.DISCOVERING_THE_WORLD;
    }

    @Override
    public String NameOfNpc() {
        return "Diana";
    }

    @Override
    protected int action(Player player)
    {
        new BukkitRunnable(){
            int i = 5;
            @Override
            public void run()
            {
                if(i == 5) { player.sendMessage(NameOfNpc()+ChatColor.RESET+": Salut, tu t'appelle "+player.getName()+" c'est ca ?");}

                if(i == 4) { player.sendMessage(NameOfNpc()+ChatColor.RESET+": Oups pardon j'ai oublier de me presenter...");}

                if(i == 3) { player.sendMessage(NameOfNpc()+ChatColor.RESET+": Je m'appelle Diana, je vend quelque bricoles..");}

                if(i == 2) { player.sendMessage(NameOfNpc()+ChatColor.RESET+": Tiens je t'offre cette epee, etant donner que tu est nouveau ici tu ne dois pas avoir beaucoup d'equipement..");}

                if(i == 1) {  player.getInventory().addItem(Items.EpeeDeDiana.getItemStack());}

                i--;

            }
        }.runTaskTimer(RPG.getPlugin(RPG.class),40, 5);
        return 0;
    }

}
