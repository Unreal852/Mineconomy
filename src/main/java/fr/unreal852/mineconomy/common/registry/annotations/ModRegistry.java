package fr.unreal852.mineconomy.common.registry.annotations;

import fr.unreal852.mineconomy.common.registry.IModRegistrable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ModRegistry
{
    Class<? extends IModRegistrable> Registrable();
}
