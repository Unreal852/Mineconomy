package fr.unreal852.mineconomy.common.test;

public interface IModRegistrable<T extends Enum>
{
    void onRegister(T element);
}
