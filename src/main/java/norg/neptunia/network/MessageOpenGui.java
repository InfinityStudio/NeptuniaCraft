package norg.neptunia.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import norg.neptunia.NeptuniaCraft;
import norg.neptunia.gui.GuiSelectCharactor;

/**
 * Created by The Sea on 2016/6/14.
 */
public class MessageOpenGui implements IMessage {

    private int id;

    public MessageOpenGui() {}

    public MessageOpenGui(int id) {
        this.id = id;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        id = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(id);
    }

    public static class OpenGuiHandler implements IMessageHandler<MessageOpenGui, IMessage> {
        @Override
        public IMessage onMessage(final MessageOpenGui message, final MessageContext ctx) {
            IThreadListener mainThread = (ctx.side.isClient())? Minecraft.getMinecraft() : (WorldServer) ctx.getServerHandler().player.world;
            final EntityPlayer player = ctx.getServerHandler().player;
            mainThread.addScheduledTask(new Runnable() {
                @Override
                public void run() {
                    player.openGui(NeptuniaCraft.instance, message.id, ctx.getServerHandler().player.world, (int) player.posY, (int) player.posY, (int) player.posZ);
                }
            });
            return null;
        }
    }

}
