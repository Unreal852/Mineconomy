package fr.unreal852.mineconomy.common.items;

import fr.unreal852.mineconomy.ModConstants;
import fr.unreal852.mineconomy.ModLogger;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ItemBankCheck extends Item
{
    public ItemBankCheck()
    {
        super(new Item.Settings().group(ModConstants.MOD_ITEM_GROUP));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
    {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient && stack.getItem() instanceof ItemBankCheck)
        {
            ContainerProviderRegistry.INSTANCE.openContainer(ModConstants.GUI_ID_BANK_CHECK, user, (buffer) ->
            {
                buffer.writeItemStack(stack);
            });
        }
        return super.use(world, user, hand);
    }
}
