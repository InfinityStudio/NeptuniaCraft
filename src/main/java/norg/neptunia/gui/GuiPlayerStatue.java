package norg.neptunia.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import norg.neptunia.capability.CapProvider;
import norg.neptunia.capability.INepCapability;
import norg.neptunia.capability.ListCharactor.*;

import java.util.Iterator;

/**
 * Created by The Sea on 2016/3/21.
 */
public class GuiPlayerStatue extends Gui{

    private final ResourceLocation guiStatue = new ResourceLocation("neptunia", "textures/gui/statue.png");

    public void drawStatue() {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        Minecraft mc = Minecraft.getMinecraft();
        mc.getTextureManager().bindTexture(guiStatue);
        this.drawTexturedModalRect(0, 0, 0, 0, 125, 49);
        float i = mc.player.getHealth();
        float j = mc.player.getMaxHealth();
        this.drawTexturedModalRect(55, 17, 0, 49, (int)(68 * i / j), 6);
        INepCapability pc = CapProvider.get(mc.player);
        this.drawTexturedModalRect(55, 29, 0, 55, (68 * pc.getSuperPower(pc.getStatue()) / pc.getMaxSP(pc.getStatue())), 6);
        int exeAccess = 40*pc.getExEDriver(pc.getStatue())/pc.getMaxExEDriver(pc.getStatue());
        this.drawTexturedModalRect(1, 41-exeAccess, 0, 61, 3, exeAccess);
        Iterator<Information> iterator = pc.getListCharactor().charactorList.iterator();
        while (iterator.hasNext()) {
            Information chara = iterator.next();
            if (chara.getName().equals(pc.getStatue())) {
                mc.getTextureManager().bindTexture(chara.getResourceLocation());
                this.drawTexturedModalRect(7, 3, 0, 0, 35, 36);
                break;
            }
        }
        FontRenderer fontRenderer = mc.fontRenderer;
        String tmp = (int)i + "/" + (int)j;
        fontRenderer.drawString(tmp, 122-tmp.length()*4, 17, 0xFFFFFF);
        fontRenderer.drawString("HP", 46, 16, 0xFFFFFF);
        tmp = pc.getSuperPower(pc.getStatue()) + "/" + pc.getMaxSP(pc.getStatue());
        fontRenderer.drawString(tmp, 122-tmp.length()*4, 29 ,0xFFFFFF);
        fontRenderer.drawString("SP", 46, 28, 0xFFFFFF);
        fontRenderer.drawString(pc.getStatue(), 48, 4, 0xFFFFFF);
        fontRenderer.drawString(Integer.toString(pc.getEXELevel(pc.getStatue())), 22, 41, 0xFFFFFF);
        mc.getTextureManager().bindTexture(Gui.ICONS);
    }

}
