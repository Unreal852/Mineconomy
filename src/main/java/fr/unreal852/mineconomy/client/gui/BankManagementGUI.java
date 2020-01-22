package fr.unreal852.mineconomy.client.gui;

import fr.unreal852.mineconomy.common.items.EconomyItems;
import net.minecraft.text.LiteralText;
import spinnery.client.BaseScreen;
import spinnery.widget.WAnchor;
import spinnery.widget.WInterface;
import spinnery.widget.WTabHolder;

public class BankManagementGUI extends BaseScreen
{
    private WInterface                 m_mainInterface;
    private WTabHolder                 m_tabHolder;
    private BankAccountCreationTab     m_tabAccountCreation;
    private BankAccountConsultationTab m_tabAccountConsultation;
    private WTabHolder.WTab            tabA;
    private WTabHolder.WTab            tabB;

    public BankManagementGUI()
    {
        super();

        m_mainInterface = new WInterface(0, 0, 0, 350, 200);
        m_tabHolder = GUIHelper.createTabHolder(m_mainInterface, 0, 0, m_mainInterface.getSizeX(), m_mainInterface.getSizeY());
        getInterfaces().add(m_mainInterface);
        m_mainInterface.add(m_tabHolder);
        initAccountCreationTab();
        center();
    }

    private void initAccountCreationTab()
    {
        tabA = m_tabHolder.addTab(EconomyItems.MONEY_BANKNOTE_TEN.getItem(), new LiteralText("Account Creation"));
        tabB = m_tabHolder.addTab(EconomyItems.MONEY_BANKNOTE_TEN.getItem(), new LiteralText("Account Consultation"));
    }

    private void center()
    {
        m_mainInterface.center();
        m_tabHolder.center();
        // Temp fix as center does not work actually
        int tabSize = m_mainInterface.getSizeX() / 2;
        tabA.getToggle().setPositionX(m_mainInterface.getPositionX());
        tabA.getToggle().setPositionY(m_mainInterface.getPositionY() - 1);
        tabB.getToggle().setPositionX(m_mainInterface.getPositionX() + tabSize);
        tabB.getToggle().setPositionY(m_mainInterface.getPositionY() - 1);
    }
}
