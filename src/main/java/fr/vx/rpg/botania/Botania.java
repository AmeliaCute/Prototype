package fr.vx.rpg.botania;

import fr.vx.rpg.botania.craft.TransformationCraft.TransformationCraft;
import fr.vx.rpg.botania.flowers.PureDaisy;
import fr.vx.rpg.botania.runnables.TransformationDaisy;

public class Botania {

    public static PureDaisy DAISY;

    public static void init() {
        registerItems();
    }

    public static void registerCrafts() {


    }

    public static void registerItems() {

        DAISY = new PureDaisy();

    }




}
