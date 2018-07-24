package norg.neptunia.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import norg.neptunia.NeptuniaCraft;
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
        this.drawTexturedModalRect(0, 0, 0, 0, 116, 43);
        float i = mc.player.getHealth();
        float j = mc.player.getMaxHealth();
        this.drawTexturedModalRect(41, 2, 0, 43, (int)(72 * i / j), 10);
        INepCapability pc = CapProvider.get(mc.player);
        this.drawTexturedModalRect(41, 14, 0, 53, (70 * pc.getSuperPower(pc.getStatue()) / 1000), 10);
        Iterator<Information> iterator = pc.getListCharactor().charactorList.iterator();
        while (iterator.hasNext()) {
            Information chara = iterator.next();
            if (chara.getName().equals(pc.getStatue())) {
                mc.getTextureManager().bindTexture(chara.getResourceLocation());
                this.drawTexturedModalRect(3, 3, 0, 0, 35, 36);
                break;
            }
        }
        FontRenderer fontRenderer = mc.fontRenderer;
        fontRenderer.drawString((int)i + "/" + (int)j, 117, 3, 0xFFFFFF);
        fontRenderer.drawString(pc.getSuperPower(pc.getStatue()) + "/" + pc.getMaxSP(pc.getStatue()), 115, 15 ,0xFFFFFF);
        fontRenderer.drawString("statue:" + CapProvider.get(mc.player).getStatue(), 42, 27, 0xFFFFFF);
        //mc.getTextureManager().bindTexture(icons);
    }

}
