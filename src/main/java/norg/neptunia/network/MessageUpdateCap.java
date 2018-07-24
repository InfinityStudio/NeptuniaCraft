package norg.neptunia.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import norg.neptunia.capability.CapProvider;

/**
 * Created by The Sea on 2016/6/12.
 */
public class MessageUpdateCap implements IMessage {

    private NBTTagCompound props;

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
            IThreadListener mainThread = (ctx.side.isClient())? Minecraft.getMinecraft() : (WorldServer) ctx.getServerHandler().player.world;
            mainThread.addScheduledTask(new Runnable() {
                @Override
                public void run() {
                    System.out.println(String.format("%s received update from server", Minecraft.getMinecraft().player.getName()));
                    System.out.println("running on client");
                    CapProvider.get(Minecraft.getMinecraft().player).loadNBTData(message.props);
                }
            });
            return null;
        }
    }
}
