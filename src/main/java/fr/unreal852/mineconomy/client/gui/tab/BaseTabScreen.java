package fr.unreal852.mineconomy.client.gui.tab;

import fr.unreal852.mineconomy.client.gui.GUIHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import spinnery.client.BaseScreen;
import spinnery.widget.WButton;
import spinnery.widget.WInterface;
import spinnery.widget.WWidget;

import java.util.*;

public class BaseTabScreen extends BaseScreen
{
    private final Map<Identifier, TabPage> m_tabPages   = new LinkedHashMap<>();
    private final List<WButton>            m_tabButtons = new ArrayList<>();
    private final WInterface               m_tabs;
    private       TabPage                  m_currentTab = null;

    public BaseTabScreen()
    {
        super();
        setInterface(new WInterface(0, 0, 0, 1, 1));
        m_tabs = new WInterface(0, 0, 0, 1, 1);
        m_tabs.setInterface(getInterface());
        getInterface().add(m_tabs);
        center();
    }

    public WInterface getTabsInterface()
    {
        return m_tabs;
    }

    public void setHeight(double height)
    {
        getInterface().setSizeY(height);
        m_tabs.setSizeY(height);
    }

    public void setTabsPanelWidth(double width)
    {
        getTabsInterface().setSizeX(width);
        center();
    }

    public void setTabContentPanelWidth(double width)
    {
        getInterface().setSizeX(width);
        center();
    }

    public void registerTabPage(TabPage tabPage)
    {
        if (m_tabPages.containsKey(tabPage.getIdentifier()))
            return;
        m_tabPages.put(tabPage.getIdentifier(), tabPage);
        getInterface().add(tabPage.getWidgets());
    }

    public void unregisterTabPage(Identifier identifier)
    {
        if (!m_tabPages.containsKey(identifier))
            return;
        TabPage tabPage = m_tabPages.remove(identifier);
        getInterface().remove(tabPage.getWidgets());
    }

    public void setCurrentTab(int index)
    {
        TabPage[] tabPages = m_tabPages.values().toArray(new TabPage[0]);
        if (index < 0 || index > tabPages.length)
            return;
        setCurrentTab(tabPages[index].getIdentifier());
    }

    public void setCurrentTab(Identifier identifier)
    {
        if (!m_tabPages.containsKey(identifier) || m_currentTab == m_tabPages.get(identifier))
            return;
        if (m_currentTab != null)
            m_currentTab.onTabSelectionChanged(false);
        m_currentTab = m_tabPages.get(identifier);
        m_currentTab.onTabSelectionChanged(true);
    }

    public void center()
    {
        double midScreenWidth = (float) MinecraftClient.getInstance().getWindow().getScaledWidth() / 2.0F;
        double midScreenHeight = (float) MinecraftClient.getInstance().getWindow().getScaledHeight() / 2.0F;
        double panelsY = midScreenHeight - (getInterface().getSizeY() / 2.0);
        getInterface().setPositionX(midScreenWidth - ((getInterface().getSizeX() / 2.0D) + (m_tabs.getSizeX() / 2.0D) - 10) / 2.0D);
        getInterface().setPositionY(panelsY);
        m_tabs.setPositionX(getInterface().getPositionX() - (m_tabs.getSizeX() + 5));
        m_tabs.setPositionY(panelsY);
    }

    public void buildTabsButtons()
    {
        int panelMarginX = 5;
        int panelMarginY = 20;

        if (m_tabButtons.size() > 0)
            getInterface().remove(m_tabButtons.toArray(new WButton[0]));

        int index = 1;
        for (TabPage tabPage : m_tabPages.values())
        {
            if(tabPage.isHidden())
                continue;
            WButton tabButton = GUIHelper.createButton(getInterface(), (int) getTabsInterface().getPositionX() + panelMarginX, (int) getTabsInterface().getPositionY() + (panelMarginY * index), (int) getTabsInterface().getSizeX() - (panelMarginX * 2), 15);
            tabButton.setLabel(tabPage.getTabPageName().asString());
            tabButton.setOnMouseClicked(() ->
            {
                if (!tabButton.getFocus())
                    return;
                setCurrentTab(tabPage.getIdentifier());
            });
            getInterface().add(tabButton);
            m_tabButtons.add(tabButton);
            index++;
        }
    }
}
