package fr.unreal852.mineconomy.common.bank;

public class MinecraftBankActionResult
{
    private boolean m_success;
    private String  m_message;

    public MinecraftBankActionResult(boolean success)
    {
        this(success, "");
    }

    public MinecraftBankActionResult(boolean success, String message)
    {
        m_success = success;
        m_message = message;
    }

    public boolean isSuccess()
    {
        return m_success;
    }

    public String getMessage()
    {
        return m_message;
    }
}
