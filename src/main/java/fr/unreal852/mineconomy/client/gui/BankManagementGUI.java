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
    private WPanel                     _mainPanel;
    private WTabHolder                 _tabHolder;
    private BankAccountCreationTab     _tabAccountCreation;
    private BankAccountConsultationTab _tabAccountConsultation;

    public BankManagementGUI()
    {
        super();
        WInterface mainInterface = getInterface();
        _mainPanel = mainInterface.createChild(WPanel::new, Position.of(mainInterface, 0, 0), Size.of(450, 250)).setLabel(new TranslatableText("gui.mineconomy.bank_management_title"));
        _tabHolder = _mainPanel.createChild(WTabHolder::new, Position.of(_mainPanel, 0, 0), Size.of(_mainPanel));
        initTabs();
        GUIHelper.setTheme("spinnery:dark", _mainPanel);
        GUIHelper.setTheme("spinnery:dark", _mainPanel.getWidgets());
    }

    @Override
    public void open(Object... params)
    {
        center();
        MinecraftClient.getInstance().openScreen(this);
    }

    private void initTabs()
    {
        _tabAccountCreation = new BankAccountCreationTab(_tabHolder, _tabHolder.addTab(ItemRegistry.MONEY_BANKNOTE_TEN, new TranslatableText("gui.mineconomy.bank_management_account_creation_name")));
        _tabAccountConsultation = new BankAccountConsultationTab(_tabHolder, _tabHolder.addTab(ItemRegistry.MONEY_BANKNOTE_TEN, new TranslatableText("gui.mineconomy.bank_management_account_consultation_name")));
    }

    private void center()
    {
        _mainPanel.center();
        _tabHolder.center();
        _tabAccountCreation.center();
        _tabAccountConsultation.center();
    }
}
