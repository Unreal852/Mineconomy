package fr.unreal852.mineconomy.client.gui;


import fr.unreal852.mineconomy.common.ModUtils;
import net.minecraft.text.TranslatableText;
import spinnery.widget.*;

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

    public BankAccountCreationTab(WTabHolder.WTab tab)
    {
        m_tab = tab;
        TranslatableText accountNameTranslation = new TranslatableText("gui.mineconomy.gui_bank_management_sections_account_creation_account_name");
        TranslatableText accountIDTranslation = new TranslatableText("gui.mineconomy.gui_bank_management_sections_account_creation_account_id");
        TranslatableText accountSecretCodeTranslation = new TranslatableText("gui.mineconomy.gui_bank_management_sections_account_creation_account_code");
        TranslatableText accountOwnerNameTranslation = new TranslatableText("gui.mineconomy.gui_bank_management_sections_account_creation_account_owner_name");
        TranslatableText accountGenerateIDTranslation = new TranslatableText("gui.mineconomy.gui_bank_management_sections_account_creation_account_id_generator");

        WInterface wInterface = m_tab.getToggle().getInterface();

        int panelMarginX = 5;
        int panelMarginY = 35;
        int widgetsMarginY = 7;
        int largestStringWidth = GUIHelper.getLargestString(accountNameTranslation.asString(), accountIDTranslation.asString(),
                                                            accountSecretCodeTranslation.asString(), accountOwnerNameTranslation.asString());
        int fieldMarginX = wInterface.getPositionX() + panelMarginX + largestStringWidth + 15;
        int fieldWidth = wInterface.getSizeX() - (largestStringWidth + panelMarginX + 20);

        m_textAccountName = GUIHelper.createStaticText(wInterface, wInterface.getPositionX() + panelMarginX, wInterface.getPositionY() + panelMarginY, accountNameTranslation);
        m_textAccountID = GUIHelper.createStaticText(wInterface, m_textAccountName.getPositionX(), m_textAccountName.getPositionY() + panelMarginY + widgetsMarginY, accountIDTranslation);
        m_textAccountSecretCode = GUIHelper.createStaticText(wInterface, m_textAccountName.getPositionX(), m_textAccountID.getPositionY() + panelMarginY + widgetsMarginY, accountSecretCodeTranslation);
        m_textAccountOwnerName = GUIHelper.createStaticText(wInterface, m_textAccountName.getPositionX(), m_textAccountSecretCode.getPositionY() + panelMarginY + widgetsMarginY, accountOwnerNameTranslation);

        m_buttonAccountID = GUIHelper.createButton(wInterface, (wInterface.getPositionX() + wInterface.getSizeX()) - 60, m_textAccountID.getPositionY() - 2, 55, 15);

        m_fieldAccountName = GUIHelper.createDynamicText(wInterface, fieldMarginX, m_textAccountName.getPositionY() - 2, fieldWidth, 15);
        m_fieldAccountID = GUIHelper.createDynamicText(wInterface, fieldMarginX, m_textAccountID.getPositionY() - 2, fieldWidth - m_buttonAccountID.getSizeX() - 2, 15);
        m_fieldAccountSecretCode = GUIHelper.createDynamicText(wInterface, fieldMarginX, m_textAccountSecretCode.getPositionY() - 2, fieldWidth, 15);
        m_fieldAccountOwnerName = GUIHelper.createDynamicText(wInterface, fieldMarginX, m_textAccountOwnerName.getPositionY() - 2, fieldWidth, 15);

        m_buttonAccountID.setLabel(accountGenerateIDTranslation);
        m_buttonAccountID.setOnMouseClicked(this::generateAccountID);
        m_tab.add(m_textAccountName, m_fieldAccountName, m_fieldAccountID, m_fieldAccountID, m_buttonAccountID,
                  m_textAccountSecretCode, m_fieldAccountSecretCode, m_textAccountOwnerName, m_fieldAccountOwnerName);
    }

    private void generateAccountID()
    {
        if (!m_buttonAccountID.getFocus())
            return;
        m_fieldAccountID.setText(String.valueOf(ModUtils.randInt(1000000, 99999999)));
    }
}
