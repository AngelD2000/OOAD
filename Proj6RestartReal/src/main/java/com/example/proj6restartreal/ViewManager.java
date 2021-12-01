package com.example.proj6restartreal;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ViewManager {

    private final AnchorPane mainPane;
    private final Stage mainStage;

    private final Game game = new Game();
    private final Menu menu = new Menu(this);
    private final setWindow window;

    public ViewManager(){
        //Pane is the canvas we're putting stuff on
        mainPane = new AnchorPane();
        window = new setWindow(this);
        //Scene - how big the canvas is
        Scene mainScene = new Scene(mainPane, Util.WIDTH, Util.HEIGHT);
        mainStage = new Stage();
        window.createBorder();
        mainStage.setScene(mainScene);
    }

    /**
     * These are what needs to be set up for UI during the initial launch for the program
     */
    public void initialDisplay(){
        window.drawMap(true);
        menu.createMenu();
        menu.setMenu();
        menu.createButton();
        getMainPane().getChildren().add(menu.getButton());
    }

    /**
     * @param: square
     * Updates the square with images based on the properties it contains
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
            assert image != null;
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
     * @param: side, square
     * Updates the walls according to damage taken
     * 2 - Normal wall
     * 1 - Partial wall
     * 0 - Remove wall
     * */
    public void updateEdge(int side, Square square){
        Edge edge = square.getEdge(side);
        int damage = edge.getDamage();
        Line line = edge.getLine();
        if(damage == 2){
            line.setStroke(Color.BLACK);
        }
        else if(damage == 1){
            line.getStrokeDashArray().addAll(25d, 15d);
            line.setStroke(Color.ORANGERED);
        }
        else if (damage == 0){
            square.setEdge(side, null);
            Square square2 = getGame().getMap().getSquareInDirection(square, side);
            int dir2 = Util.getOppositeDirection(side);
            square2.setEdge(dir2, null);
            mainPane.getChildren().remove(line);
        }
    }



    /**
     * Display the updated status of the current game on the right side of the board
     * - Number of turns
     * - Which FireFighter
     * - How many people are saved
     * - How many people died
     * - Building damage
     */
    public void updateStatus(){
        ArrayList<Text> status = window.getStatus();
        ArrayList<Rectangle> ffRect = window.getFfRect();
        for (Text text : status) {
            mainPane.getChildren().remove(text);
        }
        for (Rectangle rectangle : ffRect) {
            mainPane.getChildren().removeAll(rectangle);
        }
        window.setStatus();

    }

    /**
     * @param action
     * Takes the action user choose
     * Reset the menu after each action is taken
     * Updates map
     * Updates the status
     */
    public void clickChoice(MenuItem item,Square square,int action){
        item.setDisable(false);
        item.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                game.takeAction(action,square);
                menu.setMenu();
                //This currently works
                window.drawMap(false);
                updateStatus();
            }
        });
    }

    /**
     * @param mouseEvent
     * Displays the menu, for available actions enable the option for user to choose
     */
    public void actionMenu(Square square, MouseEvent mouseEvent) {
        FirefighterLogic ffLogic = game.firefighterLogic;
        menu.disableAll();
        menu.getCm().show(square.getRectangle(),mouseEvent.getScreenX(),mouseEvent.getScreenY());
        ArrayList<Integer> actions = ffLogic.getActions(square);
        for (Integer action : actions) {
            MenuItem item = menu.getMenuItems().get(action);
            clickChoice(item, square, action);
        }
    }

    /**
     * @param message
     * Displays the message passed in after clearing the whole screen
     */
    public void endUI(String message){
        mainPane.getChildren().clear();
        StackPane stack = new StackPane();
        Text finalMessage = new Text(message);
        finalMessage.setFont(Font.font("SansSerif", 60));
        finalMessage.setTextAlignment(TextAlignment.CENTER);
        stack.getChildren().add(finalMessage);
        StackPane.setAlignment(finalMessage, Pos.CENTER);
        Scene stackScene = new Scene(stack, Util.WIDTH, Util.HEIGHT);
        mainStage.setScene(stackScene);
    }

    public Game getGame() {
        return game;
    }

    public Menu getMenu() {
        return menu;
    }

    public setWindow getWindow() {
        return window;
    }

    public AnchorPane getMainPane() {
        return mainPane;
    }

    public Stage getMainStage(){
        return mainStage;
    }

}
