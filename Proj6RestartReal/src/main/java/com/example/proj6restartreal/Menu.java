package com.example.proj6restartreal;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class Menu {
    private ViewManager manager;
    private ContextMenu cm = new ContextMenu();
    private ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
    private Button button = new Button("End Turn");

    Menu(ViewManager manager){
        this.manager = manager;
    }


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

    public void createButton(){
        button.setMaxHeight(20);
        button.setMaxWidth(100);
        button.setLayoutX(Util.setDisplayX);
        button.setLayoutY(450);
        button.setFont(Font.font("SansSerif"));

    }

    public Button getButton() {
        return button;
    }
    /**
     * Wait for mouse click event for all squares
     */
    public void setMenu(){
        Map map = manager.getGame().getMap();
        while(map.hasNext()){
            Square square = map.next();
            square.getRectangle().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    manager.actionMenu(square,mouseEvent);
                }
            });
        }
        map.resetIterator();
        Button button = manager.getMenu().getButton();
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                manager.getGame().endTurn();
                manager.updateStatus();
            }
        });
    }
}
