package fr.unreal852.mineconomy.common.registry.registrables;

import fr.unreal852.mineconomy.common.ModConstants;
import fr.unreal852.mineconomy.common.ModLogger;
import fr.unreal852.mineconomy.common.registry.IModRegistrable;
import fr.unreal852.mineconomy.common.registry.ModRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlocksRegistrable<T extends Enum<T>> implements IModRegistrable<T>
{
    @Override
    public void onRegister(T enumValue)
    {
        Block block = ModRegistry.getRegistryElementValue(enumValue, "block", Block.class);
        Identifier identifier = ModRegistry.getRegistryElementValue(enumValue, "identifier", Identifier.class);
        Boolean hasItemBlock = ModRegistry.getRegistryElementValue(enumValue, "itemblock", Boolean.class);
        if (block == null || identifier == null)
        {
            ModLogger.LogError("Block '" + enumValue.name() + "' is missing block or identifier. Skipping Registration !");
            return;
        }
        Registry.register(Registry.BLOCK, identifier, block);
        ModLogger.LogInfo("Registered Block '" + identifier.getPath() + "'");
        if (hasItemBlock != null && hasItemBlock)
            Registry.register(Registry.ITEM, identifier, new BlockItem(block, new Item.Settings().group(ModConstants.MOD_ITEM_GROUP)));
    }
}
