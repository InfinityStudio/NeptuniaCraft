package norg.neptunia.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import norg.neptunia.NeptuniaCraft;

/**
 * Created by The Sea on 2016/6/15.
 */
public class BlockContainerBasic extends BlockContainer {

    private String blockName;

    protected BlockContainerBasic(String name) {
        super(Material.ROCK);
        setUnlocalizedName(name);
        setCreativeTab(NeptuniaCraft.nepTabs);
        this.blockName = name;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return null;
    }

    public String getBlockName() {
        return this.blockName;
    }
}
