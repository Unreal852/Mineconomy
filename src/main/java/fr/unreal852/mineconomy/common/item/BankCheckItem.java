package fr.unreal852.mineconomy.common.item;

import fr.unreal852.mineconomy.common.ModEntryCommon;
import fr.unreal852.mineconomy.common.ModHelper;
import fr.unreal852.mineconomy.common.registry.ScreenRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class BankCheckItem extends Item
{
    public BankCheckItem()
    {
        super(new Item.Settings().maxCount(1));
    }

    @Environment(EnvType.CLIENT)
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
    {
        ItemStack stack = user.getStackInHand(hand);
        if (world.isClient && stack.getItem() instanceof BankCheckItem)
            ModEntryCommon.PROXY.openScreen(ScreenRegistry.BANK_CHECK_GUI, stack);
        return super.use(world, user, hand);
    }
}
