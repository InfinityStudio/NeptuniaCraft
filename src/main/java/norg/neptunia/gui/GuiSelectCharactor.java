package norg.neptunia.gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import norg.neptunia.capability.CapProvider;
import norg.neptunia.capability.INepCapability;
import norg.neptunia.capability.ListCharactor.*;
import norg.neptunia.network.MessageOpenGui;
import norg.neptunia.proxy.CommonProxy;
import norg.neptunia.proxy.GuiID;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by The Sea on 2016/4/13.
 */
public class GuiSelectCharactor extends GuiScreen {

    private EntityPlayer player;
    private static final ResourceLocation guiSelChar = new ResourceLocation("neptunia", "textures/gui/select_charactor.png");

    public GuiSelectCharactor(EntityPlayer player) {
        this.player = player;
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.mc.getTextureManager().bindTexture(guiSelChar);
        int i = (this.width - 246) / 2;
        int j = (this.height - 222) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, 246, 222);
        INepCapability pc = CapProvider.get(this.player);
        Iterator<Information> iterator = pc.getListCharactor().charactorList.iterator();
        int k = 1;
        boolean b = false;
        while (iterator.hasNext()) {
            Information chara = iterator.next();
            if (chara.getName().equals(pc.getStatue())) { b = true; }
            this.mc.getTextureManager().bindTexture(chara.getResourceLocation());
            GlStateManager.color(1, 1, 1, 1);
            switch (k) {
                case 1: draw1(i, j, b, chara, pc); break;
                case 2: draw2(i, j, b, chara, pc); break;
                case 3: draw3(i, j, b, chara, pc); break;
                case 4: draw4(i, j, b, chara, pc); break;
                case 5: draw5(i, j, b, chara, pc); break;
                case 6: draw6(i, j, b, chara, pc); break;
                case 7: draw7(i, j, b, chara, pc); break;
                case 8: draw8(i, j, b, chara, pc); break;
            }
            b = false;
            k += 1;
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        INepCapability ps = CapProvider.get(this.player);
        int i = (this.width - 246) / 2;
        int j = (this.height - 222) / 2;
        try {
            if (mouseX <= i + 34 && mouseX >= i + 10 && mouseY <= j + 40 && mouseY >= j + 13 && mouseButton == 0) {
                ps.setStatue(ps.getListCharactor().charactorList.get(0).getName());
                ps.dataChanged(this.player);
            }
            if (mouseX <= i + 145 && mouseX >= i + 121 && mouseY <= j + 40 && mouseY >= j + 13 && mouseButton == 0) {
                ps.setStatue(ps.getListCharactor().charactorList.get(1).getName());
                ps.dataChanged(this.player);
            }
            if (mouseX <= i + 34 && mouseX >= i + 10 && mouseY <= j + 94 && mouseY >= j + 67 && mouseButton == 0) {
                ps.setStatue(ps.getListCharactor().charactorList.get(2).getName());
                ps.dataChanged(this.player);
            }
            if (mouseX <= i + 145 && mouseX >= i + 121 && mouseY <= j + 94 && mouseY >= j + 67 && mouseButton == 0) {
                ps.setStatue(ps.getListCharactor().charactorList.get(3).getName());
                ps.dataChanged(this.player);
            }
            if (mouseX <= i + 34 && mouseX >= i + 10 && mouseY <= j + 148 && mouseY >= j + 121 && mouseButton == 0) {
                ps.setStatue(ps.getListCharactor().charactorList.get(4).getName());
                ps.dataChanged(this.player);
            }
            if (mouseX <= i + 145 && mouseX >= i + 121 && mouseY <= j + 148 && mouseY >= j + 121 && mouseButton == 0) {
                ps.setStatue(ps.getListCharactor().charactorList.get(5).getName());
                ps.dataChanged(this.player);
            }
            if (mouseX <= i + 34 && mouseX >= i + 10 && mouseY <= j + 202 && mouseY >= j + 175 && mouseButton == 0) {
                ps.setStatue(ps.getListCharactor().charactorList.get(6).getName());
                ps.dataChanged(this.player);
            }
            if (mouseX <= i + 145 && mouseX >= i + 121 && mouseY <= j + 202 && mouseY >= j + 175 && mouseButton == 0) {
                ps.setStatue(ps.getListCharactor().charactorList.get(7).getName());
                ps.dataChanged(this.player);
            }
        } catch (Exception event) { }
        if (mouseX <= i + 246 && mouseX >= i + 227 && mouseY <= j + 42 && mouseY >= j + 23 && mouseButton ==0) {
            CommonProxy.network.sendToServer(new MessageOpenGui(GuiID.GUI_INVEXT));
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    private void draw1(int i, int j, boolean b, Information chara, INepCapability pc) {
        this.drawTexturedModalRect(i + 10, j + 13, 5, 4, 25, 28);
        if (b) this.drawTexturedModalRect(i + 7, j + 10, 0, 36, 31, 34);
        FontRenderer fontRenderer = this.mc.fontRenderer;
        fontRenderer.drawString(chara.getName(), i + 39, j + 10, 4210752);
        fontRenderer.drawString("Lv:" + pc.getLevel(chara.getName()), i + 39, j + 20, 4210752);
        fontRenderer.drawString("Exp:" + pc.getLevel(chara.getName()), i + 39, j + 30, 4210752);
        fontRenderer.drawString("SuperPower:" + pc.getSuperPower(chara.getName()), i + 39, j + 40, 4210752);
        fontRenderer.drawString("ExEDrive" + pc.getExEDriver(chara.getName()), i + 39, j + 50, 4210752);
    }

    private void draw2(int i, int j, boolean b, Information chara, INepCapability pc) {
        this.drawTexturedModalRect(i + 121, j + 13, 5, 4, 25, 28);
        if (b) this.drawTexturedModalRect(i + 118, j + 10, 0, 36, 31, 34);
        FontRenderer fontRenderer = this.mc.fontRenderer;
        fontRenderer.drawString(chara.getName(), i + 150, j + 10, 4210752);
        fontRenderer.drawString("Lv:" + pc.getLevel(chara.getName()), i + 150, j + 20, 4210752);
        fontRenderer.drawString("Exp:" + pc.getLevel(chara.getName()), i + 150, j + 30, 4210752);
        fontRenderer.drawString("SuperPower:" + pc.getSuperPower(chara.getName()), i + 150, j + 40, 4210752);
        fontRenderer.drawString("ExEDrive" + pc.getExEDriver(chara.getName()), i + 150, j + 50, 4210752);
    }

    private void draw3(int i, int j, boolean b, Information chara, INepCapability pc) {
        this.drawTexturedModalRect(i + 10, j + 67, 5, 4, 25, 28);
        if (b) this.drawTexturedModalRect(i + 7, j + 64, 0, 36, 31, 34);
        FontRenderer fontRenderer = this.mc.fontRenderer;
        fontRenderer.drawString(chara.getName(), i + 39, j + 64, 4210752);
        fontRenderer.drawString("Lv:" + pc.getLevel(chara.getName()), i + 39, j + 74, 4210752);
        fontRenderer.drawString("Exp:" + pc.getLevel(chara.getName()), i + 39, j + 84, 4210752);
        fontRenderer.drawString("SuperPower:" + pc.getSuperPower(chara.getName()), i + 39, j + 94, 4210752);
        fontRenderer.drawString("ExEDrive" + pc.getExEDriver(chara.getName()), i + 39, j + 104, 4210752);
    }

    private void draw4(int i, int j, boolean b, Information chara, INepCapability pc) {
        this.drawTexturedModalRect(i + 121, j + 67, 5, 4, 25, 28);
        if (b) this.drawTexturedModalRect(i + 118, j + 64, 0, 36, 31, 34);
        FontRenderer fontRenderer = this.mc.fontRenderer;
        fontRenderer.drawString(chara.getName(), i + 150, j + 64, 4210752);
        fontRenderer.drawString("Lv:" + pc.getLevel(chara.getName()), i + 150, j + 74, 4210752);
        fontRenderer.drawString("Exp:" + pc.getLevel(chara.getName()), i + 150, j + 84, 4210752);
        fontRenderer.drawString("SuperPower:" + pc.getSuperPower(chara.getName()), i + 150, j + 94, 4210752);
        fontRenderer.drawString("ExEDrive" + pc.getExEDriver(chara.getName()), i + 150, j + 104, 4210752);
    }

    private void draw5(int i, int j, boolean b, Information chara, INepCapability pc) {
        this.drawTexturedModalRect(i + 10, j + 121, 5, 4, 25, 28);
        if (b) this.drawTexturedModalRect(i + 7, j + 118, 0, 36, 31, 34);
        FontRenderer fontRenderer = this.mc.fontRenderer;
        fontRenderer.drawString(chara.getName(), i + 39, j + 118, 4210752);
        fontRenderer.drawString("Lv:" + pc.getLevel(chara.getName()), i + 39, j + 128, 4210752);
        fontRenderer.drawString("Exp:" + pc.getLevel(chara.getName()), i + 39, j + 138, 4210752);
        fontRenderer.drawString("SuperPower:" + pc.getSuperPower(chara.getName()), i + 39, j + 148, 4210752);
        fontRenderer.drawString("ExEDrive" + pc.getExEDriver(chara.getName()), i + 39, j + 158, 4210752);
    }

    private void draw6(int i, int j, boolean b, Information chara, INepCapability pc) {
        this.drawTexturedModalRect(i + 121, j + 121, 5, 4, 25, 28);
        if (b) this.drawTexturedModalRect(i + 118, j + 118, 0, 36, 31, 34);
        FontRenderer fontRenderer = this.mc.fontRenderer;
        fontRenderer.drawString(chara.getName(), i + 150, j + 118, 4210752);
        fontRenderer.drawString("Lv:" + pc.getLevel(chara.getName()), i + 150, j + 128, 4210752);
        fontRenderer.drawString("Exp:" + pc.getLevel(chara.getName()), i + 150, j + 138, 4210752);
        fontRenderer.drawString("SuperPower:" + pc.getSuperPower(chara.getName()), i + 150, j + 148, 4210752);
        fontRenderer.drawString("ExEDrive" + pc.getExEDriver(chara.getName()), i + 150, j + 158, 4210752);
    }

    private void draw7(int i, int j, boolean b, Information chara, INepCapability pc) {
        this.drawTexturedModalRect(i + 10, j + 175, 5, 4, 25, 28);
        if (b) this.drawTexturedModalRect(i + 7, j + 172, 0, 36, 31, 34);
        FontRenderer fontRenderer = this.mc.fontRenderer;
        fontRenderer.drawString(chara.getName(), i + 39, j + 172, 4210752);
        fontRenderer.drawString("Lv:" + pc.getLevel(chara.getName()), i + 39, j + 182, 4210752);
        fontRenderer.drawString("Exp:" + pc.getLevel(chara.getName()), i + 39, j + 192, 4210752);
        fontRenderer.drawString("SuperPower:" + pc.getSuperPower(chara.getName()), i + 39, j + 202, 4210752);
        fontRenderer.drawString("ExEDrive" + pc.getExEDriver(chara.getName()), i + 39, j + 212, 4210752);
    }

    private void draw8(int i, int j, boolean b, Information chara, INepCapability pc) {
        this.drawTexturedModalRect(i + 121, j + 175, 5, 4, 25, 28);
        if (b) this.drawTexturedModalRect(i + 118, j + 172, 0, 36, 31, 34);
        FontRenderer fontRenderer = this.mc.fontRenderer;
        fontRenderer.drawString(chara.getName(), i + 150, j + 172, 4210752);
        fontRenderer.drawString("Lv:" + pc.getLevel(chara.getName()), i + 150, j + 182, 4210752);
        fontRenderer.drawString("Exp:" + pc.getLevel(chara.getName()), i + 150, j + 192, 4210752);
        fontRenderer.drawString("SuperPower:" + pc.getSuperPower(chara.getName()), i + 150, j + 202, 4210752);
        fontRenderer.drawString("ExEDrive" + pc.getExEDriver(chara.getName()), i + 150, j + 212, 4210752);
    }
}
