/**
 * Binary I/O - Address Book
 * Written by Daniel de Sao Jose
 *
 * Requirements:
 *  Adds addresses.
 *  Updates addresses.
 *  Stores addresses.
 *  Retrieves addresses.
 *  An address is comprised of the following:
 *      Name - 32 bytes.
 *      Street - 32 bytes.
 *      City - 20 bytes.
 *      State - 2 bytes.
 *      Zip - 5 bytes.
 *  There must be the following buttons:
 *      An "Add" button.
 *      A "First" button.
 *      A "Next" button.
 *      A "Previous" button.
 *      A "Last" button.
 *      An "Update" button.
 *
 */


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;


public class DanielAssignment4 extends Application {

    // FIELDS!

    // Layout things
    GridPane pane = new GridPane(); // The main grid.
    HBox buttons = new HBox(); // HBox for the buttons.

    // Labels
    Label lblName = new Label("Name ");
    Label lblStreet = new Label("Street ");
    Label lblCity = new Label("City ");
    Label lblState = new Label("State ");
    Label lblZip = new Label("Zip ");

    // Text Fields
    TextField tfName = new TextField();
    TextField tfStreet = new TextField();
    TextField tfCity = new TextField();
    TextField tfState = new TextField();
    TextField tfZip = new TextField();

    // Buttons
    Button btnAdd = new Button("Add");
    Button btnFirst = new Button("First");
    Button btnPrevious = new Button("Previous");
    Button btnNext = new Button("Next");
    Button btnLast = new Button("Last");
    Button btnUpdate = new Button("Update");

    // The list and current index.
    ArrayList<DanielAssignment4Address> addressList = new ArrayList<>();
    int currentIndex;


    @Override
    public void start(Stage stage) {

        // Add Label objects to grid.
        pane.add(lblName, 0, 0);
        pane.add(lblStreet, 0, 1);
        pane.add(lblCity, 0, 2);
        pane.add(lblState, 0, 3);
        pane.add(lblZip, 0, 4);

        // Add TextField objects to grid.
        pane.add(tfName, 1, 0);
        pane.add(tfStreet, 1, 1);
        pane.add(tfCity, 1,2);
        pane.add(tfState, 1, 3);
        pane.add(tfZip, 1, 4);

        // Add buttons to HBox. then to grid.
        buttons.getChildren().addAll(btnAdd, btnFirst, btnPrevious, btnNext, btnLast, btnUpdate);
        pane.add(buttons, 0, 5, 2, 1);

        // Adding padding.
        buttons.setPadding(new Insets(10, 0, 0, 0));
        pane.setPadding(new Insets(10,10,10,10));

        // Handlers for each button.
        btnAdd.setOnAction( e -> addHandler());
        btnFirst.setOnAction(e -> firstHandler());
        btnLast.setOnAction(e -> lastHandler());
        btnNext.setOnAction(e -> nextHandler());
        btnPrevious.setOnAction(e -> previousHandler());
        btnUpdate.setOnAction(e -> updateHandler());
        tfName.setOnKeyTyped(e -> handleTextLimits(tfName, 32));
        tfStreet.setOnKeyTyped(e -> handleTextLimits(tfStreet, 5));
        tfCity.setOnKeyTyped(e -> handleTextLimits(tfCity, 20));
        tfState.setOnKeyTyped(e -> handleTextLimits(tfState, 2));
        tfZip.setOnKeyTyped(e -> handleTextLimits(tfZip, 5));

        // Load data saved on disk if present.
        loadData();

        // Update the text fields with contents of addressArray if any.
        updateTextFields();

        // Shake and bake.
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Daniel Assignment 4");
        stage.show();
    }

    public static void main (String[] args) {
        launch(args);
    }

    /**
     * Adds DanielAssignment4Address objects to the addressList ArrayList.
     */
    public void addHandler() {
        addressList.add(new DanielAssignment4Address(tfName.getText(), tfStreet.getText(), tfCity.getText(),
                tfState.getText(), tfZip.getText()));
        saveData();
    }

    /**
     * Sets the currentIndex to zero and updates the textFields.
     */
    public void firstHandler() {
        currentIndex = 0;
        updateTextFields();
    }

    /**
     * Sets the currentIndex to the size of the addressList - 1 and updates the textFields.
     */
    public void lastHandler() {
        currentIndex = addressList.size() - 1;
        updateTextFields();
    }

    /**
     * Increments the currentIndex if it isn't already at the maximum value and updates the textFields.
     */
    public void nextHandler() {
        if(!(currentIndex == addressList.size() - 1)) {
            currentIndex += 1;
        }
        updateTextFields();
    }

    /**
     * Prevents user for putting too much text in textFields.
     *
     * @param textField The TextField object with a limit.
     * @param limit The limit of the TextField object.
     */
    public void handleTextLimits(TextField textField, int limit) {
        if (textField.getText().length() == limit) {
            textField.setText(textField.getText(0, limit - 1));
            textField.positionCaret(limit -1 );
        }
    }

    /**
     * Decrements the currentIndex if it isn't already at the minimum value and updates the textFields.
     */
    public void previousHandler() {
        if(!(currentIndex == 0)) {
            currentIndex -= 1;
        }
        updateTextFields();
    }

    /**
     * Modify the object in the addressList with the currentIndex with the text in the fields.
     */
    public void updateHandler() {
        addressList.get(currentIndex).name = tfName.getText();
        addressList.get(currentIndex).street = tfStreet.getText();
        addressList.get(currentIndex).city = tfCity.getText();
        addressList.get(currentIndex).state = tfState.getText();
        addressList.get(currentIndex).zip = tfZip.getText();
        saveData(); // Save data after evey modification.
    }

    /**
     * Refreshes the value in the text fields to reflect the currentIndex value.
     */
    public void updateTextFields() {
        if(addressList.size() > 0) {
            tfName.setText(addressList.get(currentIndex).name);
            tfStreet.setText(addressList.get(currentIndex).street);
            tfCity.setText(addressList.get(currentIndex).city);
            tfState.setText(addressList.get(currentIndex).state);
            tfZip.setText(addressList.get(currentIndex).zip);
        }
    }

    /**
     * Saves a serialized version of the addressList object into a file.
     */
    public void saveData() {

        try {
            File file = new File("DanielAssignment4.dat");
            if(!(file.exists())) {
                file.createNewFile();
            }
            FileOutputStream outputFile = new FileOutputStream("DanielAssignment4.dat");
            ObjectOutputStream outputStream = new ObjectOutputStream(outputFile);
            outputStream.writeObject(addressList);
            outputStream.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a serialized version of the addressList object from a file.
     */
    public void loadData() {
        try {
            FileInputStream file = new FileInputStream("DanielAssignment4.dat");
            ObjectInputStream input = new ObjectInputStream(file);
            addressList = (ArrayList) input.readObject();
            input.close();
        } catch(FileNotFoundException e) {
            System.out.println("No data file found.");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}