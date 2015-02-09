/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.govschool.modals.tictactoePersonal;

import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.geometry.*;

/**
 *
 * @author Glaedwyn
 */
public class MessageBox {
    /**
     * show a MessageBox.
     * Displays a modal dialog box with the given message and title
     * @param msg the message to display
     * @param title the title of the message box
     */
    public static void show(String msg, String title)
    {
        Stage stage = new Stage();
        
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setMinWidth(250);
        
        Label lbl = new Label();
        lbl.setText(msg);
        
        
        Button btn = new Button();
        btn.setText("OK");
        btn.setOnAction(e -> stage.close());
        
        VBox pane = new VBox(20);
        pane.getChildren().addAll(lbl, btn);
        pane.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
