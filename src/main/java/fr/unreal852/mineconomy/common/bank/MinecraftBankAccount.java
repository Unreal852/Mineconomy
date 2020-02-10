package fr.unreal852.mineconomy.common.bank;

import java.util.UUID;

public class MinecraftBankAccount
{
    private UUID    m_ownerUUID;
    private String  m_accountName;
    private String  m_accountOwnerName;
    private String  m_accountCode;
    private int     m_accountID;
    private double  m_accountBalance            = 0.0D;
    private double  m_paymentCeiling            = 250.0;
    private boolean m_contactlessPaymentEnabled = false;
    private boolean m_accountLocked             = false;

    public MinecraftBankAccount(int accountID, String accountName, String accountCode, String accountOwnerName)
    {
        m_accountID = accountID;
        m_accountName = accountName;
        m_accountCode = accountCode;
        m_accountOwnerName = accountOwnerName;
    }

    public UUID getOwnerUUID()
    {
        return m_ownerUUID;
    }

    public String getAccountName()
    {
        return m_accountName;
    }

    public String getAccountOwnerName()
    {
        return m_accountOwnerName;
    }

    public String getAccountCode()
    {
        return m_accountCode;
    }

    public int getAccountID()
    {
        return m_accountID;
    }

    public double getAccountBalance()
    {
        return m_accountBalance;
    }

    public double getPaymentCeiling()
    {
        return m_paymentCeiling;
    }

    public boolean isContactlessPayementEnabled()
    {
        return m_contactlessPaymentEnabled;
    }

    public boolean isAccountLocked()
    {
        return m_accountLocked;
    }
}
