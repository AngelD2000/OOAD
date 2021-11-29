package com.example.proj6restartreal;

import javafx.scene.image.Image;

public class Firefighter {
    private Square location = null;
    private Image image = null;
    private int saved = 0;
    Firefighter(Square square){
        this.location = square;
        this.location.setFF(this);
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
        this.image = new Image(imagePath);
    }

    public Image getImage(){
        return this.image;
    }

    public void setActionsSaved(int saved){
        this.saved = saved;
    }
    public int getActionsSaved(){
        return this.saved;
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
