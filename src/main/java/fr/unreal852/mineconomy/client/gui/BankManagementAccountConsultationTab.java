package fr.unreal852.mineconomy.client.gui;

import fr.unreal852.mineconomy.client.gui.GUIHelper;
import fr.unreal852.mineconomy.client.gui.tab.BaseTabScreen;
import fr.unreal852.mineconomy.client.gui.tab.TabPage;
import fr.unreal852.mineconomy.common.ModUtils;
import net.minecraft.text.TranslatableText;
import spinnery.widget.WInterface;
import spinnery.widget.WStaticText;
import spinnery.widget.WWidget;

public class BankManagementAccountConsultationTab extends TabPage
{
    public BankManagementAccountConsultationTab(BaseTabScreen tabScreen)
    {
        super(tabScreen, ModUtils.getIdentifier("gui_tab_account_consultation_id"), new TranslatableText("gui.mineconomy.gui_bank_management_sections_account_consultation_name"));

        /*
        TranslatableText accountNameTranslation = new TranslatableText("gui.mineconomy.gui_bank_management_sections_account_creation_account_name");
        TranslatableText accountIDTranslation = new TranslatableText("gui.mineconomy.gui_bank_management_sections_account_creation_account_id");
        TranslatableText accountSecretCodeTranslation = new TranslatableText("gui.mineconomy.gui_bank_management_sections_account_creation_account_code");

        WInterface wInterface = getOwnerTabScreen().getInterface();

        int panelMarginX = 5;
        int panelMarginY = 10;

        m_accountNameText = GUIHelper.createStaticText(wInterface, (int) wInterface.getPositionX() + panelMarginX, (int) wInterface.getPositionY() + panelMarginY, accountNameTranslation);
        m_accountIDText = GUIHelper.createStaticText(wInterface, (int) m_accountNameText.getPositionX(), (int) m_accountNameText.getPositionY() + panelMarginY, accountIDTranslation);
        m_accountSecretCodeText = GUIHelper.createStaticText(wInterface, (int) m_accountNameText.getPositionX(), (int) m_accountIDText.getPositionY() + panelMarginY, accountSecretCodeTranslation); */
    }

    @Override
    public WWidget[] getWidgets()
    {
        return new WWidget[0];
    }

    @Override
    public void onTabSelectionChanged(boolean isSelected)
    {

    }
}
