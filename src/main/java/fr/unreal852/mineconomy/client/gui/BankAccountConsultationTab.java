package fr.unreal852.mineconomy.client.gui;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import spinnery.widget.WTabHolder;

@Environment(EnvType.CLIENT)
public class BankAccountConsultationTab
{
    private WTabHolder.WTab m_tab;

    public BankAccountConsultationTab(WTabHolder.WTab wTab)
    {
        m_tab = wTab;



        GUIHelper.setTheme("spinnery:dark", m_tab.getAllWidgets());
    }

    protected void center()
    {

    }
}
