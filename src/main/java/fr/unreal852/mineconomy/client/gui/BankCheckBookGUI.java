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
import spinnery.widget.*;

public class BankCheckBookGUI extends BaseGUI
{
    public BankCheckBookGUI(int synchronizationID, PlayerInventory linkedPlayerInventory, ItemStack stack)
    {
        super(synchronizationID, linkedPlayerInventory, stack);
        /*
        setLinkedPanel(new WPanel(0, 0, 0, 250, 100, this));

        linkedPanel.center();

        int panelX = (int) linkedPanel.getPositionX() + 5;
        int panelY = (int) (linkedPanel.getPositionY() + 23);

        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;

        TranslatableText fromTextTranslation = new TranslatableText("gui.mineconomy.bank_check_from");
        TranslatableText toTextTranslation = new TranslatableText("gui.mineconomy.bank_check_to");
        TranslatableText amountTextTranslation = new TranslatableText("gui.mineconomy.bank_check_amount");
        TranslatableText validateTextTranslation = new TranslatableText("gui.mineconomy.bank_check_validate");

        int largestString = ModUtils.getLargestString(fromTextTranslation.asString(), toTextTranslation.asString(), amountTextTranslation.asString());
        int marginX = 10;
        int marginY = 18;
        int validateWidth = textRenderer.getStringWidth(validateTextTranslation.asString());

        WStaticText fromText = new WStaticText(WAnchor.GL_ORIGIN, panelX, panelY, 0, fromTextTranslation.asString(), linkedPanel);
        WDynamicText fromField = new WDynamicText(WAnchor.GL_ORIGIN, panelX + largestString + marginX, (int) fromText.getPositionY() - 2, 0, (int) linkedPanel.getSizeX() - (largestString + marginX * 2), 15, linkedPanel);
        fromField.setLabel("84632");
        fromField.setEditable(false);

        WStaticText toText = new WStaticText(WAnchor.GL_ORIGIN, panelX, (int) fromText.getPositionY() + marginY, 0, toTextTranslation.asString(), linkedPanel);
        WDynamicText toField = new WDynamicText(WAnchor.GL_ORIGIN, panelX + largestString + marginX, (int) toText.getPositionY() - 2, 0, (int) linkedPanel.getSizeX() - (largestString + marginX * 2), 15, linkedPanel);

        WStaticText amountText = new WStaticText(WAnchor.GL_ORIGIN, panelX, (int) toText.getPositionY() + marginY, 0, amountTextTranslation.asString(), linkedPanel);
        WDynamicText amountField = new WDynamicText(WAnchor.GL_ORIGIN, panelX + largestString + marginX, (int) amountText.getPositionY() - 2, 0, (int) linkedPanel.getSizeX() - (largestString + marginX * 2), 15, linkedPanel);

        WButton validateButton = new WButton(WAnchor.GL_ORIGIN, ((int) linkedPanel.getPositionX() + (int) linkedPanel.getSizeX()) - (validateWidth + 10 + 5), (int) amountField.getPositionY() + marginY, 0, validateWidth + 10, 15, linkedPanel);
        validateButton.setLabel(validateTextTranslation.asString());
        validateButton.setOnMouseClicked(() ->
        {
            if (!validateButton.getFocus() || stack.getOrCreateTag().contains(ModConstants.TAG_ITEM_BANK_CHECK_FILLED))
                return;
            PacketByteBuf byteBuf = new PacketByteBuf(Unpooled.buffer());
            byteBuf.writeString(fromField.getText());
            byteBuf.writeString(toField.getText());
            byteBuf.writeString(amountField.getText());
            ClientSidePacketRegistry.INSTANCE.sendToServer(EconomyPackets.BANK_CHECK_VALIDATION, byteBuf);
            if (MinecraftClient.getInstance().player != null)
                MinecraftClient.getInstance().player.closeContainer();
        });

        if (stack.getOrCreateTag().contains(ModConstants.TAG_ITEM_BANK_CHECK_FILLED))
        {
            fromField.setLabel(stack.getOrCreateTag().getString("bankFrom"));
            toField.setLabel(stack.getOrCreateTag().getString("bankTo"));
            amountField.setLabel(stack.getOrCreateTag().getString("bankAmount"));
            toField.setEditable(false);
            amountField.setEditable(false);
            validateButton.setHidden(true);
        }

        linkedPanel.setLabel(new TranslatableText("gui.mineconomy.bank_check_title", newLinkedPlayerInventory.player.getDisplayName()).asString());
        linkedPanel.add(fromText, fromField, toText, toField, amountText, amountField, validateButton); */
    }

    @Override
    protected void onInitClient(ClientPlayerEntity client, TextRenderer textRenderer, Object... args)
    {
        if (args == null || args.length != 1 || !(args[0] instanceof ItemStack))
        {
            ModLogger.LogError("[ClientSide] Received 'BankCheckBookGUI' without ItemStack");
            return;
        }
        ItemStack itemStack = (ItemStack) args[0];

        linkedPanel.center();

        int panelX = (int)linkedPanel.getPositionX();
        int panelY = (int)linkedPanel.getPositionY();
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
        int textFieldsWidth = (int) linkedPanel.getSizeX() - (largestStringWidth + widgetMarginX * 2);
        int textFieldsHeight = 15;

        WStaticText fromText = GUIHelper.createStaticText(linkedPanel, panelX + panelMarginX, panelY + panelMarginY, fromTextTranslation.asString());
        WDynamicText fromField = GUIHelper.createDynamicText(linkedPanel, textFieldsX, (int) fromText.getPositionY() - 2, textFieldsWidth, textFieldsHeight);

        WStaticText toText = GUIHelper.createStaticText(linkedPanel, panelX + panelMarginX, (int) fromText.getPositionY() + widgetMarginY, fromTextTranslation.asString());
        WDynamicText toField = GUIHelper.createDynamicText(linkedPanel, textFieldsX, (int) toText.getPositionY() - 2, textFieldsWidth, textFieldsHeight);

        WStaticText amountText = GUIHelper.createStaticText(linkedPanel, panelX + panelMarginX, (int) toText.getPositionY() + widgetMarginY, fromTextTranslation.asString());
        WDynamicText amountField = GUIHelper.createDynamicText(linkedPanel, textFieldsX, (int) amountText.getPositionY() - 2, textFieldsWidth, textFieldsHeight);

        int validateWidth = textRenderer.getStringWidth(validateTextTranslation.asString());

        WButton validateButton = GUIHelper.createButtonText(linkedPanel, (panelX + (int) linkedPanel.getSizeX()) - (validateWidth + widgetMarginX + 5), (int) amountField.getPositionY() + widgetMarginY, validateWidth + 10, 15);
        validateButton.setOnMouseClicked(() ->
        {
            if (validateButton.getFocus() && itemStack.getItem() instanceof ItemBankCheckbook)
                onValidate(client, fromField.getText(), toField.getText(), amountField.getText());
        });

        fromField.setEditable(false);

        linkedPanel.add(fromText, fromField, toText, toField, amountText, amountField, validateButton);

        if (itemStack.getItem() instanceof ItemBankCheck)
        {
            linkedPanel.remove(validateButton);
            fromField.setLabel(itemStack.getOrCreateTag().getString("bankFrom"));
            toField.setLabel(itemStack.getOrCreateTag().getString("bankTo"));
            amountField.setLabel(itemStack.getOrCreateTag().getString("bankAmount"));
            toField.setEditable(false);
            amountField.setEditable(false);
        }
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
