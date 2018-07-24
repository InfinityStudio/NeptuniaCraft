package norg.neptunia;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import norg.neptunia.proxy.CommonProxy;

/**
 * Created by The Sea on 2016/3/19.
 */
@Mod(modid = "neptunia", name="Neptunia Craft", version = "1.0.0")
public class NeptuniaCraft {

    @SidedProxy(modId = "neptunia", serverSide = "norg.neptunia.proxy.CommonProxy", clientSide = "norg.neptunia.proxy.ClientProxy")
    public static CommonProxy proxy;

    @Mod.Instance("neptunia")
    public static NeptuniaCraft instance;

    public static CreativeTabs nepTabs = new CreativeTabs("neptunia") {
        @Override
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem() {
            return new ItemStack(Items.COMPARATOR);
        }
    };

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void Init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

}
