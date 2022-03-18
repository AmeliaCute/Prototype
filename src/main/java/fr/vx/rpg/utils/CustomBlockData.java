package fr.vx.rpg.utils;

import org.bukkit.Chunk;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import java.util.Set;


public class CustomBlockData implements PersistentDataContainer {

   private final PersistentDataContainer pdc;
   private final Chunk chunk;
   private final NamespacedKey key;

   public CustomBlockData(final Block block, final Plugin plugin) {
       this.chunk = block.getChunk();
       this.key = new NamespacedKey(plugin, getOldKey(block));
       this.pdc = getPersistentDataContainer();
   }


   @Deprecated()
   public CustomBlockData(final Block block, final String namespace) {
       this.chunk = block.getChunk();
       this.key = new NamespacedKey(namespace, getOldKey(block));
       this.pdc = getPersistentDataContainer();
   }

   private static String getOldKey(Block block) {
       final int x = block.getX() & 0x000F;
       final int y = block.getY();
       final int z = block.getZ() & 0x000F;
       return String.format("x%dy%dz%d", x, y, z);
   }

   public void clear() {
       pdc.getKeys().forEach(pdc::remove);
       save();
   }

   private PersistentDataContainer getPersistentDataContainer() {
       final PersistentDataContainer chunkPDC = chunk.getPersistentDataContainer();
       final PersistentDataContainer blockPDC;
       if (chunkPDC.has(key, PersistentDataType.TAG_CONTAINER)) {
           blockPDC = chunkPDC.get(key, PersistentDataType.TAG_CONTAINER);
           assert blockPDC != null;
           return blockPDC;
       }
       blockPDC = chunkPDC.getAdapterContext().newPersistentDataContainer();
       return blockPDC;
   }

   private void save() {
       if (pdc.isEmpty()) {
           chunk.getPersistentDataContainer().remove(key);
       } else {
           chunk.getPersistentDataContainer().set(key, PersistentDataType.TAG_CONTAINER, pdc);
       }
   }

   @Override
   public <T, Z> void set(final NamespacedKey namespacedKey, final PersistentDataType<T, Z> persistentDataType, final Z z) {
       pdc.set(namespacedKey, persistentDataType, z);
       save();
   }

   @Override
   public <T, Z> boolean has(final  NamespacedKey namespacedKey, final  PersistentDataType<T, Z> persistentDataType) {
       return pdc.has(namespacedKey, persistentDataType);
   }

   
   @Override
   public <T, Z> Z get(final  NamespacedKey namespacedKey, final  PersistentDataType<T, Z> persistentDataType) {
       return pdc.get(namespacedKey, persistentDataType);
   }

   
   @Override
   public <T, Z> Z getOrDefault(final  NamespacedKey namespacedKey, final  PersistentDataType<T, Z> persistentDataType, final  Z z) {
       return pdc.getOrDefault(namespacedKey, persistentDataType, z);
   }

   
   @Override
   public Set<NamespacedKey> getKeys() {
       return pdc.getKeys();
   }

   @Override
   public void remove(final  NamespacedKey namespacedKey) {
       pdc.remove(namespacedKey);
       save();
   }

   @Override
   public boolean isEmpty() {
       return pdc.isEmpty();
   }

   
   @Override
   public PersistentDataAdapterContext getAdapterContext() {
       return pdc.getAdapterContext();
   }

   public static boolean[] MultiFacing(boolean north, boolean south, boolean east, boolean west, boolean up, boolean down)
   {
        boolean[] x = new boolean[6];x[0] = north;x[1] = south;x[2] = east;x[3] = west;x[4] = up;x[5] = down;return x;
   }
}
