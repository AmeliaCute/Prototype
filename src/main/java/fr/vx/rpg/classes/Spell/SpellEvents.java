package fr.vx.rpg.classes.Spell;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class SpellEvents implements Listener {

    //MagicWand

    public static void sendSpellByItem(Spell spell, Player player)
    {
        spell.send(player);
    }

}
