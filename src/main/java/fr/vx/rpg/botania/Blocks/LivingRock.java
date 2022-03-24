package fr.vx.rpg.botania.Blocks;

import fr.vx.rpg.botania.Botania;
import fr.vx.rpg.classes.Item.Item;
import fr.vx.rpg.classes.Item.Rarity;
import fr.vx.rpg.classes.blocks.ModdedBlock;
import org.bukkit.Material;

public class LivingRock extends ModdedBlock {
    public LivingRock() {
        super(new Item(Material.STONE, "Living Rock", Rarity.UNCOMMON,1000), false);
    }
}
