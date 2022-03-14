package fr.vx.rpg.botania;

import fr.vx.rpg.botania.flowers.PureDaisy;

public class Botania {

    public static PureDaisy DAISY;

    public static void init() {registerItems();}

    public static void registerItems() {

        DAISY = new PureDaisy();

    }




}
