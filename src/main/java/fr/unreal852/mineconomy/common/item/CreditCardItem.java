package fr.unreal852.mineconomy.common.item;

import fr.unreal852.mineconomy.common.ModConstants;
import fr.unreal852.mineconomy.common.ModLogger;
import fr.unreal852.mineconomy.common.registry.ItemGroupRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;
import java.util.concurrent.ThreadLocalRandom;

public class CreditCardItem extends Item
{
    public CreditCardItem()
    {
        super(new Item.Settings().group(ItemGroupRegistry.MINECONOMY_GROUP));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
    {
        ItemStack stack = user.getStackInHand(hand);
        if(!(stack.getItem() instanceof CreditCardItem))
            return new TypedActionResult<>(ActionResult.FAIL, stack);
        CompoundTag tag = stack.getOrCreateSubTag(ModConstants.MOD_ID);
        if(tag.contains("code"))
            ModLogger.Log(Level.INFO, "CODE: " + tag.getString("code"));
        else
            tag.putString("code", String.valueOf(ThreadLocalRandom.current().nextInt(1000, 9999)));
        return new TypedActionResult<>(ActionResult.PASS, stack);
    }
}
