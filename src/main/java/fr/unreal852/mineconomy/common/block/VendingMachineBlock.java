package fr.unreal852.mineconomy.common.block;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;

public class VendingMachineBlock extends Block
{
    public VendingMachineBlock()
    {
        super(FabricBlockSettings.of(Material.STONE).build());
    }
}
