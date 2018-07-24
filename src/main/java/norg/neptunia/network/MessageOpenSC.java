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
import norg.neptunia.proxy.GuiID;

/**
 * Created by The Sea on 2016/6/13.
 */
public class MessageOpenSC implements IMessage{

    private int id;

    public MessageOpenSC() {}

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    public static class OpenSCHandler implements IMessageHandler<MessageOpenSC, IMessage> {
        @Override
        public IMessage onMessage( final MessageOpenSC message, final MessageContext ctx) {
            IThreadListener mainThread = (ctx.side.isClient())? Minecraft.getMinecraft() : (WorldServer) ctx.getServerHandler().player.world;
            final EntityPlayer player = ctx.getServerHandler().player;
            mainThread.addScheduledTask(new Runnable() {
                @Override
                public void run() {
                    Minecraft.getMinecraft().displayGuiScreen(new GuiSelectCharactor(player));
                }
            });
            return null;
        }
    }
}
