package fr.unreal852.mineconomy.client.gui;

import fr.unreal852.mineconomy.common.registry.ItemRegistry;
import fr.unreal852.ucorefabric.client.screen.ICachedScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.TranslatableText;
import spinnery.client.BaseScreen;
import spinnery.widget.WInterface;
import spinnery.widget.WPanel;
import spinnery.widget.WTabHolder;
import spinnery.widget.api.Position;
import spinnery.widget.api.Size;

@Environment(EnvType.CLIENT)
public class BankManagementGUI extends BaseScreen implements ICachedScreen
{
    private WPanel                     m_mainPanel;
    private WTabHolder                 m_tabHolder;
    private BankAccountCreationTab     m_tabAccountCreation;
    private BankAccountConsultationTab m_tabAccountConsultation;

    public BankManagementGUI()
    {
        super();
        WInterface mainInterface = getInterface();
        m_mainPanel = mainInterface.createChild(WPanel.class, Position.of(mainInterface, 0, 0), Size.of(450, 250)).setLabel(new TranslatableText("gui.mineconomy.bank_management_title"));
        m_tabHolder = m_mainPanel.createChild(WTabHolder.class, Position.of(m_mainPanel, 0, 0), Size.of(m_mainPanel));
        initTabs();
        GUIHelper.setTheme("spinnery:dark", m_mainPanel);
        GUIHelper.setTheme("spinnery:dark", m_mainPanel.getWidgets());
    }

    @Override
    public void open(Object... params)
    {
        center();
        MinecraftClient.getInstance().openScreen(this);
    }

    private void initTabs()
    {
        m_tabAccountCreation = new BankAccountCreationTab(m_tabHolder, m_tabHolder.addTab(ItemRegistry.MONEY_BANKNOTE_TEN, new TranslatableText("gui.mineconomy.bank_management_account_creation_name")));
        m_tabAccountConsultation = new BankAccountConsultationTab(m_tabHolder, m_tabHolder.addTab(ItemRegistry.MONEY_BANKNOTE_TEN, new TranslatableText("gui.mineconomy.bank_management_account_consultation_name")));
    }

    private void center()
    {
        m_mainPanel.center();
        m_tabHolder.center();
        m_tabAccountCreation.center();
        m_tabAccountConsultation.center();
    }
}
