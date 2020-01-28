package fr.unreal852.mineconomy.common.block;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;

public class CreditCardPaymentBlock extends Block
{
    public CreditCardPaymentBlock()
    {
        super(FabricBlockSettings.of(Material.STONE).build());
    }
}
