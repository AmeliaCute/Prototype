package fr.vx.rpg.classes.mobs.impl;

import fr.vx.rpg.classes.Item.impl.Items;
import fr.vx.rpg.classes.mobs.Mob;
import fr.vx.rpg.classes.mobs.MobEquipment;
import net.minecraft.server.v1_16_R3.EntityTypes;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Mobs
{
    public static Mob TEST;
    public static MobEquipment TEST_EQUIPMENT;

    public static void register()
    {
        // MOB EQUIPMENT :

        TEST_EQUIPMENT = new MobEquipment(new ItemStack(Material.IRON_HELMET), new ItemStack(Material.DIAMOND_CHESTPLATE), new ItemStack(Material.NETHERITE_LEGGINGS), new ItemStack(Material.AIR), Items.IRON_ESPADON.getItemStack());

        // MOB:

        TEST = new Mob(EntityTypes.HUSK, new Location(Bukkit.getWorld("world"), 12, 19, 51), "Test", 50, TEST_EQUIPMENT, Items.GOLD_NUGGET.getItemStack());
    }
}
