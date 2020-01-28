package fr.unreal852.ucorefabric.registry;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.network.PacketConsumer;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

public final class ModRegistry
{
    public static <T extends Block> T registerBlock(Identifier identifier, T block)
    {
        return registerBlock(identifier, block, (BlockItem) null);
    }

    public static <T extends Block> T registerBlock(Identifier identifier, T block, Item.Settings settings)
    {
        return registerBlock(identifier, block, new BlockItem(block, settings));
    }

    public static <T extends Block> T registerBlock(Identifier identifier, T block, ItemGroup itemGroup)
    {
        return registerBlock(identifier, block, new BlockItem(block, new Item.Settings().group(itemGroup)));
    }

    public static <T extends Block> T registerBlock(Identifier identifier, T block, BlockItem blockItem)
    {
        Registry.register(Registry.BLOCK, identifier, block);
        if (blockItem != null)
            Registry.register(Registry.ITEM, identifier, blockItem);
        return block;
    }

    public static <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(Identifier identifier, Supplier<T> supplier, Block... supportedBlocks)
    {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, identifier, BlockEntityType.Builder.create(supplier, supportedBlocks).build(null));
    }

    public static <T extends Item> T registerItem(Identifier identifier, T item)
    {
        return Registry.register(Registry.ITEM, identifier, item);
    }

    public static <T extends Identifier> void registerServerPacket(Identifier identifier, PacketConsumer packetConsumer)
    {
        ServerSidePacketRegistry.INSTANCE.register(identifier, packetConsumer);
    }

    public static ItemGroup registerItemGroup(Identifier identifier, Supplier<ItemStack> stackSupplier)
    {
        return FabricItemGroupBuilder.build(identifier, stackSupplier);
    }

    @Environment(EnvType.CLIENT)
    public static <T extends Identifier> void registerClientPacket(Identifier identifier, PacketConsumer packetConsumer)
    {
        ClientSidePacketRegistry.INSTANCE.register(identifier, packetConsumer);
    }
}
