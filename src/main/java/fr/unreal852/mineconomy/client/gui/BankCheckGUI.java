package fr.unreal852.mineconomy.client.gui;

import fr.unreal852.mineconomy.ModConstants;
import fr.unreal852.mineconomy.ModLogger;
import fr.unreal852.mineconomy.ModUtils;
import fr.unreal852.mineconomy.common.EconomyPackets;
import fr.unreal852.mineconomy.common.items.ItemBankCheck;
import fr.unreal852.mineconomy.common.items.ItemBankCheckbook;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
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

        setInterface(new WInterface(0, 0, 0, 250, 100));
        getInterface().center();

        int panelX = (int)getInterface().getPositionX();
        int panelY = (int)getInterface().getPositionY();
        int panelMarginX = 5;
        int panelMarginY = 23;

        TranslatableText fromTextTranslation = new TranslatableText("gui.mineconomy.bank_check_from");
        TranslatableText toTextTranslation = new TranslatableText("gui.mineconomy.bank_check_to");
        TranslatableText amountTextTranslation = new TranslatableText("gui.mineconomy.bank_check_amount");
        TranslatableText validateTextTranslation = new TranslatableText("gui.mineconomy.bank_check_validate");

        int widgetMarginX = 10;
        int widgetMarginY = 18;

        int largestStringWidth = ModUtils.getLargestString(fromTextTranslation.asString(), toTextTranslation.asString(), amountTextTranslation.asString());

        int textFieldsX = panelX + panelMarginX + widgetMarginX + largestStringWidth;
        int textFieldsWidth = (int) getInterface().getSizeX() - (largestStringWidth + widgetMarginX * 2);
        int textFieldsHeight = 15;

        WStaticText fromText = GUIHelper.createStaticText(getInterface(), panelX + panelMarginX, panelY + panelMarginY, fromTextTranslation.asString());
        WDynamicText fromField = GUIHelper.createDynamicText(getInterface(), textFieldsX, (int) fromText.getPositionY() - 2, textFieldsWidth, textFieldsHeight);

        WStaticText toText = GUIHelper.createStaticText(getInterface(), panelX + panelMarginX, (int) fromText.getPositionY() + widgetMarginY, toTextTranslation.asString());
        WDynamicText toField = GUIHelper.createDynamicText(getInterface(), textFieldsX, (int) toText.getPositionY() - 2, textFieldsWidth, textFieldsHeight);

        WStaticText amountText = GUIHelper.createStaticText(getInterface(), panelX + panelMarginX, (int) toText.getPositionY() + widgetMarginY, amountTextTranslation.asString());
        WDynamicText amountField = GUIHelper.createDynamicText(getInterface(), textFieldsX, (int) amountText.getPositionY() - 2, textFieldsWidth, textFieldsHeight);

        int validateWidth = MinecraftClient.getInstance().textRenderer.getStringWidth(validateTextTranslation.asString());

        WButton validateButton = GUIHelper.createButtonText(getInterface(), (panelX + (int) getInterface().getSizeX()) - (validateWidth + widgetMarginX + 5), (int) amountField.getPositionY() + widgetMarginY, validateWidth + 10, 15);
        validateButton.setOnMouseClicked(() ->
        {
            if (validateButton.getFocus() && stack.getItem() instanceof ItemBankCheckbook)
                onValidate(MinecraftClient.getInstance().player, fromField.getLabel(), toField.getText(), amountField.getText());
        });

        validateButton.setLabel(validateTextTranslation.asString());
        fromField.setLabel(MinecraftClient.getInstance().player.getDisplayName().asString());
        fromField.setEditable(false);

        getInterface().add(fromText, fromField, toText, toField, amountText, amountField, validateButton);

        if (stack.getItem() instanceof ItemBankCheck)
        {
            getInterface().remove(validateButton);
            getInterface().setLabel(new TranslatableText("gui.mineconomy.bank_check_title", stack.getOrCreateTag().getString("bankFromName")).asString());
            fromField.setLabel(stack.getOrCreateTag().getString("bankFrom"));
            toField.setLabel(stack.getOrCreateTag().getString("bankTo"));
            amountField.setLabel(stack.getOrCreateTag().getString("bankAmount"));
            toField.setEditable(false);
            amountField.setEditable(false);
        }
        else
            getInterface().setLabel(new TranslatableText("gui.mineconomy.bank_check_title", MinecraftClient.getInstance().player.getDisplayName()).asString());
    }

    private void onValidate(ClientPlayerEntity player, String from, String to, String amount)
    {
        PacketByteBuf byteBuf = new PacketByteBuf(Unpooled.buffer());
        byteBuf.writeString(from);
        byteBuf.writeString(to);
        byteBuf.writeString(amount);
        ClientSidePacketRegistry.INSTANCE.sendToServer(EconomyPackets.BANK_CHECK_VALIDATION, byteBuf);
        if (MinecraftClient.getInstance().player != null)
            MinecraftClient.getInstance().player.closeContainer();
    }
}
