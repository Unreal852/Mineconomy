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
    private WPanel      m_mainPanel;
    private WStaticText m_fromText;
    private WStaticText m_toText;
    private WStaticText m_amountText;
    private WTextField  m_fromField;
    private WTextField  m_toField;
    private WTextField  m_amountField;
    private WButton     m_validateButton;

    public BankCheckGUI()
    {
        super();

        WInterface mainInterface = getInterface();
        m_mainPanel = mainInterface.createChild(WPanel.class);
        m_fromText = m_mainPanel.createChild(WStaticText.class).setLabel(new TranslatableText("gui.mineconomy.bank_check_from"));
        m_toText = m_mainPanel.createChild(WStaticText.class).setLabel(new TranslatableText("gui.mineconomy.bank_check_to"));
        m_amountText = m_mainPanel.createChild(WStaticText.class).setLabel(new TranslatableText("gui.mineconomy.bank_check_amount"));
        m_fromField = m_mainPanel.createChild(WTextField.class).setEditable(false);
        m_toField = m_mainPanel.createChild(WTextField.class);
        m_amountField = m_mainPanel.createChild(WTextField.class);
        m_validateButton = m_mainPanel.createChild(WButton.class).setOnMouseClicked((wButton, x, y, z) -> onValidateClicked()).setLabel(new TranslatableText("gui.mineconomy.bank_check_validate"));
        GUIHelper.setTheme("spinnery:dark", m_mainPanel);
        GUIHelper.setTheme("spinnery:dark", m_mainPanel.getWidgets());
    }

    @Override
    public void open(Object... params)
    {
        if (params == null || params.length != 1 || !(params[0] instanceof ItemStack))
            return;
        ItemStack itemStack = (ItemStack) params[0];
        center();
        MinecraftClient.getInstance().openScreen(this);
        if (itemStack.getItem() instanceof BankCheckbookItem)
        {

        }
        else if (itemStack.getItem() instanceof BankCheckItem)
        {

        }
        else
        {
            if (MinecraftClient.getInstance().currentScreen != null)
                MinecraftClient.getInstance().currentScreen.onClose();
        }
    }

    private void onValidateClicked()
    {
        if (!m_validateButton.isFocused() || MinecraftClient.getInstance().player == null)
            return;
        try
        {
            int toID = Integer.parseInt(m_toField.getText());
            double amount = Double.parseDouble(m_amountField.getText());
            PacketByteBuf byteBuf = new PacketByteBuf(Unpooled.buffer());
            byteBuf.writeInt(toID);
            byteBuf.writeDouble(amount);
            ClientUtils.closeScreen();
            ClientSidePacketRegistry.INSTANCE.sendToServer(PacketRegistry.CHECKBOOK_VALIDATION, byteBuf);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void center()
    {
        m_mainPanel.center();

        int widgetMarginX = 10;
        int largestStringWidth = GUIHelper.getLargestString(m_fromText.getText().asString(), m_toText.getText().asString(), m_amountText.getText().asString());
        int textFieldsWidth = m_mainPanel.getWidth() - (largestStringWidth + 25);
        int validateWidth = GUIHelper.getStringWidth(m_validateButton.getLabel().asString());

        m_fromText.setPosition(Position.of(m_mainPanel, widgetMarginX, 24));
        m_fromField.setPosition(Position.of(m_fromText, largestStringWidth + widgetMarginX, -4));
        m_fromField.setSize(Size.of(textFieldsWidth, 15));

        m_toText.setPosition(Position.of(m_fromText, 0, 17));
        m_toField.setPosition(Position.of(m_toText, largestStringWidth + widgetMarginX, -4));
        m_toField.setSize(Size.of(m_fromField));

        m_amountText.setPosition(Position.of(m_toText, 0, 17));
        m_amountField.setPosition(Position.of(m_amountText, largestStringWidth + widgetMarginX, -4));
        m_amountField.setSize(Size.of(m_fromField));

        m_validateButton.setPosition(Position.of(m_mainPanel, m_mainPanel.getWidth() - (validateWidth + 15), m_mainPanel.getHeight() - 25));
        m_validateButton.setSize(Size.of(validateWidth + 10, 15));
    }
}
