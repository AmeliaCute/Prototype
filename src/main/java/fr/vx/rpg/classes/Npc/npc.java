package fr.vx.rpg.classes.Npc;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import fr.vx.rpg.RPG;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftServer;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import java.util.List;
import java.util.UUID;

public class npc implements Listener
{
    private static WorldServer world;

    public npc(String name, String skinValue, String skinSignature ,Location location)
    {
        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer world = ((CraftWorld) location.getWorld()).getHandle();
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), name);
        gameProfile.getProperties().removeAll("textures");
        gameProfile.getProperties().put("textures", new Property("textures", skinValue, skinSignature));
        EntityPlayer npc = new EntityPlayer(server, world, gameProfile, new PlayerInteractManager(world));
        RPG.npcList.add(npc);

        npc.setLocation(location.getX(),location.getY(),location.getZ(), 90, 0);
        npc.getDataWatcher().set(new DataWatcherObject<>(16, DataWatcherRegistry.a), (byte)127);

        this.world = world;
        addNPCPackets(npc);
    }

    public static void addNPCPackets(EntityPlayer npc)
    {
        for(Player player : Bukkit.getOnlinePlayers())
        {
            PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
            connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
            connection.sendPacket(new PacketPlayOutEntityMetadata(npc.getId(), npc.getDataWatcher(), true));
            connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 256 / 360)));
        }
    }

    public static void addJoinPackets(Player player)
    {
        for(EntityPlayer npc : RPG.npcList)
        {
            PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
            connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
            connection.sendPacket(new PacketPlayOutEntityMetadata(npc.getId(), npc.getDataWatcher(), true));
            connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 256 / 360)));
        }
    }

    public static List<EntityPlayer> getNpcList()  {return RPG.npcList;}

    public static void RemoveAllNpc()
    {
        for(int i = 1; i < RPG.npcList.size(); i++)
        {
            world.removeEntity(RPG.npcList.get(i));
            removeNPCPacket(RPG.npcList.get(i));
            RPG.npcList.remove(i);
        }
    }

    public static void removeNPCPacket(EntityPlayer npc) {
        for(Player player : Bukkit.getOnlinePlayers())
        {
            PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
            connection.sendPacket(new PacketPlayOutEntityDestroy(npc.getId()));
            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, npc));
        }
    }

}
