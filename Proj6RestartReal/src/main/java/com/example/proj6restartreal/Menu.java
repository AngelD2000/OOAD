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
    private final ViewManager manager;
    private final ContextMenu cm = new ContextMenu();
    private final ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
    private final Button endButton = new Button("End Turn");

    Menu(ViewManager manager){
        this.manager = manager;
    }

    /**
     * Creates the interactive menu with options when each square is clicked
     */
    public void createMenu(){
        cm.getItems().add(getMenuItems("move"));
        cm.getItems().add(getMenuItems("drag"));
        cm.getItems().add(getMenuItems("hose"));
        cm.getItems().add(getMenuItems("chop"));

    }

    /**
     * @param menuName Pass in the name of menuItem want to create
     * @return The menuItem object from javafx
     */
    private MenuItem getMenuItems(String menuName){
        Label menuLabel = new Label(menuName);
        menuLabel.setStyle("-fx-padding: 5 10 5 10");
        menuLabel.setFont(Font.font("SansSerif"));
        MenuItem mi = new MenuItem();
        mi.setGraphic(menuLabel);
        this.menuItems.add(mi);
        return mi;
    }

    /**
     * At the beginning of each turn, disable all choices for each square, only when the action is available
     * will the choice be enabled
     */
    public void disableAll(){
        for (MenuItem menuItem : menuItems) {
            menuItem.setDisable(true);
        }
    }


    /**
     * Create the end turn button
     */
    public void createButton(){
        this.endButton.setMaxHeight(20);
        this.endButton.setMaxWidth(100);
        this.endButton.setLayoutX(Util.setDisplayX);
        this.endButton.setLayoutY(450);
        this.endButton.setFont(Font.font("SansSerif"));

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
        endButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                manager.getGame().endTurn();
                manager.getWindow().drawMap(false);
                manager.updateStatus();
            }
        });
    }

    public ContextMenu getCm() {
        return cm;
    }

    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }

    public Button getButton() {
        return endButton;
    }
}
