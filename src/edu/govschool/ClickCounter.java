import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
 
public class ClickCounter extends Application
{
    private static Button btn;
    private static Label lbl;
    private static int clickCount = 0;
    
    public static void main(String[] args)
    {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage)
    {
        btn = new Button();
        btn.setText("Click me please!");
        btn.setOnAction(e -> buttonClick());
        
        lbl = new Label();
        lbl.setText("You have not clicked the button.");
        
        BorderPane pane = new BorderPane();
        pane.setTop(lbl);
        pane.setCenter(btn);
        
        Scene scene = new Scene(pane, 250, 150);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Click Counter");
        primaryStage.show();
    }
    
    public void buttonClick()
    {
        clickCount++;
        if (clickCount == 1) {
            lbl.setText("You've clicked the button once.");
        } else {
            lbl.setText("You've clicked the button " + clickCount + " times.");
        }
    }
}