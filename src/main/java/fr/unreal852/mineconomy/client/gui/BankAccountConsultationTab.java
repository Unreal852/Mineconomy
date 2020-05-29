package fr.unreal852.mineconomy.client.gui;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import spinnery.widget.WTabHolder;

@Environment(EnvType.CLIENT)
public class BankAccountConsultationTab
{
    private WTabHolder.WTab _tab;

    public BankAccountConsultationTab(WTabHolder tabHolder,WTabHolder.WTab wTab)
    {
        _tab = wTab;



        GUIHelper.setTheme("spinnery:dark", _tab.getAllWidgets());
    }

    protected void center()
    {

    }
}
