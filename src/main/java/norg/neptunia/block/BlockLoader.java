package norg.neptunia.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by The Sea on 2016/6/14.
 */
@Mod.EventBusSubscriber(modid = "neptunia")
public class BlockLoader {

    public static BlockBasic[] blockList = new BlockBasic[] {

    };

    public static BlockContainerBasic[] containerList = new BlockContainerBasic[] {
            //new BlockBeliefProvider(), new BlockDiscFactory()
    };

    public BlockLoader() {

    }

    @SubscribeEvent
    public static void registerBlock(RegistryEvent.Register<Block> event) {
        for (int i = 0; i < blockList.length; i++) {
            event.getRegistry().registerAll(blockList[i]);
        }
        for (int i = 0; i < containerList.length; i++) {
            event.getRegistry().registerAll(containerList[i]);
        }
    }

    @SubscribeEvent
    public static void registerRender(ModelRegistryEvent event) {
        for (int i = 0; i < blockList.length; i++) {
            registerRender(blockList[i]);
        }
        for (int i = 0; i < containerList.length; i++) {
            registerRender(containerList[i]);
        }
    }

    private static void registerRender(Block block) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }

}
