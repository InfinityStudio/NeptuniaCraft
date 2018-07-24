package norg.neptunia.item;

import net.minecraft.item.Item;
import norg.neptunia.NeptuniaCraft;
import norg.neptunia.proxy.ClientProxy;

/**
 * Created by The Sea on 2016/6/12.
 */
public class ItemBasic extends Item {

    private String itemName;

    public ItemBasic(String name) {
        setRegistryName(name);
        setUnlocalizedName(name);
        this.itemName = name;
        setCreativeTab(NeptuniaCraft.nepTabs);
    }

    public String getName() {
        return this.itemName;
    }

}
