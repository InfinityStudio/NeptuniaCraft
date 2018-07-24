package norg.neptunia.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import norg.neptunia.NeptuniaCraft;

/**
 * Created by The Sea on 2016/6/14.
 */
public class BlockBasic extends Block {

    private String blockName;

    public BlockBasic(String name) {
        super(Material.ROCK, MapColor.SNOW);
        setCreativeTab(NeptuniaCraft.nepTabs);
        setRegistryName(name);
        setUnlocalizedName(name);
        this.blockName = name;
    }

    public String getBlockName() {
        return this.blockName;
    }

}
