package fr.unreal852.mineconomy.common.items;

import fr.unreal852.mineconomy.common.ModConstants;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ItemWallet extends Item
{
    public ItemWallet()
    {
        super(new Item.Settings().group(ModConstants.MOD_ITEM_GROUP).maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
    {
        return super.use(world, user, hand);
    }
}
