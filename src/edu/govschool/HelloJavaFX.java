/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.govschool;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

/**
 *
 * @author Glaedwyn
 */
public class HelloJavaFX extends Application {

    private static Button btn;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        btn = new Button();
        btn.setText("Click me please!");
        btn.setOnAction(e -> buttonClick());

        BorderPane pane = new BorderPane();
        pane.setCenter(btn);

        Scene scene = new Scene(pane, 300, 250);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Welcome to JavaFX");
        primaryStage.show();
    }

    private void buttonClick() {
        if (btn.getText() == "Click me Please!")
            btn.setText("You clicked me!");
        
        else
            btn.setText("Click me Please!");
    }
}

