package com.example.project6_fx;

import javafx.scene.image.Image;

public class Firefighter {
    private  Square location = null;
    private Image image = null;
    Firefighter(Square square){
        this.location = square;
    }

    public void setImage(String imagePath){
        //TODO: Fix this once images figured out
        this.image = new ImageIcon((imagePath)).getImage();
    }

    public Square getLoc(){
        return location;
    }

    public void setLoc(Square location){
        this.location = location;
    }
}
