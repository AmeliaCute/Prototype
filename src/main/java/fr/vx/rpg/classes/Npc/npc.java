package fr.vx.rpg.classes.Npc;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftServer;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class npc implements Listener
{
    private static List<EntityPlayer> npcList = new ArrayList<EntityPlayer>();
    private static WorldServer world;

    public npc(String name, String skin ,Location location)
    {
        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer world = ((CraftWorld) location.getWorld()).getHandle();
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), ChatColor.BOLD+name);
        EntityPlayer npc = new EntityPlayer(server, world, gameProfile, new PlayerInteractManager(world));
        gameProfile.getProperties().put("textures", new Property("textures", Base64Coder.encodeString("{textures:{SKIN:{url:\"" + skin + "\"}}}")));
        npc.setLocation(location.getX(),location.getY(),location.getZ(), 0, 90);
        npc.isCreative();
        npcList.add(npc);

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
            connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 256 / 360)));
        }
    }

    public static void addJoinPackets(Player player)
    {
        for(EntityPlayer npc : npcList)
        {
            PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
            connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
            connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 256 / 360)));
        }
    }

    public static List<EntityPlayer> getNpcList()  {return npcList;}

    public static void RemoveAllNpc()
    {
        for(int i = npcList.size(); i > 0; i--)
        {
            world.removeEntity(npcList.get(i));
            npcList.remove(i);
        }
    }

}
