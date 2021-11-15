package com.example.project6_fx;

import javafx.scene.image.Image;

public class Firefighter {
    /** imagePath can be found like this
     * private File ffBlackPath = new File("/Users/angeldong/Desktop/CSCI5448/OOAD/Project6/project6_fx/src/main/graphics/black_ff.png").toURI().toString();
     * private File ffBluePath = new File("/Users/angeldong/Desktop/CSCI5448/OOAD/Project6/project6_fx/src/main/graphics/blue_ff.png").toURI().toString();
     * private File ffGreenPath = new File("/Users/angeldong/Desktop/CSCI5448/OOAD/Project6/project6_fx/src/main/graphics/green_ff.png").toURI().toString();
     * private File ffRedPath = new File("/Users/angeldong/Desktop/CSCI5448/OOAD/Project6/project6_fx/src/main/graphics/red_ff.png").toURI().toString();
     */
    private  Square location = null;
    private Image image = null;
    Firefighter(Square square){
        this.location = square;
    }

    public void setImage(String imagePath){
        //TODO: Fix this once images figured out
        this.image = new Image(imagePath);
    }

    public Image getImage(){
        return this.image;
    }

    public Square getLoc(){
        return location;
    }

    public void setLoc(Square location){
        this.location = location;
    }
}
