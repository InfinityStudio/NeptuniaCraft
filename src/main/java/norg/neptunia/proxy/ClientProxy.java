package norg.neptunia.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import norg.neptunia.block.BlockLoader;
import norg.neptunia.item.ItemLoader;

/**
 * Created by The Sea on 2016/6/10.
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        ClientRegistry.registerKeyBinding(EventHandler.keyShowSC);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }
}
