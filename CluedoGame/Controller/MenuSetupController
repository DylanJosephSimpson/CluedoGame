package Controller;

import View.MenuSetup;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MenuSetupController {

    public MenuSetupController(){
        MenuSetup.getMenuContainer().addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                MenuSetup.getGameTitlePanel().setBounds(MenuSetup.getMenuWindow().getSize().width / 4, MenuSetup.getMenuWindow().getSize().height / 4, MenuSetup.getMenuWindow().getSize().width / 2, MenuSetup.getMenuWindow().getSize().height / 4);
                MenuSetup.getGameTitleName().setBounds(MenuSetup.getGameTitlePanel().getBounds().x / 3, MenuSetup.getGameTitlePanel().getBounds().y / 12, MenuSetup.getGameTitlePanel().getBounds().width, MenuSetup.getGameTitlePanel().getBounds().height);
                MenuSetup.getGameTitleName().setFont(new Font("Times New Roman", Font.BOLD, (MenuSetup.getMenuWindow().getSize().width - MenuSetup.getMenuWindow().getSize().height) / 4));
                MenuSetup.getStartButton().setBounds(MenuSetup.getMenuWindow().getSize().width / 4, MenuSetup.getMenuWindow().getSize().height / 2, MenuSetup.getMenuWindow().getSize().width / 2, MenuSetup.getMenuWindow().getSize().height / 8);
            }
        });
    }

}
