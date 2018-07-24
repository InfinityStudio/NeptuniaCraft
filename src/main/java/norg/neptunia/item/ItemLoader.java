package norg.neptunia.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import norg.neptunia.item.disc.ItemDisc;
import norg.neptunia.item.disc.ItemIdeaChipB;
import norg.neptunia.item.disc.ItemIdeaChipR;
import norg.neptunia.item.disc.ItemIdeaChipY;
import norg.neptunia.item.weapon.*;

/**
 * Created by The Sea on 2016/6/12.
 */
@Mod.EventBusSubscriber(modid = "neptunia")
public class ItemLoader {

    public static ItemBasic[] itemList = new ItemBasic[] {
            new ItemDisc(), new ItemIdeaChipB(), new ItemIdeaChipR(), new ItemIdeaChipY()
    };

    public static ItemBasic[] weaponList = new ItemBasic[] {
            new ItemSwordNeptune(), new ItemKnifeNoire(), new ItemAxeBlanc(), new ItemGunVert(),
            new ItemSwordNepgear()
    };

    public ItemLoader() { }

    @SubscribeEvent
    public static void registerItem(RegistryEvent.Register<Item> event) {
        for (int i = 0; i < itemList.length; i++) {
            event.getRegistry().registerAll(itemList[i]);
        }
        for (int i = 0; i < weaponList.length; i++) {
            event.getRegistry().registerAll(weaponList[i]);
        }
    }

    @SubscribeEvent
    public static void registerRender(ModelRegistryEvent event) {
        for (int i = 0; i < itemList.length; i++) {
            registerRender(itemList[i]);
        }
        for (int i = 0; i < weaponList.length; i++) {
            registerRender(weaponList[i]);
        }
    }

    public static void registerRender(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

}
