package com.example.proj6restartreal;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.File;

public class Display {
    private static String firePath = new File("/Users/angeldong/Desktop/CSCI5448/OOAD/Project6/project6_fx/src/main/graphics/fire.png").toURI().toString();
    private static String smokePath = new File("/Users/angeldong/Desktop/CSCI5448/OOAD/Project6/project6_fx/src/main/graphics/smoke.png").toURI().toString();
    private static String poiPath = new File("/Users/angeldong/Desktop/CSCI5448/OOAD/Project6/project6_fx/src/main/graphics/poi.png").toURI().toString();
    private static String personPath = new File("/Users/angeldong/Desktop/CSCI5448/OOAD/Project6/project6_fx/src/main/graphics/person.png").toURI().toString();
    private static String blankPath = new File("/Users/angeldong/Desktop/CSCI5448/OOAD/Project6/project6_fx/src/main/graphics/blank.png").toURI().toString();

    public void displayElement(Square square){
        Image image = null;
        if(square.hasFire()||square.hasSmoke()||square.hasFF()||square.hasPoi()||square.hasVictim()){
            if(square.hasFire())
                image = new Image(firePath);
            if(square.hasSmoke())
                image = new Image(smokePath);
            if(square.hasFF())
                image = square.getFF().getImage();
            if(square.hasPoi())
                image = new Image(poiPath);
            if(square.hasVictim())
                image = new Image(personPath);

            Rectangle rect = square.getRectangle();
            ImagePattern pattern = new ImagePattern(image);
            rect.setFill(pattern);
            ImageView view = new ImageView(image);
            view.setFitHeight(60);
            view.setFitWidth(60);
        }
    }
}
