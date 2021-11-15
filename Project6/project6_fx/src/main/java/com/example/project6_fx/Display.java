package com.example.project6_fx;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;

import java.io.File;

public class Display {
    private File firePath = new File("/Users/angeldong/Desktop/CSCI5448/OOAD/Project6/project6_fx/src/main/graphics/fire.png");
    private File smokePath = new File("/Users/angeldong/Desktop/CSCI5448/OOAD/Project6/project6_fx/src/main/graphics/smoke.png");
    private File poiPath = new File("/Users/angeldong/Desktop/CSCI5448/OOAD/Project6/project6_fx/src/main/graphics/poi.png");
    private File personPath = new File("/Users/angeldong/Desktop/CSCI5448/OOAD/Project6/project6_fx/src/main/graphics/person.png");
    private File blankPath = new File("/Users/angeldong/Desktop/CSCI5448/OOAD/Project6/project6_fx/src/main/graphics/blank.png");

    public void displayElement(Square square){
        Image image = new Image(blankPath.toURI().toString());

        if(square.hasFire())
            image = new Image(firePath.toURI().toString());
        else if (square.hasSmoke())
            image = new Image(smokePath.toURI().toString());
        if(square.hasFF())
            image = square.getFF().getImage();
        if(square.hasPoi())
            image = new Image(poiPath.toURI().toString());
        if(square.hasVictim())
            image = new Image(personPath.toURI().toString());

        ImagePattern pattern = new ImagePattern(image);
        square.rect.setFill(pattern);
        ImageView view = new ImageView(image);
        view.setFitHeight(60);
        view.setFitWidth(60);
    }
}
