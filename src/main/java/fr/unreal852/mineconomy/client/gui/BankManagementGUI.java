package fr.unreal852.mineconomy.client.gui;

import fr.unreal852.mineconomy.client.gui.tab.BaseTabScreen;
import net.minecraft.text.TranslatableText;

public class BankManagementGUI extends BaseTabScreen
{
    public BankManagementGUI()
    {
        super();

        getTabsInterface().setLabel(new TranslatableText("gui.mineconomy.gui_bank_management_sections_title").asString());
        setTabsPanelWidth(125);
        setTabContentPanelWidth(350);
        setHeight(150);
        center();
        registerTabPage(new BankManagementAccountCreationTab(this));
        registerTabPage(new BankManagementAccountConsultationTab(this));
        buildTabsButtons();
        setCurrentTab(0);
    }
}
