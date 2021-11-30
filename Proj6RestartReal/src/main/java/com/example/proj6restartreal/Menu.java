package com.example.proj6restartreal;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class Menu {
    private ContextMenu cm = new ContextMenu();
    private ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
    public void createMenu(){
        cm.getItems().add(getMenuItems("move"));
        cm.getItems().add(getMenuItems("drag"));
        cm.getItems().add(getMenuItems("hose"));
        cm.getItems().add(getMenuItems("chop"));

    }
    private MenuItem getMenuItems(String menuName){
        Label menuLabel = new Label(menuName);
        menuLabel.setStyle("-fx-padding: 5 10 5 10");
        menuLabel.setFont(Font.font("SansSerif"));
        MenuItem mi = new MenuItem();
        mi.setGraphic(menuLabel);
        this.menuItems.add(mi);
        return mi;
    }

    public ContextMenu getCm() {
        return cm;
    }

    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void disableAll(){
        for(int i = 0; i < menuItems.size(); i++){
            menuItems.get(i).setDisable(true);
        }
    }


}
