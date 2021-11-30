package com.example.proj6restartreal;

import javafx.scene.image.Image;

public class Firefighter {
    public int[] location = {0,0};
    private Image image = null;
    private int saved = 0;
    private Map map;
    Firefighter(Map map, int x, int y){
        this.map = map;
        setLoc(x, y);
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
        return map.getLoc(location);
    }

    public void setLoc(int x, int y){
        map.getLoc(location).removeFF();
        location[0] = x;
        location[1] = y;
        map.getLoc(location).setFF(this);
    }
}
