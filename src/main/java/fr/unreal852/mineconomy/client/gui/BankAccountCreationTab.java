package fr.unreal852.mineconomy.client.gui;


import fr.unreal852.mineconomy.common.ModUtils;
import fr.unreal852.mineconomy.common.registry.PacketRegistry;
import fr.unreal852.ucorefabric.util.JavaUtils;
import io.netty.buffer.Unpooled;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.PacketByteBuf;
import spinnery.widget.*;

@Environment(EnvType.CLIENT)
public class BankAccountCreationTab
{
    private WTabHolder.WTab m_tab;
    private WStaticText     m_textAccountName;
    private WStaticText     m_textAccountID;
    private WStaticText     m_textAccountSecretCode;
    private WStaticText     m_textAccountOwnerName;
    private WDynamicText    m_fieldAccountName;
    private WDynamicText    m_fieldAccountID;
    private WDynamicText    m_fieldAccountSecretCode;
    private WDynamicText    m_fieldAccountOwnerName;
    private WButton         m_buttonAccountID;
    private WButton         m_buttonCreateAccount;

    public BankAccountCreationTab(WTabHolder.WTab tab)
    {
        m_tab = tab;
        TranslatableText accountNameTranslation = new TranslatableText("gui.mineconomy.gui_bank_management_account_creation_account_name");
        TranslatableText accountIDTranslation = new TranslatableText("gui.mineconomy.gui_bank_management_account_creation_account_id");
        TranslatableText accountSecretCodeTranslation = new TranslatableText("gui.mineconomy.gui_bank_management_account_creation_account_code");
        TranslatableText accountOwnerNameTranslation = new TranslatableText("gui.mineconomy.gui_bank_management_account_creation_account_owner_name");
        TranslatableText accountGenerateIDTranslation = new TranslatableText("gui.mineconomy.gui_bank_management_account_creation_account_id_generator");
        TranslatableText accountCreateTranslation = new TranslatableText("gui.mineconomy.gui_bank_management_account_creation_validate");
        WInterface wInterface = m_tab.getToggle().getInterface();
        m_textAccountName = new WStaticText(GUIHelper.getPosition(0, 0), wInterface, accountNameTranslation);
        m_textAccountID = new WStaticText(GUIHelper.getPosition(0, 0), wInterface, accountIDTranslation);
        m_textAccountSecretCode = new WStaticText(GUIHelper.getPosition(0, 0), wInterface, accountSecretCodeTranslation);
        m_textAccountOwnerName = new WStaticText(GUIHelper.getPosition(0, 0), wInterface, accountOwnerNameTranslation);
        m_fieldAccountName = new WDynamicText(GUIHelper.getPosition(0, 0), WSize.of(0, 0), wInterface);
        m_fieldAccountID = new WDynamicText(GUIHelper.getPosition(0, 0), WSize.of(0, 0), wInterface);
        m_fieldAccountSecretCode = new WDynamicText(GUIHelper.getPosition(0, 0), WSize.of(0, 0), wInterface);
        m_fieldAccountOwnerName = new WDynamicText(GUIHelper.getPosition(0, 0), WSize.of(0, 0), wInterface);
        m_buttonAccountID = new WButton(GUIHelper.getPosition(0, 0), WSize.of(0, 0), wInterface);
        m_buttonCreateAccount = new WButton(GUIHelper.getPosition(0, 0), WSize.of(0, 0), wInterface);
        m_buttonAccountID.setLabel(accountGenerateIDTranslation);
        m_buttonAccountID.setOnMouseClicked(this::generateAccountID);
        m_buttonCreateAccount.setLabel(accountCreateTranslation);
        m_buttonCreateAccount.setOnMouseClicked(this::createAccount);
        m_tab.add(m_textAccountName, m_fieldAccountName, m_textAccountID, m_fieldAccountID, m_textAccountSecretCode, m_fieldAccountSecretCode,
                  m_textAccountOwnerName, m_fieldAccountOwnerName, m_buttonAccountID, m_buttonCreateAccount);
        GUIHelper.setTheme("dark", m_tab.getWidgets());
    }

    protected void center()
    {
        WInterface wInterface = m_tab.getToggle().getInterface();
        int widgetMarginX = 10;
        int widgetMarginY = 18;
        int fieldMarginY = -4;
        int largestStringWidth = GUIHelper.getLargestString(m_textAccountName.getText().asString(), m_textAccountID.getText().asString(), m_textAccountSecretCode.getText().asString(), m_textAccountOwnerName.getText().asString());
        int textFieldsWidth = wInterface.getWidth() - (largestStringWidth + 25);
        m_textAccountName.setPosition(GUIHelper.getPosition(widgetMarginX, 35, wInterface));
        m_fieldAccountName.setPosition(GUIHelper.getPosition(largestStringWidth + widgetMarginX, fieldMarginY, m_textAccountName));
        m_fieldAccountName.setSize(WSize.of(textFieldsWidth, 15));

        m_textAccountID.setPosition(GUIHelper.getPosition(0, widgetMarginY, m_textAccountName));
        m_buttonAccountID.setSize(WSize.of(GUIHelper.getStringWidth(m_buttonAccountID.getLabel().asString() + widgetMarginX), 15));
        m_buttonAccountID.setPosition(GUIHelper.getPosition(wInterface.getWidth() - ((widgetMarginX + 5) + m_buttonAccountID.getWidth()), fieldMarginY, m_textAccountID));
        m_fieldAccountID.setPosition(GUIHelper.getPosition(largestStringWidth + widgetMarginX, fieldMarginY, m_textAccountID));
        m_fieldAccountID.setSize(WSize.of(textFieldsWidth - (m_buttonAccountID.getWidth() + (widgetMarginX / 2)), 15));

        m_textAccountSecretCode.setPosition(GUIHelper.getPosition(0, widgetMarginY, m_textAccountID));
        m_fieldAccountSecretCode.setPosition(GUIHelper.getPosition(largestStringWidth + widgetMarginX, fieldMarginY, m_textAccountSecretCode));
        m_fieldAccountSecretCode.setSize(WSize.of(textFieldsWidth, 15));

        m_textAccountOwnerName.setPosition(GUIHelper.getPosition(0, 17, m_textAccountSecretCode));
        m_fieldAccountOwnerName.setPosition(GUIHelper.getPosition(largestStringWidth + widgetMarginX, fieldMarginY, m_textAccountOwnerName));
        m_fieldAccountOwnerName.setSize(WSize.of(textFieldsWidth, 15));

        m_buttonCreateAccount.setSize(WSize.of((GUIHelper.getStringWidth(m_buttonCreateAccount.getLabel().asString() + widgetMarginX)), 15));
        m_buttonCreateAccount.setPosition(GUIHelper.getPosition(wInterface.getWidth() - (m_buttonCreateAccount.getWidth() + 5), wInterface.getHeight() - 25, wInterface));
    }

    private void createAccount()
    {
        if (!m_buttonCreateAccount.getFocus())
            return;
        try
        {
            String accountName = m_fieldAccountName.getText();
            int accountID = Integer.parseInt(m_fieldAccountID.getText());
            String accountCode = m_fieldAccountSecretCode.getText();
            String accountOwnerName = m_fieldAccountOwnerName.getText();
            PacketByteBuf packetByteBuf = new PacketByteBuf(Unpooled.buffer());
            packetByteBuf.writeString(accountName);
            packetByteBuf.writeInt(accountID);
            packetByteBuf.writeString(accountCode);
            packetByteBuf.writeString(accountOwnerName);
            ClientSidePacketRegistry.INSTANCE.sendToServer(PacketRegistry.ACCOUNT_CREATION, packetByteBuf);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void generateAccountID()
    {
        if (!m_buttonAccountID.getFocus())
            return;
        m_fieldAccountID.setText(String.valueOf(JavaUtils.randInt(100000, 999999)));
    }
}
