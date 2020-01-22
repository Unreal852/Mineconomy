package fr.unreal852.mineconomy.common.registry;

import fr.unreal852.mineconomy.common.ModLogger;
import fr.unreal852.mineconomy.common.registry.annotations.ModRegistryElement;

import java.lang.reflect.Field;
import java.util.EnumSet;

public final class ModRegistry
{
    public static <E extends Enum<E>> void register(Class<E> enumClass)
    {
        if (enumClass == null)
            return;
        fr.unreal852.mineconomy.common.registry.annotations.ModRegistry modRegistry = enumClass.getAnnotation(fr.unreal852.mineconomy.common.registry.annotations.ModRegistry.class);
        if (modRegistry == null)
        {
            ModLogger.LogError("Couldn't register '" + enumClass.getSimpleName() + "'. Missing @ModRegistry Annotation.");
            return;
        }
        EnumSet<E> enumSet = EnumSet.allOf(enumClass);
        if (enumSet.isEmpty())
        {
            ModLogger.LogError("Couldn't register '" + enumClass.getSimpleName() + "'. Missing Enumeration values.");
            return;
        }
        try
        {
            IModRegistrable<E> registrable = modRegistry.Registrable().newInstance();
            for (E enumValue : enumSet)
                registrable.onRegister(enumValue);
        }
        catch (InstantiationException | IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }

    public static <E extends Enum<E>, R> R getRegistryElementValue(E enumValue, String registryElementName, Class<R> classType)
    {
        Object obj = getRegistryElementValue(enumValue, registryElementName);
        if (!classType.isInstance(obj))
            return null;
        return classType.cast(obj);
    }

    public static <E extends Enum<E>> Object getRegistryElementValue(E enumValue, String registryElementName)
    {
        if (enumValue == null || registryElementName == null || registryElementName.isEmpty())
            return null;
        for (Field field : enumValue.getDeclaringClass().getDeclaredFields())
        {
            if (field.isSynthetic())
                continue;
            ModRegistryElement registryElement = field.getAnnotation(ModRegistryElement.class);
            if (registryElement != null && registryElement.Name().equals(registryElementName))
            {
                if (!field.isAccessible())
                    field.setAccessible(true);
                try
                {
                    return field.get(enumValue);
                }
                catch (IllegalAccessException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
