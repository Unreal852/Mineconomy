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
    private final WTabHolder      _tabHolder;
    private final WTabHolder.WTab _tab;
    private final WStaticText     _textAccountName;
    private final WStaticText     _textAccountID;
    private final WStaticText     _textAccountSecretCode;
    private final WStaticText     _textAccountOwnerName;
    private final WTextField      _fieldAccountName;
    private final WTextField      _fieldAccountID;
    private final WTextField      _fieldAccountSecretCode;
    private final WTextField      _fieldAccountOwnerName;
    private final WButton         _buttonAccountID;
    private final WButton         _buttonCreateAccount;

    public BankAccountCreationTab(WTabHolder tabHolder, WTabHolder.WTab tab)
    {
        _tabHolder = tabHolder;
        _tab = tab;
        WInterface mainInterface = tab.getInterface();
        _textAccountName = _tab.createChild(WStaticText::new).setText(new TranslatableText("gui.mineconomy.bank_management_account_creation_account_name"));
        _textAccountID = _tab.createChild(WStaticText::new).setText(new TranslatableText("gui.mineconomy.bank_management_account_creation_account_id"));
        _textAccountSecretCode = _tab.createChild(WStaticText::new).setText(new TranslatableText("gui.mineconomy.bank_management_account_creation_account_code"));
        _textAccountOwnerName = _tab.createChild(WStaticText::new).setText(new TranslatableText("gui.mineconomy.bank_management_account_creation_account_owner_name"));
        _fieldAccountName = _tab.createChild(WTextField::new);
        _fieldAccountID = _tab.createChild(WTextField::new);
        _fieldAccountSecretCode = _tab.createChild(WTextField::new);
        _fieldAccountOwnerName = _tab.createChild(WTextField::new);
        _buttonAccountID = _tab.createChild(WButton::new).setOnMouseClicked(((widget, x, y, z) -> generateAccountID())).setLabel(new TranslatableText("gui.mineconomy.bank_management_account_creation_account_id_generator"));
        _buttonCreateAccount = _tab.createChild(WButton::new).setOnMouseClicked(((widget, x, y, z) -> createAccount())).setLabel(new TranslatableText("gui.mineconomy.bank_management_account_creation_validate"));
        GUIHelper.setTheme("spinnery:dark", _tab.getWidgets());
    }

    private void createAccount()
    {
        if (!_buttonCreateAccount.isFocused())
            return;
        try
        {
            String accountName = _fieldAccountName.getText();
            int accountID = Integer.parseInt(_fieldAccountID.getText());
            String accountCode = _fieldAccountSecretCode.getText();
            String accountOwnerName = _fieldAccountOwnerName.getText();
            PacketByteBuf packetByteBuf = new PacketByteBuf(Unpooled.buffer());
            packetByteBuf.writeString(accountName);
            packetByteBuf.writeInt(accountID);
            packetByteBuf.writeString(accountCode);
            packetByteBuf.writeString(accountOwnerName);
            ClientSidePacketRegistry.INSTANCE.sendToServer(PacketRegistry.ACCOUNT_CREATION, packetByteBuf);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void generateAccountID()
    {
        if (!_buttonAccountID.isFocused())
            return;
        _fieldAccountID.setText(String.valueOf(JavaUtils.randInt(100000, 999999)));
    }

    protected void center()
    {
        int widgetMarginX = 10;
        int widgetMarginY = 18;
        int fieldMarginY = -4;
        int largestStringWidth = GUIHelper.getLargestString(_textAccountName.getText().asString(), _textAccountID.getText().asString(), _textAccountSecretCode.getText().asString(), _textAccountOwnerName.getText().asString());
        int textFieldsWidth = _tabHolder.getWidth() - (largestStringWidth + 25);
        _textAccountName.setPosition(Position.of(_tabHolder, widgetMarginX, 35));
        _fieldAccountName.setPosition(Position.of(_textAccountName, largestStringWidth + widgetMarginX, fieldMarginY));
        _fieldAccountName.setSize(Size.of(textFieldsWidth, 15));

        _textAccountID.setPosition(Position.of(_textAccountName, 0, widgetMarginY));
        _buttonAccountID.setSize(Size.of(GUIHelper.getStringWidth(_buttonAccountID.getLabel().asString() + widgetMarginX), 15));
        _buttonAccountID.setPosition(Position.of(_textAccountID, _tabHolder.getWidth() - ((widgetMarginX + 5) + _buttonAccountID.getWidth()), fieldMarginY));
        _fieldAccountID.setPosition(Position.of(_textAccountID, largestStringWidth + widgetMarginX, fieldMarginY));
        _fieldAccountID.setSize(Size.of(textFieldsWidth - (_buttonAccountID.getWidth() + (widgetMarginX / 2)), 15));

        _textAccountSecretCode.setPosition(Position.of(_textAccountID, 0, widgetMarginY));
        _fieldAccountSecretCode.setPosition(Position.of(_textAccountSecretCode, largestStringWidth + widgetMarginX, fieldMarginY));
        _fieldAccountSecretCode.setSize(Size.of(textFieldsWidth, 15));

        _textAccountOwnerName.setPosition(Position.of(_textAccountSecretCode, 0, 17));
        _fieldAccountOwnerName.setPosition(Position.of(_textAccountOwnerName, largestStringWidth + widgetMarginX, fieldMarginY));
        _fieldAccountOwnerName.setSize(Size.of(textFieldsWidth, 15));

        _buttonCreateAccount.setSize(Size.of((GUIHelper.getStringWidth(_buttonCreateAccount.getLabel().asString() + widgetMarginX)), 15));
        _buttonCreateAccount.setPosition(Position.of(_tabHolder, _tabHolder.getWidth() - (_buttonCreateAccount.getWidth() + 5), _tabHolder.getHeight() - 25));
    }
}
