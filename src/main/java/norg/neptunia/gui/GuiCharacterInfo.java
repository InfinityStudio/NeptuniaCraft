package norg.neptunia.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import norg.neptunia.capability.CapProvider;
import norg.neptunia.capability.INepCapability;
import norg.neptunia.capability.ListCharactor;
import norg.neptunia.inventory.ContainerCharacterInfo;
import norg.neptunia.inventory.InventoryExtended;
import norg.neptunia.network.MessageOpenSC;
import norg.neptunia.proxy.CommonProxy;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by The Sea on 2016/4/18.
 */
public class GuiCharacterInfo extends GuiContainer {

    private final ResourceLocation guiCharaInfo = new ResourceLocation("neptunia", "textures/gui/charactor_info.png");
    private EntityPlayer player;

    public GuiCharacterInfo(EntityPlayer entityPlayer, InventoryPlayer inventoryPlayer, InventoryExtended inventoryExtended) {
        super(new ContainerCharacterInfo(entityPlayer, inventoryPlayer, inventoryExtended));
        this.player = entityPlayer;
        this.xSize = 246;
        this.ySize = 222;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fontRenderer.drawString("HP", 49, 11, 4210752);
        this.fontRenderer.drawString(player.getHealth() + "/" + player.getMaxHealth(), 152, 11, 4210752);
        this.fontRenderer.drawString("SP", 49, 35, 4210752);
        INepCapability pc = CapProvider.get(player);
        this.fontRenderer.drawString(pc.getSuperPower(pc.getStatue()) + "/" + pc.getMaxSP(pc.getStatue()), 152, 35, 4210752);
        this.fontRenderer.drawString(pc.getStatue(), 27, 58 ,4210752);
        this.fontRenderer.drawString("CPU", 117, 58, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(guiCharaInfo);
        this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, this.xSize, this.ySize);
        INepCapability pc = CapProvider.get(player);
        Iterator<ListCharactor.Information> iterator = pc.getListCharactor().charactorList.iterator();
        while (iterator.hasNext()) {
            ListCharactor.Information chara = iterator.next();
            if (chara.getName().equals(pc.getStatue())) {
                this.drawTexturedModalRect(guiLeft + 65, guiTop + 11, 0, 222, (int) (85 * player.getHealth() / player.getMaxHealth()), 16);
                this.drawTexturedModalRect(guiLeft + 65, guiTop + 35, 85, 222, 85 * pc.getSuperPower(pc.getStatue()) / pc.getMaxSP(pc.getStatue()), 16);
                this.mc.getTextureManager().bindTexture(chara.getResourceLocation());
                this.drawTexturedModalRect(guiLeft + 10, guiTop + 13, 0, 0, 35, 36);
                break;
            }
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if (mouseX <= guiLeft + 246 && mouseX >= guiLeft + 227 && mouseY <= guiTop + 21 && mouseY >= guiTop + 2 && mouseButton == 0) {
            CommonProxy.network.sendToServer(new MessageOpenSC());
        } else {
            super.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    @Override
    public void onGuiClosed() {
    }

}
