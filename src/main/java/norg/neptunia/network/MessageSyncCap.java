package norg.neptunia.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import norg.neptunia.capability.CapProvider;
import norg.neptunia.capability.INepCapability;

public class MessageSyncCap implements IMessage {

    public NBTTagCompound props;

    public MessageSyncCap() {

    }

    public MessageSyncCap(NBTTagCompound compound) {
        this.props = compound;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        props = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, props);
    }

    public static class SyncCapsHandler implements IMessageHandler<MessageSyncCap, IMessage> {
        @Override
        public IMessage onMessage(final MessageSyncCap message, final MessageContext ctx) {
            if (ctx.side == Side.SERVER) {
                final NBTBase props = message.props.getTag("Nep!");
                IThreadListener mainThread = (WorldServer)ctx.getServerHandler().player.world;
                mainThread.addScheduledTask(new Runnable() {
                    @Override
                    public void run() {
                        EntityPlayer player = ctx.getServerHandler().player;
                        if (player.hasCapability(CapProvider.NEP_CAP, null)) {
                            INepCapability nepCapability = player.getCapability(CapProvider.NEP_CAP, null);
                            Capability.IStorage<INepCapability> storage = CapProvider.NEP_CAP.getStorage();
                            storage.readNBT(CapProvider.NEP_CAP, nepCapability, null, props);
                        }
                    }
                });
            }
            return null;
        }
    }

}
