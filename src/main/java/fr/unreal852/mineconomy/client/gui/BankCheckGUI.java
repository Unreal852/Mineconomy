package fr.unreal852.mineconomy.client.gui;

import fr.unreal852.mineconomy.common.item.BankCheckItem;
import fr.unreal852.mineconomy.common.item.BankCheckbookItem;
import fr.unreal852.mineconomy.common.registry.PacketRegistry;
import fr.unreal852.ucorefabric.client.screen.ICachedScreen;
import fr.unreal852.ucorefabric.client.util.ClientUtils;
import io.netty.buffer.Unpooled;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.PacketByteBuf;
import spinnery.client.BaseScreen;
import spinnery.widget.*;
import spinnery.widget.api.Position;
import spinnery.widget.api.Size;

@Environment(EnvType.CLIENT)
public class BankCheckGUI extends BaseScreen implements ICachedScreen
{
    private final WPanel      _mainPanel;
    private final WStaticText _fromText;
    private final WStaticText _toText;
    private final WStaticText _amountText;
    private final WTextField  _fromField;
    private final WTextField  _toField;
    private final WTextField  _amountField;
    private final WButton     _validateButton;

    public BankCheckGUI()
    {
        super();

        WInterface mainInterface = getInterface();
        _mainPanel = mainInterface.createChild(WPanel::new).setSize(Size.of(250, 100));
        _fromText = _mainPanel.createChild(WStaticText::new).setText(new TranslatableText("gui.mineconomy.bank_check_from"));
        _toText = _mainPanel.createChild(WStaticText::new).setText(new TranslatableText("gui.mineconomy.bank_check_to"));
        _amountText = _mainPanel.createChild(WStaticText::new).setText(new TranslatableText("gui.mineconomy.bank_check_amount"));
        _fromField = _mainPanel.createChild(WTextField::new).setEditable(false);
        _toField = _mainPanel.createChild(WTextField::new);
        _amountField = _mainPanel.createChild(WTextField::new);
        _validateButton = _mainPanel.createChild(WButton::new).setOnMouseClicked((wButton, x, y, z) -> onValidateClicked()).setLabel(new TranslatableText("gui.mineconomy.bank_check_validate"));
        GUIHelper.setTheme("spinnery:dark", _mainPanel);
        GUIHelper.setTheme("spinnery:dark", _mainPanel.getWidgets());
    }

    @Override
    public void open(Object... params)
    {
        if (params == null || params.length != 1 || !(params[0] instanceof ItemStack))
            return;
        ItemStack itemStack = (ItemStack) params[0];
        if (itemStack.getItem() instanceof BankCheckbookItem)
        {
            _mainPanel.setLabel(new TranslatableText("gui.mineconomy.bank_check_title", ""));
        }
        else if (itemStack.getItem() instanceof BankCheckItem)
        {
            _mainPanel.setLabel(new TranslatableText("gui.mineconomy.bank_check_title", ""));
        }
        else
            return;
        center();
        MinecraftClient.getInstance().openScreen(this);
    }

    private void onValidateClicked()
    {
        if (!_validateButton.isFocused() || MinecraftClient.getInstance().player == null)
            return;
        try
        {
            int toID = Integer.parseInt(_toField.getText());
            double amount = Double.parseDouble(_amountField.getText());
            PacketByteBuf byteBuf = new PacketByteBuf(Unpooled.buffer());
            byteBuf.writeInt(toID);
            byteBuf.writeDouble(amount);
            ClientUtils.closeScreen();
            ClientSidePacketRegistry.INSTANCE.sendToServer(PacketRegistry.CHECKBOOK_VALIDATION, byteBuf);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void center()
    {
        _mainPanel.center();

        int widgetMarginX = 10;
        int largestStringWidth = GUIHelper.getLargestString(_fromText.getText().asString(), _toText.getText().asString(), _amountText.getText().asString());
        int textFieldsWidth = _mainPanel.getWidth() - (largestStringWidth + 25);
        int validateWidth = GUIHelper.getStringWidth(_validateButton.getLabel().asString());

        _fromText.setPosition(Position.of(_mainPanel, widgetMarginX, 24));
        _fromField.setPosition(Position.of(_fromText, largestStringWidth + widgetMarginX, -4));
        _fromField.setSize(Size.of(textFieldsWidth, 15));

        _toText.setPosition(Position.of(_fromText, 0, 17));
        _toField.setPosition(Position.of(_toText, largestStringWidth + widgetMarginX, -4));
        _toField.setSize(Size.of(_fromField));

        _amountText.setPosition(Position.of(_toText, 0, 17));
        _amountField.setPosition(Position.of(_amountText, largestStringWidth + widgetMarginX, -4));
        _amountField.setSize(Size.of(_fromField));

        _validateButton.setPosition(Position.of(_mainPanel, _mainPanel.getWidth() - (validateWidth + 15), _mainPanel.getHeight() - 25));
        _validateButton.setSize(Size.of(validateWidth + 10, 15));
    }
}
