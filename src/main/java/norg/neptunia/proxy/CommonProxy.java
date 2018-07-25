package norg.neptunia.proxy;

import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import norg.neptunia.NeptuniaCraft;
import norg.neptunia.block.BlockLoader;
import norg.neptunia.capability.INepCapability;
import norg.neptunia.capability.NeptuniaCapability;
import norg.neptunia.item.ItemLoader;
import norg.neptunia.network.MessageUpdateCap;

/**
 * Created by The Sea on 2016/3/19.
 */
public class CommonProxy {

    public static SimpleNetworkWrapper network;

    public void preInit(FMLPreInitializationEvent event) {
        CapabilityManager.INSTANCE.register(INepCapability.class, NeptuniaCapability.CapStorage.capStorge, NeptuniaCapability.class);
        registerMessenger();;
        new ItemLoader();
        new BlockLoader();
    }

    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(NeptuniaCraft.instance, new GuiHandler());
        new EventHandler();
    }

    public void postInit(FMLPostInitializationEvent event) { }

    protected void registerMessenger() {
        int messageCount = 0;
        network = NetworkRegistry.INSTANCE.newSimpleChannel("neptunia_messenger");
        network.registerMessage(MessageUpdateCap.UpdateCapsHandler.class, MessageUpdateCap.class, messageCount++, Side.CLIENT);
    }
}
