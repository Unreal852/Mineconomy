package fr.unreal852.mineconomy.common.items;

import fr.unreal852.mineconomy.ModConstants;
import fr.unreal852.mineconomy.common.ModEntryCommon;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ItemBankCheckbook extends Item
{
    public ItemBankCheckbook()
    {
        super(new Item.Settings().group(ModConstants.MOD_ITEM_GROUP));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
    {
        ItemStack stack = user.getStackInHand(hand);
        if (world.isClient && stack.getItem() instanceof ItemBankCheckbook)
            ModEntryCommon.PROXY.openCheckGUI(stack);
        return super.use(world, user, hand);
    }
}
