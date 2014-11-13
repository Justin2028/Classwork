package edu.govschool;

import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class SceneSwitcher extends Application
{
    // Elements of ClickCounter scene
    int clickCount = 0;
    Label lblClicks;
    Button btnClickMe;
    Button btnSwitch2;
    Scene scene1;

    // Elements of AddSubtract scene
    int count = 0;
    Label lblCounter;
    Button btnAdd;
    Button btnSub;
    Button btnSwitch1;
    Scene scene2;

    // The overall stage
    Stage stage;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        stage = primaryStage;

        // Build the ClickCounter scene
        lblClicks = new Label();
        lblClicks.setText("You have not clicked the button.");
        
        btnClickMe = new Button();
        btnClickMe.setText("Click me please!");
        btnClickMe.setOnAction(e -> btnClickMe_Click());

        btnSwitch2 = new Button();
        btnSwitch2.setText("Switch!");
        btnSwitch2.setOnAction(e -> btnSwitch2_Click());

        VBox pane1 = new VBox(10);
        pane1.getChildren().addAll(lblClicks, btnClickMe, btnSwitch2);
        scene1 = new Scene(pane1, 250, 150);

        // Build the AddSubtract scene
        lblCounter = new Label();
        lblCounter.setText(Integer.toString(count));

        btnAdd = new Button();
        btnAdd.setText("Add");
        btnAdd.setOnAction(e -> btnAdd_Click());

        btnSub = new Button();
        btnSub.setText("Subtract");
        btnSub.setOnAction(e -> btnSub_Click());

        btnSwitch2 = new Button();
        btnSwitch2.setText("Switch!");
        btnSwitch2.setOnAction(e -> btnSwitch1_Click());

        HBox pane2 = new HBox(10);
        pane2.getChildren().addAll(lblCounter, btnAdd, btnSub, btnSwitch2);
        scene2 = new Scene(pane2, 300, 75);

        primaryStage.setScene(scene1);
        primaryStage.setTitle("Scene Switcher");
        primaryStage.show();
    }

    // ClickCounter event handlers
    private void btnClickMe_Click()
    {
        clickCount++;
        if (clickCount == 1) {
            lblClicks.setText("You have clicked once.");
        } else {
            lblClicks.setText("You have clicked " + clickCount + " times.");
        }
    }

    private void btnSwitch2_Click()
    {
        stage.setScene(scene2);
    }

    // AddSubtract event handlers
    private void btnAdd_Click()
    {
        count++;
        lblCounter.setText(Integer.toString(count));
    }

    private void btnSub_Click()
    {
        count--;
        lblCounter.setText(Integer.toString(count));
    }

    private void btnSwitch1_Click()
    {
        stage.setScene(scene1);
    }
}