package fr.unreal852.mineconomy.common.registry;

public interface IModRegistrable<E extends Enum<E>>
{
    void onRegister(E element);
}
