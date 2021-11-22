package com.example.proj6restartreal;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

public class ViewManager {

    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    private Game game = new Game();
    private Menu menu = new Menu();

    public Game getGame() {
        return game;
    }

    public Menu getMenu() {
        return menu;
    }

    public void createBorder(){
        for(int i = 0; i < Util.mapWidth; i++){
            for(int j = 0; j < Util.mapHeight; j++){
                if(i == 0 || i == Util.mapWidth-1 || j == 0 || j ==Util.mapHeight-1){
                    Rectangle rect = new Rectangle(i * Util.length, j * Util.length, Util.length,Util.length);
                    rect.setFill(Color.GREEN);
                    mainPane.getChildren().add(rect);
                }
            }
        }
    }

    public ViewManager(){
        //Pane is the canvas we're putting stuff on
        mainPane = new AnchorPane();
        //Scene - how big the canvas is
        mainScene = new Scene(mainPane,Util.WIDTH,Util.HEIGHT);
        mainStage = new Stage();
        createBorder();
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
     * @param square
     * For every square, loops through the edges and draws the lines
     * */
    public void drawWall(Square square) {
        for(int i = 0; i < 4; i++){
            Edge edge = square.getEdge(i);
            if(edge != null){
                Line line = edge.getLine();
                double x = square.getRectangle().getX();
                double y = square.getRectangle().getY();

                if(i == 0){
                    line.setStartX(x);
                    line.setStartY(y);
                    line.setEndX(Util.length + x);
                    line.setEndY(y);
                }
                else if(i == 1){
                    line.setStartX(x);
                    line.setStartY(y + Util.length);
                    line.setEndX(Util.length + x);
                    line.setEndY(y + Util.length);

                }
                else if(i == 2){
                    line.setStartX(Util.length + x);
                    line.setStartY(y);
                    line.setEndX(Util.length + x);
                    line.setEndY(y+ Util.length);
                }

                else if(i == 3){
                    line.setStartX(x);
                    line.setStartY(y);
                    line.setEndX(x);
                    line.setEndY(y + Util.length);
                }
                mainPane.getChildren().add(line);
            }
        }
    }

    /**
     * Uses the map iterator to loop through every single square and draws them
     * */
    public void drawMap(){
        Map map = game.getMap();
        while (map.hasNext()) {
            Square square = map.next();
            Rectangle rectangle = square.getRectangle();
            if(square.isOutside()){
                rectangle.setFill(Color.GREEN);
            }
            else{
                rectangle.setFill(Color.WHITE);
            }
            rectangle.setY(square.getX()*Util.length);
            rectangle.setX(square.getY()*Util.length);
            rectangle.setWidth(Util.length);
            rectangle.setHeight(Util.length);
            mainPane.getChildren().add(rectangle);
            updateSquare(square);
            drawWall(square);
        }
        map.resetIterator();
    }


    /**
     * Creates new rectangles and render the firefighter images
     */
    public void displayFFTurn(){
        int len = 40;
        int currentFireFighter = game.firefighterLogic.company.activeFirefighter;
        String currentFFPath = Util.firefighterImages[currentFireFighter];
        int height = 90;
        int displayed = 0;
        int i = 0;
        while(displayed < Util.numFirefighters){
            Rectangle rect;
            Image ffImage;
            ImagePattern ffPattern;
            if(i == currentFireFighter){
                rect = new Rectangle(Util.setDisplayX, 70,len,len);
                ffImage = new Image(currentFFPath);
            }
            else{
                height += (len + 10);
                rect = new Rectangle(Util.setDisplayX, height,len,len);
                ffImage = new Image(Util.firefighterImages[i]);
            }
            displayed++;

            ffPattern = new ImagePattern(ffImage);
            rect.setFill(ffPattern);
            mainPane.getChildren().add(rect);
            if(i == Util.numFirefighters){
                i = 0;
            }
            i++;
        }
    }

    /**
     * All texts on the canvas, gets the numbers from game since game knows about the numbers
     * Problem: Edge doesn't know if it is damaged or not
     * */
    public void displayStatus() {
        Text currentFFStr = new Text("Current Firefighter: ");
        currentFFStr.setFont(Font.font("SansSerif"));
        currentFFStr.setX(Util.setDisplayX);
        currentFFStr.setY(50);

        displayFFTurn();

        Text nextFFStr = new Text("Next Firefighters: ");
        nextFFStr.setFont(Font.font("SansSerif"));
        nextFFStr.setX(Util.setDisplayX);
        nextFFStr.setY(130);

        Text peopleSaved = new Text("Victims Saved: " + game.building.saved);
        peopleSaved.setFont(Font.font("SansSerif"));
        peopleSaved.setX(Util.setDisplayX);
        peopleSaved.setY(370);

        Text peoplePerished = new Text("Victims Perished: " + game.building.perished);
        peoplePerished.setFont(Font.font("SansSerif"));
        peoplePerished.setX(Util.setDisplayX);
        peoplePerished.setY(390);

        Text damage = new Text("Building Integrity: " + game.getDamage());
        damage.setFont(Font.font("SansSerif"));
        damage.setX(Util.setDisplayX);
        damage.setY(410);

        mainPane.getChildren().addAll(currentFFStr,damage, peopleSaved, peoplePerished,nextFFStr);
    }

    public void setMenu(){
        Map map = game.getMap();
        while(map.hasNext()){
            Square square = map.next();
            square.getRectangle().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    actionMenu(square,mouseEvent);
                }
            });
        }
    }

    public void clickChoice(MenuItem item,Square square,String action){
        FirefighterLogic ffLogic = game.firefighterLogic;
        item.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(action == "move"){
                    ffLogic.move(square);
                }
                else if(action == "drag"){
                    ffLogic.drag(square);
                }
                else if(action == "hose"){
                    ffLogic.hose(square);
                }
                else if(action == "chop"){
                    ffLogic.chop(square);
                }
                updateSquare(square);
            }
        });
    }

    public void actionMenu(Square square, MouseEvent mouseEvent) {
        FirefighterLogic ffLogic = game.firefighterLogic;
        menu.disableAll();
        menu.getCm().show(square.getRectangle(),mouseEvent.getScreenX(),mouseEvent.getScreenY());
        ArrayList<Integer> actions = ffLogic.getActions(square);
        for(int i = 0; i < actions.size();i++){
            if(actions.get(i) == 0){
                menu.getMenuItems().get(0).setDisable(false);
                clickChoice(menu.getMenuItems().get(0),square,"move");
            }
            else if(actions.get(i) == 1){
                menu.getMenuItems().get(1).setDisable(false);
                clickChoice(menu.getMenuItems().get(1),square,"drag");
            }
            else if(actions.get(i) == 2){
                menu.getMenuItems().get(2).setDisable(false);
                clickChoice(menu.getMenuItems().get(2),square,"hose");

            }
            else if(actions.get(i) == 3){
                menu.getMenuItems().get(3).setDisable(false);
                clickChoice(menu.getMenuItems().get(3),square,"chop");
            }
        }
    }
}
