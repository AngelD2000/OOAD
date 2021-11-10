package com.example.project6_fx;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class FlashpointController {

    private Flashpoint startGame = new Flashpoint();
    //need a map
    @FXML
    protected void onStartGameClick() {
        //Need to render a new page with the map
//        Stage stage = new Stage();
//        //Second argument is a map
//        startGame.game(stage);
    }
}