/**
 *
 * UI Controls and Multimedia - Geometry: Two circles intersect.
 * Written by Daniel de Sao Jose
 *
 * Requirements:
 *  Complete exercise 16.8
 *  Centered at the top of the window display "Two circles intersect?" followed by either a "Yes" or "No".
 *  Beneath this text, display two circles.
 *  Beneath the circles display two tables side by side.
 *  The left tablePane displays information about circlePane 1.
 *  The right tablePane displays information about circlePane 2.
 *  For each tablePane, display a row and column describing the following:
 *      Center x
 *      Center y
 *      Radius
 *  Beneath the fields, there must be a button with the text "Redraw circles".
 *  When clicking and holding on the circlePane, the user must be able to move the position of the circlePane.
 *  Moving the circlePane causes the information about the circlePane to update.
 *  When clicking on the "Redraw Circles" button, the circles return to their original size and position.
 *
 */

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DanielAssignment3 extends Application {

    // Class properties
    GridPane gridPane = new GridPane();  // Main pane.
    Pane circlePane = new Pane(); // Pane for circles.
    Label title = new Label("Two circles intersect? No");  // Text above circles.

    // Class properties related to the red circle and it's table.
    Circle redCircle = new Circle(50, Color.RED);
    Label redCircleTitle = new Label("Red Circle");
    Label redCircleCenterX = new Label("Center X:");
    Label redCircleCenterY = new Label("Center Y:");
    Label redCircleRadius = new Label("Radius:");
    TextField redCircleCenterXValue = new TextField();
    TextField redCircleCenterYValue = new TextField();
    TextField redCircleRadiusValue = new TextField();

    // Class properties related to the red circle and it's table.
    Circle blueCircle = new Circle(50, Color.BLUE);
    Label blueCircleTitle = new Label("Blue Circle");
    Label blueCircleCenterX = new Label("Center X:");
    Label blueCircleCenterY = new Label("Center Y:");
    Label blueCircleRadius = new Label("Radius:");
    TextField blueCircleCenterXValue = new TextField();
    TextField blueCircleCenterYValue = new TextField();
    TextField blueCircleRadiusValue = new TextField();

    // The redraw button.
    Button redrawButton = new Button("Redraw Circles");

    @Override
    public void start (Stage stage) {

        // Ensure the circles are transparent so we can see the overlap.
        redCircle.setOpacity(0.5);
        blueCircle.setOpacity(0.5);

        // Map all of the nodes and actions with event handlers.
        redCircle.setOnMouseDragged(e -> { dragHandler(e, redCircle);});
        blueCircle.setOnMouseDragged(e -> { dragHandler(e, blueCircle);});
        redrawButton.setOnAction(e -> { initialize(); });

        // Title, circles, and button spanning across all 4 columns in various rows. Ensure they are centered.
        gridPane.add(title, 0, 0, 4, 1);
        GridPane.setHalignment(title, HPos.CENTER);
        gridPane.add(redrawButton, 0, 6, 4, 1);
        GridPane.setHalignment(redrawButton, HPos.CENTER);
        gridPane.add(circlePane, 0, 1, 4, 1);
        circlePane.getChildren().addAll(redCircle, blueCircle);

        // All red circle related nodes going down columns 0 and 1.
        gridPane.add(redCircleTitle, 0, 2, 2, 1);
        gridPane.add(redCircleCenterX, 0, 3, 1, 1);
        gridPane.add(redCircleCenterXValue, 1, 3, 1, 1);
        gridPane.add(redCircleCenterY, 0, 4, 1, 1);
        gridPane.add(redCircleCenterYValue, 1, 4, 1, 1);
        gridPane.add(redCircleRadius, 0, 5, 1, 1);
        gridPane.add(redCircleRadiusValue, 1, 5, 1, 1);

        // All blue circle related nodes going down columns 2 and 3.
        gridPane.add(blueCircleTitle, 2, 2, 2, 1);
        gridPane.add(blueCircleCenterX, 2, 3, 1, 1);
        gridPane.add(blueCircleCenterXValue, 3, 3, 1, 1);
        gridPane.add(blueCircleCenterY, 2, 4, 1, 1);
        gridPane.add(blueCircleCenterYValue, 3, 4, 1, 1);
        gridPane.add(blueCircleRadius, 2, 5, 1, 1);
        gridPane.add(blueCircleRadiusValue, 3, 5, 1, 1);

        // Initialize all of the Nodes with values.
        initialize();
        gridPane.setOnMouseDragged(e -> checkOverlap());

        // Setting up and displaying the scene.
        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Given a MouseEvent object and a Circle object, update the circles's Center X and Center Y properties to match
     * that of the mouse location.
     *
     * @param e The MouseEvent object.
     * @param c The Circle Object that will be modified.
     */
    public void dragHandler(MouseEvent e, Circle c) {
        c.setCenterX(e.getX());
        c.setCenterY(e.getY());
    }

    /**
     * Update the Class fields so that they reflect the position and radius of the circles including the TextField
     * values and the text within the "title" label.
     */
    public void checkOverlap() {

        // Logic that validates overlap.
        if(redCircle.getBoundsInParent().intersects(blueCircle.getBoundsInParent())) {
            title.setText("Two circles intersect? Yes");
        }
        else {
            title.setText("Two circles intersect? No");
        }

        // Update fields in red table.
        redCircleCenterXValue.setText(String.valueOf(redCircle.getCenterX()));
        redCircleCenterYValue.setText(String.valueOf(redCircle.getCenterY()));
        redCircleRadiusValue.setText(String.valueOf(redCircle.getRadius()));

        // Update fields in blue table.
        blueCircleCenterXValue.setText(String.valueOf(blueCircle.getCenterX()));
        blueCircleCenterYValue.setText(String.valueOf(blueCircle.getCenterY()));
        blueCircleRadiusValue.setText(String.valueOf(blueCircle.getRadius()));
    }

    /**
     * Sets the initial values for the red and blue circle and calls checkOverlap to populate fields before the user
     * interacts with the UI.
     */
    public void initialize() {
        redCircle.setCenterX(100);
        redCircle.setCenterY(60);

        blueCircle.setCenterX(300);
        blueCircle.setCenterY(60);

        checkOverlap();

    }

    public static void main (String[] args) {
        launch();
    }
}





