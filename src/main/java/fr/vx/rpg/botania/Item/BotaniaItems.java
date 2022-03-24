package fr.vx.rpg.botania.Item;

import fr.vx.rpg.botania.Blocks.LivingRock;
import fr.vx.rpg.botania.Blocks.Pool.ManaPool;
import fr.vx.rpg.botania.Botania;
import fr.vx.rpg.botania.flowers.PureDaisy;
import fr.vx.rpg.classes.Item.Item;
import fr.vx.rpg.classes.Item.Rarity;
import org.bukkit.Material;

import java.util.Arrays;

public class BotaniaItems
{
    public static Item ManaSteelIngot;

    public static Item MANAPOOLITEM1;
    public static Item MANAPOOLITEM2;
    public static Item MANAPOOLITEM3;
    public static Item MANAPOOLITEM4;

    public static PureDaisy DAISY;
    public static ManaPool MANAPOOL;
    public static LivingRock LIVINGROCK;

    public static void init()
    {
        ManaSteelIngot = new Item(Material.CYAN_DYE, "Lingot d'acier de Mana", Rarity.RARE, 3000);

        MANAPOOLITEM1 = new Item(Material.CARROT_ON_A_STICK, "ManaPoolItem1 sdrifgrgsrhgksugh", Rarity.UNCOMMON, 0);
        MANAPOOLITEM2 = new Item(Material.CARROT_ON_A_STICK, "ManaPoolItem2 asjoifsdfosufdhsi", Rarity.UNCOMMON, 0);
        MANAPOOLITEM3 = new Item(Material.CARROT_ON_A_STICK, "ManaPoolItem3 pcsdfopgsfd9ipsdu", Rarity.UNCOMMON, 0);
        MANAPOOLITEM4 = new Item(Material.CARROT_ON_A_STICK, "ManaPoolItem4 zsldljepqjgpqprjp", Rarity.UNCOMMON, 0);

        DAISY = new PureDaisy();
        MANAPOOL = new ManaPool();
        LIVINGROCK = new LivingRock();

        Botania.ITEMS.addAll(Arrays.asList
                (
                        ManaSteelIngot.getItemStack(),
                        DAISY.getItem().getItemStack(),
                        MANAPOOL.getItemStack(),
                        LIVINGROCK.getItem().getItemStack()
                ));
    }
}
