package norg.neptunia.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import norg.neptunia.capability.CapProvider;
import norg.neptunia.capability.INepCapability;
import norg.neptunia.capability.NeptuniaCapability;
import norg.neptunia.gui.GuiPlayerStatue;
import norg.neptunia.gui.GuiSelectCharactor;
import norg.neptunia.network.MessageOpenSC;
import org.lwjgl.input.Keyboard;

/**
 * Created by The Sea on 2016/6/12.
 */
public class EventHandler {

    public static final KeyBinding keyShowSC = new KeyBinding("keyShowSC", Keyboard.KEY_U, "Nep");

    public EventHandler() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void cancelHealth(RenderGameOverlayEvent.Pre event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.HEALTH) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void playerStatue(RenderGameOverlayEvent event) {
        GuiPlayerStatue playerStatue = new GuiPlayerStatue();
        playerStatue.drawStatue();
    }


    @SubscribeEvent
    public void increaseSP(AttackEntityEvent event) {
        if (!event.getEntityPlayer().getEntityWorld().isRemote) {
            INepCapability pc = CapProvider.get(event.getEntityPlayer());
            pc.setSuperPower(pc.getStatue(), pc.getSuperPower(pc.getStatue()) + 1);
            pc.dataChanged(event.getEntityPlayer());
        }
    }

    @SubscribeEvent
    public void keyListener(InputEvent.KeyInputEvent event) {
        if (keyShowSC.isPressed()) {
            CommonProxy.network.sendToServer(new MessageOpenSC());
        }
    }

    @SubscribeEvent
    public void onClonePlayer(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            CapProvider.get(event.getEntityPlayer()).loadNBTData(CapProvider.get(event.getOriginal()).saveNBTData());
            CapProvider.get(event.getEntityPlayer()).dataChanged(event.getEntityPlayer());
        }
    }

    @SubscribeEvent
    public void entityJoinWorld(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof EntityPlayerMP && !event.getEntity().getEntityWorld().isRemote) {
            CapProvider.get((EntityPlayerMP) event.getEntity()).dataChanged((EntityPlayerMP) event.getEntity());
        }
    }

    @SubscribeEvent
    public void AttachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if(event.getObject() instanceof EntityPlayer)
            event.addCapability(new ResourceLocation("neptunia_epp"), new CapProvider(new NeptuniaCapability()));
    }
}
