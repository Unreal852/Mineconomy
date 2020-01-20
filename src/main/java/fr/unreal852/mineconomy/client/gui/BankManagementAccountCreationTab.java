package fr.unreal852.mineconomy.client.gui;

import fr.unreal852.mineconomy.client.gui.tab.BaseTabScreen;
import fr.unreal852.mineconomy.client.gui.tab.TabPage;
import fr.unreal852.mineconomy.common.ModUtils;
import net.minecraft.text.TranslatableText;
import spinnery.widget.*;

public class BankManagementAccountCreationTab extends TabPage
{
    private WStaticText  m_textAccountName;
    private WStaticText  m_textAccountID;
    private WStaticText  m_textAccountSecretCode;
    private WStaticText  m_textAccountOwnerName;
    private WDynamicText m_fieldAccountName;
    private WDynamicText m_fieldAccountID;
    private WDynamicText m_fieldAccountSecretCode;
    private WDynamicText m_fieldAccountOwnerName;
    private WButton      m_buttonAccountID;

    public BankManagementAccountCreationTab(BaseTabScreen tabScreen)
    {
        super(tabScreen, ModUtils.getIdentifier("gui_tab_account_creation_id"), new TranslatableText("gui.mineconomy.gui_bank_management_sections_account_creation_name"));

        TranslatableText accountNameTranslation = new TranslatableText("gui.mineconomy.gui_bank_management_sections_account_creation_account_name");
        TranslatableText accountIDTranslation = new TranslatableText("gui.mineconomy.gui_bank_management_sections_account_creation_account_id");
        TranslatableText accountSecretCodeTranslation = new TranslatableText("gui.mineconomy.gui_bank_management_sections_account_creation_account_code");
        TranslatableText accountOwnerNameTranslation = new TranslatableText("gui.mineconomy.gui_bank_management_sections_account_creation_account_owner_name");
        TranslatableText accountGenerateIDTranslation = new TranslatableText("gui.mineconomy.gui_bank_management_sections_account_creation_account_id_generator");

        WInterface wInterface = getOwnerTabScreen().getInterface();

        int panelMarginX = 5;
        int panelMarginY = 10;
        int widgetsMarginY = 7;
        int largestStringWidth = GUIHelper.getLargestString(accountNameTranslation.asString(), accountIDTranslation.asString(),
                                                            accountSecretCodeTranslation.asString(), accountOwnerNameTranslation.asString());
        int fieldMarginX = (int) wInterface.getPositionX() + panelMarginX + largestStringWidth + 15;
        int fieldWidth = (int) wInterface.getSizeX() - (largestStringWidth + panelMarginX + 20);

        m_textAccountName = GUIHelper.createStaticText(wInterface, (int) wInterface.getPositionX() + panelMarginX, (int) wInterface.getPositionY() + panelMarginY, accountNameTranslation);
        m_textAccountID = GUIHelper.createStaticText(wInterface, (int) m_textAccountName.getPositionX(), (int) m_textAccountName.getPositionY() + panelMarginY + widgetsMarginY, accountIDTranslation);
        m_textAccountSecretCode = GUIHelper.createStaticText(wInterface, (int) m_textAccountName.getPositionX(), (int) m_textAccountID.getPositionY() + panelMarginY + widgetsMarginY, accountSecretCodeTranslation);
        m_textAccountOwnerName = GUIHelper.createStaticText(wInterface, (int) m_textAccountName.getPositionX(), (int) m_textAccountSecretCode.getPositionY() + panelMarginY + widgetsMarginY, accountOwnerNameTranslation);

        m_buttonAccountID = GUIHelper.createButton(wInterface, ((int)wInterface.getPositionX() + (int)wInterface.getSizeX()) - 60, (int)m_textAccountID.getPositionY() - 2, 55, 15);

        m_fieldAccountName = GUIHelper.createDynamicText(wInterface, fieldMarginX, (int) m_textAccountName.getPositionY() - 2, fieldWidth, 15);
        m_fieldAccountID = GUIHelper.createDynamicText(wInterface, fieldMarginX, (int) m_textAccountID.getPositionY() - 2, fieldWidth - (int)m_buttonAccountID.getSizeX() - 2, 15);
        m_fieldAccountSecretCode = GUIHelper.createDynamicText(wInterface, fieldMarginX, (int) m_textAccountSecretCode.getPositionY() - 2, fieldWidth, 15);
        m_fieldAccountOwnerName = GUIHelper.createDynamicText(wInterface, fieldMarginX, (int) m_textAccountOwnerName.getPositionY() - 2, fieldWidth, 15);

        m_buttonAccountID.setLabel(accountGenerateIDTranslation.asString());
        m_buttonAccountID.setOnMouseClicked(this::generateAccountID);
    }

    private void generateAccountID()
    {
        if(!m_buttonAccountID.getFocus())
            return;
        m_fieldAccountID.setText(String.valueOf(ModUtils.randInt(1000000, 99999999)));
    }

    @Override
    public WWidget[] getWidgets()
    {
        return new WWidget[]{m_textAccountName, m_textAccountID, m_textAccountSecretCode, m_textAccountOwnerName,
                             m_fieldAccountName, m_fieldAccountID, m_fieldAccountSecretCode, m_fieldAccountOwnerName,
                             m_buttonAccountID};
    }

    @Override
    public void onTabSelectionChanged(boolean isSelected)
    {
        for (WWidget widget : getWidgets())
            widget.setHidden(!isSelected);
    }
}
