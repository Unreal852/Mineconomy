package fr.unreal852.mineconomy.client.gui;

import fr.unreal852.mineconomy.common.registry.PacketRegistry;
import fr.unreal852.ucorefabric.common.util.JavaUtils;
import io.netty.buffer.Unpooled;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.PacketByteBuf;
import spinnery.widget.*;
import spinnery.widget.api.Position;
import spinnery.widget.api.Size;

@Environment(EnvType.CLIENT)
public class BankAccountCreationTab
{
    private WTabHolder      m_tabHolder;
    private WTabHolder.WTab m_tab;
    private WStaticText     m_textAccountName;
    private WStaticText     m_textAccountID;
    private WStaticText     m_textAccountSecretCode;
    private WStaticText     m_textAccountOwnerName;
    private WTextField      m_fieldAccountName;
    private WTextField      m_fieldAccountID;
    private WTextField      m_fieldAccountSecretCode;
    private WTextField      m_fieldAccountOwnerName;
    private WButton         m_buttonAccountID;
    private WButton         m_buttonCreateAccount;

    public BankAccountCreationTab(WTabHolder tabHolder, WTabHolder.WTab tab)
    {
        m_tabHolder = tabHolder;
        m_tab = tab;
        WInterface mainInterface = tab.getInterface();
        m_textAccountName = m_tab.createChild(WStaticText.class).setText(new TranslatableText("gui.mineconomy.bank_management_account_creation_account_name"));
        m_textAccountID = m_tab.createChild(WStaticText.class).setText(new TranslatableText("gui.mineconomy.bank_management_account_creation_account_id"));
        m_textAccountSecretCode = m_tab.createChild(WStaticText.class).setText(new TranslatableText("gui.mineconomy.bank_management_account_creation_account_code"));
        m_textAccountOwnerName = m_tab.createChild(WStaticText.class).setText(new TranslatableText("gui.mineconomy.bank_management_account_creation_account_owner_name"));
        m_fieldAccountName = m_tab.createChild(WTextField.class);
        m_fieldAccountID = m_tab.createChild(WTextField.class);
        m_fieldAccountSecretCode = m_tab.createChild(WTextField.class);
        m_fieldAccountOwnerName = m_tab.createChild(WTextField.class);
        m_buttonAccountID = m_tab.createChild(WButton.class).setOnMouseClicked(((widget, x, y, z) -> generateAccountID())).setLabel(new TranslatableText("gui.mineconomy.bank_management_account_creation_account_id_generator"));
        m_buttonCreateAccount = m_tab.createChild(WButton.class).setOnMouseClicked(((widget, x, y, z) -> createAccount())).setLabel(new TranslatableText("gui.mineconomy.bank_management_account_creation_validate"));
        GUIHelper.setTheme("spinnery:dark", m_tab.getWidgets());
    }

    private void createAccount()
    {
        if (!m_buttonCreateAccount.isFocused())
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
        if (!m_buttonAccountID.isFocused())
            return;
        m_fieldAccountID.setText(String.valueOf(JavaUtils.randInt(100000, 999999)));
    }

    protected void center()
    {
        int widgetMarginX = 10;
        int widgetMarginY = 18;
        int fieldMarginY = -4;
        int largestStringWidth = GUIHelper.getLargestString(m_textAccountName.getText().asString(), m_textAccountID.getText().asString(), m_textAccountSecretCode.getText().asString(), m_textAccountOwnerName.getText().asString());
        int textFieldsWidth = m_tabHolder.getWidth() - (largestStringWidth + 25);
        m_textAccountName.setPosition(Position.of(m_tabHolder, widgetMarginX, 35));
        m_fieldAccountName.setPosition(Position.of(m_textAccountName, largestStringWidth + widgetMarginX, fieldMarginY));
        m_fieldAccountName.setSize(Size.of(textFieldsWidth, 15));

        m_textAccountID.setPosition(Position.of(m_textAccountName, 0, widgetMarginY));
        m_buttonAccountID.setSize(Size.of(GUIHelper.getStringWidth(m_buttonAccountID.getLabel().asString() + widgetMarginX), 15));
        m_buttonAccountID.setPosition(Position.of(m_textAccountID, m_tabHolder.getWidth() - ((widgetMarginX + 5) + m_buttonAccountID.getWidth()), fieldMarginY));
        m_fieldAccountID.setPosition(Position.of(m_textAccountID, largestStringWidth + widgetMarginX, fieldMarginY));
        m_fieldAccountID.setSize(Size.of(textFieldsWidth - (m_buttonAccountID.getWidth() + (widgetMarginX / 2)), 15));

        m_textAccountSecretCode.setPosition(Position.of(m_textAccountID, 0, widgetMarginY));
        m_fieldAccountSecretCode.setPosition(Position.of(m_textAccountSecretCode, largestStringWidth + widgetMarginX, fieldMarginY));
        m_fieldAccountSecretCode.setSize(Size.of(textFieldsWidth, 15));

        m_textAccountOwnerName.setPosition(Position.of(m_textAccountSecretCode, 0, 17));
        m_fieldAccountOwnerName.setPosition(Position.of(m_textAccountOwnerName, largestStringWidth + widgetMarginX, fieldMarginY));
        m_fieldAccountOwnerName.setSize(Size.of(textFieldsWidth, 15));

        m_buttonCreateAccount.setSize(Size.of((GUIHelper.getStringWidth(m_buttonCreateAccount.getLabel().asString() + widgetMarginX)), 15));
        m_buttonCreateAccount.setPosition(Position.of(m_tabHolder, m_tabHolder.getWidth() - (m_buttonCreateAccount.getWidth() + 5), m_tabHolder.getHeight() - 25));
    }
}
