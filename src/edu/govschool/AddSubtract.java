package edu.govschool;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;



public class AddSubtract extends Application
{
    private static Button btnAdd;
    private static Button btnSubtract;
    private static Label lbl;
    private static int counter = 0;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        // Create the add button
        btnAdd = new Button();
        btnAdd.setText("Add");
        // setOnAction() expects an object that implements EventHandler<T>
        // So, let's use a lambda!
        btnAdd.setOnAction(e -> btnAddClick());

        // Create the subtract button
        btnSubtract = new Button();
        btnSubtract.setText("Subtract");
        btnSubtract.setOnAction(e -> btnSubtractClick());

        // Create the label
        lbl = new Label();
        // The autoboxed Integer class provides this toString(Integer) method
        lbl.setText(Integer.toString(counter));

        // Add the elements to an HBox pane. HBoxes organize elements 
        // horizontally. The constructor argument specifies the spacing 
        // between elements in pixels.
        //
        // HBox.getChildren() returns a list of the child elements. 
        // addAll() lets you add several elements at once.
        HBox pane = new HBox(10);
        pane.getChildren().addAll(lbl, btnAdd, btnSubtract);

        // Add the layout pane to the scene
        Scene scene = new Scene(pane, 200, 75);

        // Add the stage to the scene and show the window
        primaryStage.setScene(scene);
        primaryStage.setTitle("Add/Sub");
        primaryStage.show();
    }

    private void btnAddClick()
    {
        counter++;
        lbl.setText(Integer.toString(counter));
    }

    private void btnSubtractClick()
    {
        counter--;
        lbl.setText(Integer.toString(counter));
    }
}