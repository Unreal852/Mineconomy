package fr.unreal852.mineconomy.common.registry.registrables;

import fr.unreal852.mineconomy.common.ModLogger;
import fr.unreal852.mineconomy.common.registry.IModRegistrable;
import fr.unreal852.mineconomy.common.registry.ModRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemsRegistrable<T extends Enum<T>> implements IModRegistrable<T>
{
    @Override
    public void onRegister(T enumValue)
    {
        Item item = ModRegistry.getRegistryElementValue(enumValue, "item", Item.class);
        Identifier identifier = ModRegistry.getRegistryElementValue(enumValue, "identifier", Identifier.class);
        if(item == null || identifier == null)
        {
            ModLogger.LogError("Item '" + enumValue.name() + "' is missing item or identifier. Skipping Registration !");
            return;
        }
        Registry.register(Registry.ITEM, identifier, item);
        ModLogger.LogInfo("Registered Item '" + identifier.getPath() + "'");
    }
}
