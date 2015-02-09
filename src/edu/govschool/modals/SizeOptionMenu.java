/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.govschool.modals;

import java.util.Random;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.event.*;
import javafx.geometry.*;

/**
 *
 * @author Glaedwyn
 */
public class SizeOptionMenu {
    static Stage stage;
    static boolean confirm;
    static int choice = 3;
     /**
     * Display a confirmation box.
     * @param msg The message to display
     * @param title The title of the box
     * @param yesText The text for the confirm button
     * @param noText The text for the deny button
     * @return <code>true</code> if the user confirms, <code>false</code> otherwise
     */
    public static int show() {
        // We will use this boolean to return the user's choice
        Button size3 = new Button();
        size3.setText("3 x 3");
        size3.setOnAction(e -> {
            choice = 3;
            stage.close();
        });
        Button size4 = new Button();
        size4.setText("4 x 4");
        size4.setOnAction(e -> {
            choice = 4;
            stage.close();
        });
        Button size5 = new Button();
        size5.setText("5 x 5");
        size5.setOnAction(e -> {
            choice = 5;
            stage.close();
        });
        Button rand = new Button();
        rand.setText("Random size");
        rand.setOnAction(e -> {
            choice = 3 + new Random().nextInt(7);
            stage.close();
        });
        // Again, we are initializing a modal stage
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Size selection");
        // Create our various GUI elements
        
        // Here is an example of using multiple layout panes to organize our
        // elements. The buttons are arranged horizontally in an HBox, but the
        // Label and this HBox are arranged vertically in a VBox.
        
        HBox box = new HBox(20);
        box.getChildren().addAll(size3, size4, size5, rand);
        // Let's set the scene!
        Scene scene = new Scene(box);
        stage.setScene(scene);
        stage.showAndWait();
        
        return choice;
    }
    
    /**
     * Event handler for the confirmation button.
     * Closes the stage and sets that the user confirmed.
     */
    private static void confirmBtn_Click()
    {
        stage.close();
        confirm = true;
    }
    
    /**
     * Event handler for the deny button.
     * Closes the stage and sets that the user denied.
     */
    private static void denyBtn_Click()
    {
        stage.close();
        confirm = false;
    }
}
