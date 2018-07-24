package norg.neptunia.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import norg.neptunia.capability.CapProvider;
import norg.neptunia.gui.GuiCharacterInfo;
import norg.neptunia.inventory.ContainerCharacterInfo;

/**
 * Created by The Sea on 2016/5/3.
 */
public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GuiID.GUI_INVEXT) {
            return new ContainerCharacterInfo(player, player.inventory, CapProvider.get(player).getInventory(CapProvider.get(player).getStatue()));
        //} else if (ID == GuiID.GUI_BELIEF) {
        //    return new ContainerBeliefProvider(player.inventory, (IInventory) world.getTileEntity(new BlockPos(x, y, z)));
        } else {
            return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GuiID.GUI_INVEXT) {
            return new GuiCharacterInfo(player, player.inventory, CapProvider.get(player).getInventory(CapProvider.get(player).getStatue()));
        //} else if (ID == GuiID.GUI_BELIEF) {
            //return new GuiBeliefProvider(player, player.inventory, (IInventory) world.getTileEntity(new BlockPos(x, y, z)));
        } else {
                return null;
        }
    }
}
