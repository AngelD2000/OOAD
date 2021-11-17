package com.example.proj6restartreal;

import javafx.scene.image.Image;

public class Firefighter {
    private Square location = null;
    private Image image = null;
    Firefighter(Square square){
        this.location = square;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Firefighter other = (Firefighter) obj;
        return (location.equals(other.getLoc()) && image.equals(other.getImage()));
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
        this.location.removeFF();
        this.location = location;
        location.setFF(this);
    }
}
