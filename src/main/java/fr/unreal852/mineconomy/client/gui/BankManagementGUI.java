package fr.unreal852.mineconomy.client.gui;

import fr.unreal852.mineconomy.common.registry.ItemRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.TranslatableText;
import spinnery.client.BaseScreen;
import spinnery.widget.WInterface;
import spinnery.widget.WSize;
import spinnery.widget.WTabHolder;

@Environment(EnvType.CLIENT)
public class BankManagementGUI extends BaseScreen
{
    private WInterface                 m_mainInterface;
    private WInterface                 m_titleInterface;
    private WTabHolder                 m_tabHolder;
    private BankAccountCreationTab     m_tabAccountCreation;
    private BankAccountConsultationTab m_tabAccountConsultation;

    public BankManagementGUI()
    {
        super();
        m_mainInterface = new WInterface(GUIHelper.getPosition(0, 0), WSize.of(350, 200));
        m_titleInterface = new WInterface(GUIHelper.getPosition(0, 0), WSize.of(350, 25));
        getInterfaceHolder().add(m_titleInterface, m_mainInterface);
        m_tabHolder = new WTabHolder(GUIHelper.getPosition(0, 0, m_mainInterface), WSize.of(m_mainInterface), m_mainInterface);
        m_titleInterface.setLabel(new TranslatableText("gui.mineconomy.gui_bank_management_title"));
        m_mainInterface.add(m_tabHolder);
        initTabs();
        center();
        GUIHelper.setTheme("dark", m_mainInterface, m_titleInterface);
        GUIHelper.setTheme("dark", m_mainInterface.getWidgets());
    }

    private void initTabs()
    {
        m_tabAccountCreation = new BankAccountCreationTab(m_tabHolder.addTab(ItemRegistry.MONEY_BANKNOTE_TEN, new TranslatableText("gui.mineconomy.gui_bank_management_account_creation_name")));
        m_tabAccountConsultation = new BankAccountConsultationTab(m_tabHolder.addTab(ItemRegistry.MONEY_BANKNOTE_TEN, new TranslatableText("gui.mineconomy.gui_bank_management_account_consultation_name")));
    }

    private void center()
    {
        m_mainInterface.center();
        m_titleInterface.setPosition(GUIHelper.getPosition(0, -16, m_mainInterface));
        m_tabHolder.center();
        m_tabAccountCreation.center();
        m_tabAccountConsultation.center();
    }
}
