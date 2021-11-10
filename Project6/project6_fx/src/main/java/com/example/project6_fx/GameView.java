package com.example.project6_fx;

public class GameView implements GameViewInterface {
    public void renderSquare(SimpleSquare square){
        Flashpoint game = new Flashpoint();
        boolean isOutside = square.isOutside();
        if(isOutside){
            //Render squares with no attributes
            //game.displaySquare(square,"green");
            //If there is a firefighter render the firefighter
//            if(square.firefighter){
//                //Render firefighter
//                System.out.println("Place a firefighter here!");
//            }
        }
//        else{
//            boolean isFire = square.isFire();
//            boolean isSmoke = square.isSmoke();
//            boolean isPOI = square.POI();
//            if(isFire){
//                //Render fire
//                System.out.println("Place a fire here!");
//            }
//            if(isSmoke){
//                //Render Smoke
//                System.out.println("Place a smoke here!");
//            }
//            if(isPOI){
//                //Render POI
//                System.out.println("Place a poi here!");
//            }
//        }
    }
    public void ActionMenu(){

    }
}
