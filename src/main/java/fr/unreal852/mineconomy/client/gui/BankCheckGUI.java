package fr.unreal852.mineconomy.client.gui;

import fr.unreal852.mineconomy.common.ModConstants;
import fr.unreal852.mineconomy.common.items.ItemBankCheck;
import fr.unreal852.mineconomy.common.items.ItemBankCheckbook;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.PacketByteBuf;
import spinnery.client.BaseScreen;
import spinnery.widget.*;

public class BankCheckGUI extends BaseScreen
{
    private static BankCheckGUI s_instance;

    public static BankCheckGUI getInstance()
    {
        if (s_instance == null)
            s_instance = new BankCheckGUI();
        return s_instance;
    }

    private WStaticText  m_fromText;
    private WStaticText  m_toText;
    private WStaticText  m_amountText;
    private WDynamicText m_fromField;
    private WDynamicText m_toField;
    private WDynamicText m_amountField;
    private WButton      m_validateButton;

    public BankCheckGUI()
    {
        super();

        WInterface wInterface = new WInterface(GUIHelper.getPosition(0, 0), WSize.of(250, 100));
        getInterfaces().add(wInterface);
        TranslatableText fromTextTranslation = new TranslatableText("gui.mineconomy.bank_check_from");
        TranslatableText toTextTranslation = new TranslatableText("gui.mineconomy.bank_check_to");
        TranslatableText amountTextTranslation = new TranslatableText("gui.mineconomy.bank_check_amount");
        TranslatableText validateTextTranslation = new TranslatableText("gui.mineconomy.bank_check_validate");
        m_fromText = new WStaticText(GUIHelper.getPosition(0, 0), wInterface, fromTextTranslation);
        m_fromField = new WDynamicText(GUIHelper.getPosition(0, 0), WSize.of(0, 0), wInterface);
        m_fromField.setEditable(false);
        m_toText = new WStaticText(GUIHelper.getPosition(0, 0), wInterface, toTextTranslation);
        m_toField = new WDynamicText(GUIHelper.getPosition(0, 0), WSize.of(0, 0), wInterface);
        m_amountText = new WStaticText(GUIHelper.getPosition(0, 0), wInterface, amountTextTranslation);
        m_amountField = new WDynamicText(GUIHelper.getPosition(0, 0), WSize.of(0, 0), wInterface);
        m_validateButton = new WButton(GUIHelper.getPosition(0, 0), WSize.of(0, 0), wInterface);
        m_validateButton.setLabel(validateTextTranslation);
        m_validateButton.setOnMouseClicked(this::onValidateClicked);
        wInterface.add(m_fromText, m_fromField, m_toText, m_toField, m_amountText, m_amountField, m_validateButton);
        wInterface.setLabel(new LiteralText(""));
        center();
        GUIHelper.setTheme("dark", wInterface);
        GUIHelper.setTheme("dark", wInterface.getWidgets());
    }

    public void open(ItemStack stack)
    {
        center();
        MinecraftClient.getInstance().openScreen(this);
        WInterface wInterface = getInterfaces().getInterfaces().get(0);
        if(stack.getItem() instanceof ItemBankCheckbook)
        {
            wInterface.setLabel(new TranslatableText("gui.mineconomy.bank_check_title", ""));
        }
        else if(stack.getItem() instanceof ItemBankCheck)
        {

        }
    }

    private void onValidateClicked()
    {
        if(!m_validateButton.getFocus() || MinecraftClient.getInstance().player == null)
            return;
        try
        {
            int toID = Integer.parseInt(m_toField.getText());
            double amount = Double.parseDouble(m_amountField.getText());
            PacketByteBuf byteBuf = new PacketByteBuf(Unpooled.buffer());
            byteBuf.writeInt(toID);
            byteBuf.writeDouble(amount);
            if (MinecraftClient.getInstance().player != null)
                MinecraftClient.getInstance().player.closeContainer();
            ClientSidePacketRegistry.INSTANCE.sendToServer(ModConstants.PACKET_BANK_CHECK_VALIDATION, byteBuf);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void onValidate(ClientPlayerEntity player, int from, int to, double amount)
    {
        PacketByteBuf byteBuf = new PacketByteBuf(Unpooled.buffer());
        byteBuf.writeInt(from);
        byteBuf.writeInt(to);
        byteBuf.writeDouble(amount);
        if (MinecraftClient.getInstance().player != null)
            MinecraftClient.getInstance().player.closeContainer();
        ClientSidePacketRegistry.INSTANCE.sendToServer(ModConstants.PACKET_BANK_CHECK_VALIDATION, byteBuf);
    }

    private void center()
    {
        WInterface wInterface = getInterfaces().getInterfaces().get(0);
        wInterface.center();

        int widgetMarginX = 10;
        int largestStringWidth = GUIHelper.getLargestString(m_fromText.getText().asString(), m_toText.getText().asString(), m_amountText.getText().asString());
        int textFieldsWidth = wInterface.getWidth() - (largestStringWidth + 25);
        int validateWidth = GUIHelper.getStringWidth(m_validateButton.getLabel().asString());

        m_fromText.setPosition(GUIHelper.getPosition(widgetMarginX, 24, wInterface));
        m_fromField.setPosition(GUIHelper.getPosition(largestStringWidth + widgetMarginX, -4, m_fromText));
        m_fromField.setSize(WSize.of(textFieldsWidth, 15));

        m_toText.setPosition(GUIHelper.getPosition(0, 17, m_fromText));
        m_toField.setPosition(GUIHelper.getPosition(largestStringWidth + widgetMarginX, -4, m_toText));
        m_toField.setSize(WSize.of(m_fromField));

        m_amountText.setPosition(GUIHelper.getPosition(0, 17, m_toText));
        m_amountField.setPosition(GUIHelper.getPosition(largestStringWidth + widgetMarginX, -4, m_amountText));
        m_amountField.setSize(WSize.of(m_fromField));

        m_validateButton.setPosition(GUIHelper.getPosition(wInterface.getWidth() - (validateWidth + 15), wInterface.getHeight() - 25, wInterface));
        m_validateButton.setSize(WSize.of(validateWidth + 10, 15));
    }
}
