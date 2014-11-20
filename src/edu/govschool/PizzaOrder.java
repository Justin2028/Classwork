package edu.govschool;

import edu.govschool.modals.*;
import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;

/**
 * Demonstrates the use of <code>TextField</code>s, <code>CheckBox</code>es, and
 * <code>RadioButton</code>s.
 * @author Mr. Davis
 */
public class PizzaOrder extends Application {
    
    // Our main stage
    private static Stage stage;
    
    // Customer information
    private static TextField txtName;
    private static TextField txtPhone;
    private static TextField txtAddress;
    
    // Pizza size options
    private static RadioButton rdoSmall;
    private static RadioButton rdoMedium;
    private static RadioButton rdoLarge;
    
    // Crust style options
    private static RadioButton rdoThin;
    private static RadioButton rdoThick;
    
    // Toppings options
    private static CheckBox chkPepperoni;
    private static CheckBox chkSausage;
    private static CheckBox chkOlives;
    private static CheckBox chkMushrooms;
    private static CheckBox chkTomatoes;
    private static CheckBox chkAnchovies;
    
    @Override
    public void start(Stage primaryStage) {
        // Set our main stage
        stage = primaryStage;
        
        // Create the title pane
        Text textHeading = new Text("BESTELL 'NE PIZZA JETZT");
        textHeading.setFont(new Font(20));
        HBox paneTop = new HBox(textHeading);
        paneTop.setPadding(new Insets(20, 10, 20, 10));
        
        /* Create the customer pane */
        // Name
        Label lblName = new Label("Name:");
        // setPrefWidth(double) sets the default width of the field.
        lblName.setPrefWidth(100);
        txtName = new TextField();
        // setPrefColumnCount(int) sets the number of characters to display at a
        // time.
        txtName.setPrefColumnCount(20);
        txtName.setPromptText("Enter the customer's name");
        txtName.setMaxWidth(Double.MAX_VALUE);
        HBox paneName = new HBox(lblName, txtName);
        
        // Phone Number
        Label lblPhone = new Label("Phone:");
        lblPhone.setPrefWidth(100);
        txtPhone = new TextField();
        txtPhone.setPrefColumnCount(20);
        txtPhone.setPromptText("Enter the customer's phone number");
        HBox panePhone = new HBox(lblPhone, txtPhone);
        
        // Address
        Label lblAddress = new Label("Address:");
        lblAddress.setPrefWidth(100);
        txtAddress = new TextField();
        txtAddress.setPrefColumnCount(20);
        txtAddress.setPromptText("Enter the customer's address");
        HBox paneAddress = new HBox(lblAddress, txtAddress);
        
        VBox paneCustomer = new VBox(10, paneName, panePhone, paneAddress);
        
        /* Create the order pane */
        // Pizza size
        Label lblSize = new Label("Size");
        rdoSmall = new RadioButton("Small");
        rdoMedium = new RadioButton("Medium");
        rdoLarge = new RadioButton("Large");
        rdoMedium.setSelected(true);
        ToggleGroup groupSize = new ToggleGroup();
        rdoSmall.setToggleGroup(groupSize);
        rdoMedium.setToggleGroup(groupSize);
        rdoLarge.setToggleGroup(groupSize);
        
        VBox paneSize = new VBox(lblSize, rdoSmall, rdoMedium, rdoLarge);
        paneSize.setSpacing(10);
        
        // Crust style
        Label lblCrust = new Label("Crust");
        rdoThin = new RadioButton("Thin");
        rdoThick = new RadioButton("Thick");
        rdoThin.setSelected(true);
        ToggleGroup groupCrust = new ToggleGroup();
        rdoThin.setToggleGroup(groupCrust);
        rdoThick.setToggleGroup(groupCrust);
        
        VBox paneCrust = new VBox(lblCrust, rdoThin, rdoThick);
        paneCrust.setSpacing(10);
        
        // Toppings
        Label lblToppings = new Label("Toppings");
        chkPepperoni = new CheckBox("Pepperoni");
        chkSausage = new CheckBox("Sausage");
        chkOlives = new CheckBox("Olives");
        chkMushrooms = new CheckBox("Mushrooms");
        chkTomatoes = new CheckBox("Tomatoes");
        chkAnchovies = new CheckBox("Anchovies");
        
        FlowPane paneToppings = new FlowPane(Orientation.VERTICAL,
            chkPepperoni, chkSausage, chkOlives, chkMushrooms,
            chkTomatoes, chkAnchovies);
        paneToppings.setPadding(new Insets(10, 0, 10, 0));
        paneToppings.setHgap(20);
        paneToppings.setVgap(10);
        paneToppings.setPrefWrapLength(100);
        VBox paneTopping = new VBox(lblToppings, paneToppings);
        
        // Add the sub-panes to the order pane
        HBox paneOrder = new HBox(50, paneSize, paneCrust, paneTopping);
        
        // Create the center pane
        VBox paneCenter = new VBox(20, paneCustomer, paneOrder);
        paneCenter.setPadding(new Insets(0, 10, 0, 10));
        
        /* Create the bottom pane */
        Button btnOK = new Button("OK");
        btnOK.setPrefWidth(80);
        btnOK.setOnAction(e -> btnOK_click());
        
        Button btnCancel = new Button("Cancel");
        btnCancel.setPrefWidth(80);
        btnCancel.setOnAction(e -> btnCancel_click());
        
        Region spacer = new Region();
        
        HBox paneBottom = new HBox(10, spacer, btnOK, btnCancel);
        HBox.setHgrow(spacer, Priority.ALWAYS);
        paneBottom.setPadding(new Insets(20, 10, 20, 10));
        
        /* Finish the scene */
        BorderPane paneMain = new BorderPane();
        paneMain.setTop(paneTop);
        paneMain.setCenter(paneCenter);
        paneMain.setBottom(paneBottom);
        
        Scene scene = new Scene(paneMain);
        stage.setScene(scene);
        stage.setTitle("Pizza Order");
        stage.show();
    }
    
    public void btnOK_click()
    {
        // Create our message string
        String msg = "Customer:\n\n";
        msg += "\t" + txtName.getText() + "\n";
        msg += "\t" + txtAddress.getText() + "\n";
        msg += "\t" + txtPhone.getText() + "\n";
        msg += "You have ordered a ";
        
        if (rdoSmall.isSelected()) {
            msg += "small ";
        } else if (rdoMedium.isSelected()) {
            msg += "medium ";
        } else if (rdoLarge.isSelected()) {
            msg += "large ";
        }
        
        if (rdoThin.isSelected()) {
            msg += "thin crust pizza with ";
        } else if (rdoThick.isSelected()) {
            msg += "thick crust pizza with ";
        }
        
        String toppings = "";
        toppings = buildToppings(chkPepperoni, toppings);
        toppings = buildToppings(chkSausage, toppings);
        toppings = buildToppings(chkOlives, toppings);
        toppings = buildToppings(chkTomatoes, toppings);
        toppings = buildToppings(chkMushrooms, toppings);
        toppings = buildToppings(chkAnchovies, toppings);
        
        if (toppings.equals("")) msg += "no toppings.";
        else msg += "the following toppings:\n" + toppings;
        
        MessageBox.show(msg, "Order Details");
    }
    
    public String buildToppings(CheckBox chk, String msg)
    {
        if (chk.isSelected()) {
            if (!msg.equals("")) msg += ", ";
            msg += chk.getText();
        }
        return msg;
    }
    
    public void btnCancel_click()
    {
        stage.close();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}