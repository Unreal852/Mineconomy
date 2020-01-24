package fr.unreal852.mineconomy.client.gui;


import fr.unreal852.mineconomy.common.ModUtils;
import net.minecraft.text.TranslatableText;
import spinnery.widget.WTabHolder;
import spinnery.widget.WWidget;

public class BankAccountConsultationTab
{
    private WTabHolder.WTab m_tab;

    public BankAccountConsultationTab(WTabHolder.WTab wTab)
    {
        m_tab = wTab;

        GUIHelper.setTheme("dark", m_tab.getWidgets());
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
}
