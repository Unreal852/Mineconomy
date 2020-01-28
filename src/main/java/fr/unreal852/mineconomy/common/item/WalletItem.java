package fr.unreal852.mineconomy.common.item;

import fr.unreal852.mineconomy.common.ModConstants;
import fr.unreal852.mineconomy.common.registry.ItemGroupRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class WalletItem extends Item
{
    public WalletItem()
    {
        super(new Item.Settings().group(ItemGroupRegistry.MINECONOMY_GROUP).maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
    {
        return super.use(world, user, hand);
    }
}
