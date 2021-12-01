package fr.vx.rpg.classes.mobs.impl;

import fr.vx.rpg.classes.Item.impl.Items;
import fr.vx.rpg.classes.mobs.Mob;
import fr.vx.rpg.classes.mobs.MobEquipment;
import net.minecraft.server.v1_16_R3.ChatComponentText;
import net.minecraft.server.v1_16_R3.EntityTypes;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

public class Goblin extends Mob
{
    public Goblin(Location location)
    {
        super(EntityTypes.ZOMBIE, location, "Goblin De La Mort Qui Tue", 50, Mobs.TEST_EQUIPMENT, Items.GOLD_NUGGET.getItemStack());
        this.setLocation(location.getX(), location.getY(), location.getZ(), -90, 0);
        this.setCustomName(new ChatComponentText("Goblin De La Mort Qui Tue"));
        this.setCustomNameVisible(true);
        LivingEntity entity = (LivingEntity) this.getBukkitEntity();
        MobEquipment.setEquipment(entity, Mobs.TEST_EQUIPMENT);
        MobEquipment.setHearth(entity, 50);
    }
}
