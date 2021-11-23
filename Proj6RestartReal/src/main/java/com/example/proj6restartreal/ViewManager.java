package com.example.proj6restartreal;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ViewManager {

    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    private Game game = new Game();
    private Menu menu = new Menu();
    private setInitialStage initStage;


    public Game getGame() {
        return game;
    }

    public Menu getMenu() {
        return menu;
    }

    public setInitialStage getInitStage() {
        return initStage;
    }

    public AnchorPane getMainPane() {
        return mainPane;
    }

    public ViewManager(){
        //Pane is the canvas we're putting stuff on
        mainPane = new AnchorPane();
        initStage = new setInitialStage(this);
        //Scene - how big the canvas is
        mainScene = new Scene(mainPane,Util.WIDTH,Util.HEIGHT);
        mainStage = new Stage();
        initStage.createBorder();
        mainStage.setScene(mainScene);
    }

    public Stage getMainStage(){
        return mainStage;
    }

    /**
     * @param:
     *      - square
     * Updates the square according to its properties
     * */
    public void updateSquare(Square square) {
        Image image = null;
        Rectangle rect = square.getRectangle();
        if (square.hasFire() || square.hasSmoke() || square.hasFF() || square.hasPoi() || square.hasVictim()) {
            if (square.hasFire()){
                image = new Image(Util.firePath);
            }
            if (square.hasSmoke()){
                image = new Image(Util.smokePath);
            }
            if (square.hasFF()){
                image = square.getFF().getImage();

            }
            if (square.hasPoi()) {
                image = new Image(Util.poiPath);
            }

            if (square.hasVictim()){
                image = new Image(Util.personPath);
            }
            ImagePattern pattern = new ImagePattern(image);
            rect.setFill(pattern);
        }
        else{
            if(square.isOutside()){
                rect.setFill(Color.GREEN);
            }
            else{
                rect.setFill(Color.WHITE);
            }
        }
    }

    /**
     * @param:
     *      - side, square, damage on that wall
     * Updates the edge according to damage taken
     * - 2: good edge
     * - 1: dashed edge
     * - 0: remove edge
     * TODO: Update edge
     * Problem: Need to know the damage on current edge
     * */
    public void updateEdge(int side, Square square, int damage){
        Edge edge = square.getEdge(side);
        Line line = edge.getLine();
        if(damage == 1){
            line.getStrokeDashArray().addAll(25d, 15d);
            line.setStroke(Color.ORANGERED);
        }
        else{
            mainPane.getChildren().remove(line);
        }
    }


    /**
     * Removes all object that compose status and then re-add them with updated values
     */
    public void updateStatus(){
        ArrayList<Text> status = initStage.getStatus();
        ArrayList<Rectangle> ffRect = initStage.getFfRect();
        for(int i = 0; i < status.size(); i++){
            mainPane.getChildren().remove(status.get(i));
        }
        for(int j = 0; j < ffRect.size(); j++){
            mainPane.getChildren().removeAll(ffRect.get(j));
        }
        initStage.setStatus();

    }

    /**
     * @param item
     * @param square
     * @param action
     * Checks which action to take
     */
    public void clickChoice(MenuItem item,Square square,int action){
        item.setDisable(false);
        item.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                game.takeAction(action,square);
                updateSquare(square);
                updateStatus();
            }
        });
    }

    /**
     * @param square
     * @param mouseEvent
     * Displays the menu, for available actions, enables the option
     */
    public void actionMenu(Square square, MouseEvent mouseEvent) {
        FirefighterLogic ffLogic = game.firefighterLogic;
        menu.disableAll();
        menu.getCm().show(square.getRectangle(),mouseEvent.getScreenX(),mouseEvent.getScreenY());
        ArrayList<Integer> actions = ffLogic.getActions(square);
        for(int i = 0; i < actions.size();i++){
            MenuItem item = menu.getMenuItems().get(actions.get(i));
            clickChoice(item,square,actions.get(i));
        }
    }
}
