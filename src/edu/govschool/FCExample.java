package edu.govschool;

import java.io.File;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class FCExample extends Application
{
    private static Button btn;
    private static FileChooser fc;
    private static Image image;
    private static ImageView imageView;
    
    public static void main(String[] args)
    {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage)
    {
        btn = new Button("I'm here for no reason!");
        fc = new FileChooser();
        imageView = new ImageView();
        
        fc.setTitle("Choose photo!");
        File file = fc.showOpenDialog(primaryStage);
        if (file != null) {
            image = new Image(file.toURI().toString());
            imageView.setImage(image);
        }
        
        Scene scene = new Scene(new VBox(imageView, btn));
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("FileChooser Example");
        primaryStage.show();
    }
}