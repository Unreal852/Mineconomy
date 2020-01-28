package fr.unreal852.mineconomy.common.bank;

import fr.unreal852.ucorefabric.util.JavaUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;

public class MinecraftBank
{
    private static MinecraftBank s_instance;

    public static MinecraftBank getInstance()
    {
        if (s_instance == null)
            s_instance = new MinecraftBank();
        return s_instance;
    }

    private final String                             m_bankName     = "MineBank";
    private final Map<Integer, MinecraftBankAccount> m_bankAccounts = new HashMap<>();

    public MinecraftBank()
    {

    }

    public String getBankName()
    {
        return m_bankName;
    }

    public boolean accountExists(int accountID)
    {
        return m_bankAccounts.containsKey(accountID);
    }

    public Collection<MinecraftBankAccount> getAccounts()
    {
        return m_bankAccounts.values();
    }

    public MinecraftBankAccount findAccount(Predicate<MinecraftBankAccount> accountPredicate)
    {
        return JavaUtils.find(getAccounts(), accountPredicate);
    }

    public MinecraftBankAccount getAccountByID(int accountID)
    {
        if (!accountExists(accountID))
            return null;
        return m_bankAccounts.get(accountID);
    }

    public MinecraftBankAccount getAccountByOwnerUUID(UUID uuid)
    {
        return JavaUtils.find(getAccounts(), account -> account.getOwnerUUID() == uuid);
    }

    public MinecraftBankAccount getAccountByName(String name)
    {
        return JavaUtils.find(getAccounts(), account -> account.getAccountName().toLowerCase().equals(name));
    }

    public MinecraftBankAccount getAccountByOwnerName(String ownerName)
    {
        return JavaUtils.find(getAccounts(),account -> account.getAccountOwnerName().toLowerCase().equals(ownerName));
    }

    public MinecraftBankActionResult createAccount(MinecraftBankAccount account)
    {
        if (accountExists(account.getAccountID()))
            return new MinecraftBankActionResult(false, "Could not register account '" + account.getAccountID() + "' (Account ID already exists).");
        m_bankAccounts.put(account.getAccountID(), account);
        return new MinecraftBankActionResult(true, "Successfully registered account '" + account.getAccountID() + "'.");
    }

    public MinecraftBankActionResult deleteAccount(int accountID)
    {
        if (!accountExists(accountID))
            return new MinecraftBankActionResult(false, "Could not delete account '" + accountID + "' (Account ID does not exists).");
        m_bankAccounts.remove(accountID);
        return new MinecraftBankActionResult(true, "Successfully deleted account '" + accountID + "'.");
    }
}
