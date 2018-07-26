package norg.neptunia.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import norg.neptunia.capability.CapProvider;
import norg.neptunia.capability.INepCapability;

/**
 * Created by The Sea on 2016/6/12.
 */
public class MessageUpdateCap implements IMessage {

    public NBTTagCompound props;

    public MessageUpdateCap() {}

    public MessageUpdateCap(NBTTagCompound nbtTagCompound) {
        this.props = nbtTagCompound;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        props = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, props);
    }

    public static class UpdateCapsHandler implements IMessageHandler<MessageUpdateCap, IMessage> {
        @Override
        public IMessage onMessage( final MessageUpdateCap message, final MessageContext ctx) {
            if (ctx.side == Side.CLIENT) {
                final NBTBase props = message.props.getTag("Nep!");
                Minecraft.getMinecraft().addScheduledTask(new Runnable() {
                    @Override
                    public void run() {
                        EntityPlayer player = Minecraft.getMinecraft().player;
                        if (player.hasCapability(CapProvider.NEP_CAP, null)) {
                            INepCapability nepCapability = player.getCapability(CapProvider.NEP_CAP, null);
                            Capability.IStorage<INepCapability> storage = CapProvider.NEP_CAP.getStorage();
                            storage.readNBT(CapProvider.NEP_CAP, nepCapability, null, props);
                        }
                    }
                });
            }
            /*IThreadListener mainThread = (ctx.side.isClient())? Minecraft.getMinecraft() : (WorldServer) ctx.getServerHandler().player.world;
            mainThread.addScheduledTask(new Runnable() {
                @Override
                public void run() {
                    CapProvider.get(Minecraft.getMinecraft().player).loadNBTData(message.props);
                }
            });*/
            return null;
        }
    }
}
