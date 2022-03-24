package fr.vx.rpg.botania.Blocks.Pool;

import fr.vx.rpg.RPG;
import fr.vx.rpg.botania.Botania;
import fr.vx.rpg.botania.Item.BotaniaItems;
import fr.vx.rpg.classes.Item.Item;
import fr.vx.rpg.classes.Item.Rarity;
import fr.vx.rpg.classes.blocks.Events.ModdedBlockRightClicked;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.MultipleFacing;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Arrays;
import java.util.List;

public class ManaPool  implements Listener {
    private Item item = new Item(Material.STONE, "ManaPool", Rarity.UNCOMMON, 5000);
    public ManaPool() {
        Botania.LOGGER.info("Registering of ManaPool with id "+this.item.getIdentifier());
        RPG.plugin.getServer().getPluginManager().registerEvents(this, RPG.plugin);
    }
    public ItemStack getItemStack()
    {
        ItemStack item = new ItemStack(Material.CARROT_ON_A_STICK);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(this.item.getRarity().getColor()+"ManaPool");
        itemMeta.setLore(Arrays.asList("",Rarity.UNCOMMON.getDescription(), ChatColor.GOLD+""+5000+" pieces"));
        itemMeta.setCustomModelData(Integer.valueOf(this.item.getIdentifier()));

        item.setItemMeta(itemMeta);
        net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        NBTTagCompound itemCompound = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
        itemCompound.setString("custom_id", this.item.getIdentifier());
        itemCompound.setInt("mana", 400000);
        nmsItem.setTag(itemCompound);
        item = CraftItemStack.asBukkitCopy(nmsItem);
        return item;
    }

    public ItemStack getItemStackFromBlock(Block block)
    {
        ItemStack item = new ItemStack(Material.CARROT_ON_A_STICK);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(this.item.getRarity().getColor()+"ManaPool");
        itemMeta.setLore(Arrays.asList("",Rarity.UNCOMMON.getDescription(), ChatColor.GOLD+""+5000+" pieces"));
        itemMeta.setCustomModelData(Integer.valueOf(this.item.getIdentifier()));

        item.setItemMeta(itemMeta);
        net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        NBTTagCompound itemCompound = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
        itemCompound.setString("custom_id", this.item.getIdentifier());
        itemCompound.setInt("mana", getMana(block));
        nmsItem.setTag(itemCompound);
        item = CraftItemStack.asBukkitCopy(nmsItem);
        return item;
    }

    public void spawnArmorStand(ItemStack itemStack, Location location)
    {
        ArmorStand as = location.getWorld().spawn(location, ArmorStand.class);

        as.setGravity(false);
        as.setCanPickupItems(false);
        as.setVisible(false);
        LivingEntity entity = as;
        entity.getEquipment().setHelmet(itemStack);
    }

    public void setArmorStand(ItemStack itemStack, Block block)
    {
        List<Entity> nearbyEntities = (List<Entity>) block.getLocation().getWorld().getNearbyEntities(block.getLocation().add(0.5,0,0.5), 0.1, 0.1, 0.1);

        for(Entity e : block.getWorld().getEntities()){
            if(nearbyEntities.contains(e)){
                if(e.getType().equals(EntityType.ARMOR_STAND))
                {
                    LivingEntity entity = (LivingEntity) nearbyEntities.get(0);
                    entity.getEquipment().setHelmet(itemStack);
                }
            }
        }
    }

    public Item getItem()
    {
        return this.item;
    }

    public void applyBlock(org.bukkit.block.Block block, int mana) {

        block.setMetadata("custom_id", new FixedMetadataValue(RPG.plugin, item.getIdentifier()));
        block.setMetadata("mana", new FixedMetadataValue(RPG.plugin, mana));
    }

    public void resetBlock(org.bukkit.block.Block block) {

        block.setMetadata("custom_id", new FixedMetadataValue(RPG.plugin, null));
        block.setMetadata("mana", new FixedMetadataValue(RPG.plugin, null));
    }

    @EventHandler
    public void onModdedBlockPlaced(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
            if (Item.isSame(this.item, item)) {
                org.bukkit.block.Block block = event.getClickedBlock().getLocation().add(0, 1, 0).getBlock();

                if (block.hasMetadata("custom_id")) {
                    if (event.getPlayer().isSneaking()) {
                        block.setType(Material.MUSHROOM_STEM);
                        BlockData blockData = block.getBlockData();
                        MultipleFacing mf = (MultipleFacing) blockData;
                        mf.setFace(BlockFace.NORTH, false);
                        mf.setFace(BlockFace.SOUTH, false);
                        mf.setFace(BlockFace.EAST, false);
                        mf.setFace(BlockFace.WEST, false);
                        mf.setFace(BlockFace.UP, false);
                        mf.setFace(BlockFace.DOWN, true);
                        block.setBlockData(mf);
                        net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
                        NBTTagCompound itemCompound = nmsItem.getTag();
                        int mana = itemCompound.getInt("mana");

                        applyBlock(block, mana);
                        spawnArmorStand(item, block.getLocation().add(0.5, 0, 0.5));
                        event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BASALT_PLACE, 1.5F, 0.5F);
                        item.setAmount(item.getAmount() - 1);
                        event.getPlayer().updateInventory();
                        updateRendering(block);
                    }
                    BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
                    scheduler.scheduleSyncDelayedTask(RPG.getPlugin(RPG.class), new Runnable() {
                        @Override
                        public void run() {
                            Bukkit.getPluginManager().callEvent(new ModdedBlockRightClicked(event));
                        }
                    }, 20L);
                }
                block.setType(Material.MUSHROOM_STEM);
                BlockData blockData = block.getBlockData();
                MultipleFacing mf = (MultipleFacing) blockData;
                mf.setFace(BlockFace.NORTH, false);
                mf.setFace(BlockFace.SOUTH, false);
                mf.setFace(BlockFace.EAST, false);
                mf.setFace(BlockFace.WEST, false);
                mf.setFace(BlockFace.UP, false);
                mf.setFace(BlockFace.DOWN, true);
                block.setBlockData(mf);
                net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
                NBTTagCompound itemCompound = nmsItem.getTag();
                int mana = itemCompound.getInt("mana");

                applyBlock(block, mana);
                spawnArmorStand(item, block.getLocation().add(0.5, 0, 0.5));
                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BASALT_PLACE, 1.5F, 0.5F);
                item.setAmount(item.getAmount() - 1);
                event.getPlayer().updateInventory();
                updateRendering(block);
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        org.bukkit.block.Block block = event.getBlock();

        if (block.hasMetadata("custom_id") && block.getMetadata("custom_id").get(0).asString().equals(this.item.getIdentifier())) {
            Location BlockLocation = event.getBlock().getLocation();
            World world = BlockLocation.getWorld();
            List<Entity> nearbyEntities = (List<Entity>) BlockLocation.getWorld().getNearbyEntities(BlockLocation.add(0.5,0,0.5), 0.1, 0.1, 0.1);

            for(Entity e : world.getEntities())
                if(nearbyEntities.contains(e))
                    if(e.getType().equals(EntityType.ARMOR_STAND))
                        e.remove();


            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BASALT_BREAK, 1.5F, 0.5F);
            event.setDropItems(false);
            event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), getItemStackFromBlock(block));
            resetBlock(block);
        }
    }

    public void updateRendering(Block block)
    {
        if(block.hasMetadata("custom_id") && block.getMetadata("custom_id").get(0).asString().equals(this.item.getIdentifier()))
        {
            int mana = getMana(block);

            if(mana == 0)
                setArmorStand(BotaniaItems.MANAPOOL.getItemStack(), block);
            if(mana >= 1 && mana < 100000)
                setArmorStand(BotaniaItems.MANAPOOLITEM1.getItemStack(), block);
            if(mana >= 100000 && mana < 400000)
                setArmorStand(BotaniaItems.MANAPOOLITEM2.getItemStack(), block);
            if(mana >= 400000 && mana < 800000)
                setArmorStand(BotaniaItems.MANAPOOLITEM3.getItemStack(), block);
            if(mana >= 800000)
                setArmorStand(BotaniaItems.MANAPOOLITEM4.getItemStack(), block);
        }
    }

    public int getMana(Block block)
    {
        if(block.hasMetadata("mana"))
            return block.getMetadata("mana").get(0).asInt();
        return 0;
    }
}
