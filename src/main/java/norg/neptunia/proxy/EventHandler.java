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
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import norg.neptunia.NeptuniaCraft;
import norg.neptunia.capability.CapProvider;
import norg.neptunia.capability.INepCapability;
import norg.neptunia.gui.GuiPlayerStatue;
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
    public void increaseEXE(AttackEntityEvent event) {
        if (!event.getEntityPlayer().getEntityWorld().isRemote) {
            INepCapability pc = CapProvider.get(event.getEntityPlayer());
            int curEXE = pc.getExEDriver(pc.getStatue()) + 1;
            if (curEXE > pc.getMaxExEDriver(pc.getStatue())) {
                if (pc.getEXELevel(pc.getStatue()) < 5) {
                    curEXE = 0;
                    pc.setEXELevel(pc.getStatue(), pc.getEXELevel(pc.getStatue()) + 1);
                } else {
                    curEXE = pc.getMaxExEDriver(pc.getStatue());
                }
            }
            pc.setExEDriver(pc.getStatue(), curEXE);
            pc.dataChanged(event.getEntityPlayer());
        }
    }

    @SubscribeEvent
    public void getExp(PlayerPickupXpEvent event) {
        INepCapability pc = CapProvider.get(event.getEntityPlayer());
        pc.setExp(pc.getStatue(), pc.getExp(pc.getStatue())+event.getOrb().xpValue);
        pc.updateLevel(pc.getStatue());
        pc.dataChanged(event.getEntityPlayer());
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void keyListener(InputEvent.KeyInputEvent event) {
        if (keyShowSC.isPressed()) {
            EntityPlayer player = Minecraft.getMinecraft().player;
            player.openGui(NeptuniaCraft.instance, GuiID.GUI_SELECTCHARACTER, player.world, (int)player.posX, (int)player.posY, (int)player.posZ);
        }
    }

    @SubscribeEvent
    public void onClonePlayer(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            CapProvider.get(event.getEntityPlayer()).loadNBTData(CapProvider.get(event.getOriginal()).saveNBTData(event.getEntityPlayer()), event.getEntityPlayer());
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
    public void AttachCapability(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof EntityPlayer)
            event.addCapability(new ResourceLocation("neptunia_epp"), new CapProvider());
    }
}
