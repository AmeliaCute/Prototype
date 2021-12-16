package fr.vx.rpg.classes.Jobs.impl;

import fr.vx.rpg.classes.Item.impl.Items;
import fr.vx.rpg.classes.Jobs.JobItem;

public class jobs
{
    public static void Register()
    {
        JobItem silver_sword = new JobItem(Items.SILVER_SWORD.getItemStack(), JobItem.JobType.PALADIN, 3);
    }
}
