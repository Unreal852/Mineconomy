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
    public BankCheckGUI(ItemStack stack)
    {
        super();
        if(stack == null || MinecraftClient.getInstance().player == null)
            return;

        WInterface wInterface = new WInterface(0, 0, 0, 250, 100);
        getInterfaces().add(wInterface);
        wInterface.center();

        int panelX = wInterface.getPositionX();
        int panelY = wInterface.getPositionY();
        int panelMarginX = 5;
        int panelMarginY = 23;

        TranslatableText fromTextTranslation = new TranslatableText("gui.mineconomy.bank_check_from");
        TranslatableText toTextTranslation = new TranslatableText("gui.mineconomy.bank_check_to");
        TranslatableText amountTextTranslation = new TranslatableText("gui.mineconomy.bank_check_amount");
        TranslatableText validateTextTranslation = new TranslatableText("gui.mineconomy.bank_check_validate");

        int widgetMarginX = 10;
        int widgetMarginY = 18;

        int largestStringWidth = GUIHelper.getLargestString(fromTextTranslation.asString(), toTextTranslation.asString(), amountTextTranslation.asString());

        int textFieldsX = panelX + panelMarginX + widgetMarginX + largestStringWidth;
        int textFieldsWidth = (int) wInterface.getSizeX() - (largestStringWidth + widgetMarginX * 2);
        int textFieldsHeight = 15;

        WStaticText fromText = GUIHelper.createStaticText(wInterface, panelX + panelMarginX, panelY + panelMarginY, fromTextTranslation);
        WDynamicText fromField = GUIHelper.createDynamicText(wInterface, textFieldsX, fromText.getPositionY() - 2, textFieldsWidth, textFieldsHeight);

        WStaticText toText = GUIHelper.createStaticText(wInterface, panelX + panelMarginX,  fromText.getPositionY() + widgetMarginY, toTextTranslation);
        WDynamicText toField = GUIHelper.createDynamicText(wInterface, textFieldsX, toText.getPositionY() - 2, textFieldsWidth, textFieldsHeight);

        WStaticText amountText = GUIHelper.createStaticText(wInterface, panelX + panelMarginX,  toText.getPositionY() + widgetMarginY, amountTextTranslation);
        WDynamicText amountField = GUIHelper.createDynamicText(wInterface, textFieldsX, amountText.getPositionY() - 2, textFieldsWidth, textFieldsHeight);

        int validateWidth = MinecraftClient.getInstance().textRenderer.getStringWidth(validateTextTranslation.asString());

        WButton validateButton = GUIHelper.createButton(wInterface, (panelX + wInterface.getSizeX()) - (validateWidth + widgetMarginX + 5), amountField.getPositionY() + widgetMarginY, validateWidth + 10, 15);
        validateButton.setOnMouseClicked(() ->
        {
            if (validateButton.getFocus() && stack.getItem() instanceof ItemBankCheckbook)
                onValidate(MinecraftClient.getInstance().player, Integer.parseInt(fromField.getLabel().asString()), Integer.parseInt(toField.getText()), Double.parseDouble(amountField.getText()));
        });

        validateButton.setLabel(validateTextTranslation);
        fromField.setLabel(new LiteralText("54723"));
        fromField.setEditable(false);

        wInterface.add(fromText, fromField, toText, toField, amountText, amountField, validateButton);

        if (stack.getItem() instanceof ItemBankCheck)
        {
            wInterface.remove(validateButton);
            CompoundTag stackTag = stack.getOrCreateTag();
            wInterface.setLabel(new TranslatableText("gui.mineconomy.bank_check_title", stackTag.getString("bankCheckFromName")));
            fromField.setLabel(new LiteralText("" + stackTag.getInt("bankCheckFrom")));
            toField.setLabel(new LiteralText("" + stackTag.getInt("bankCheckTo")));
            amountField.setLabel(new LiteralText("" + stackTag.getInt("bankCheckAmount")));
            toField.setEditable(false);
            amountField.setEditable(false);
        }
        else
            wInterface.setLabel(new TranslatableText("gui.mineconomy.bank_check_title", MinecraftClient.getInstance().player.getDisplayName()));
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
}
