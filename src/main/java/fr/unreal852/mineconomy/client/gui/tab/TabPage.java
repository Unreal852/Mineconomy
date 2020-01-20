package fr.unreal852.mineconomy.client.gui.tab;

import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import spinnery.widget.WWidget;

public abstract class TabPage
{
    private BaseTabScreen m_ownerTabScreen;
    private Identifier    m_tabIdentifier;
    private Text          m_tabPageName   = null;
    private boolean       m_hideTabButton = false;

    public TabPage(BaseTabScreen tabScreen, Identifier tabIdentifier, Text tabPageName)
    {
        m_ownerTabScreen = tabScreen;
        m_tabIdentifier = tabIdentifier;
        m_tabPageName = tabPageName;
    }

    public BaseTabScreen getOwnerTabScreen()
    {
        return m_ownerTabScreen;
    }

    public Identifier getIdentifier()
    {
        return m_tabIdentifier;
    }

    public Text getTabPageName()
    {
        return m_tabPageName;
    }

    public boolean isHidden()
    {
        return m_hideTabButton;
    }

    public void setHidden(boolean hidden)
    {
        m_hideTabButton = hidden;
        getOwnerTabScreen().buildTabsButtons();
    }

    public abstract WWidget[] getWidgets();

    public abstract void onTabSelectionChanged(boolean isSelected);
}
