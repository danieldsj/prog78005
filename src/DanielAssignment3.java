/**
 *
 * UI Controls and Multimedia - Geometry: Two circles intersect.
 * Written by Daniel de Sao Jose
 *
 * Requirements:
 *  Complete exercise 16.8
 *  Centered at the top of the window display "Two circles intersect?" followed by either a "Yes" or "No.
 *  Beneath this text, display two circles.
 *  Beneath the circles display two tables side by side.
 *  The left table displays information about circle 1.
 *  The right table displays information about circle 2.
 *  For each table, display a row and column describing the following:
 *      Center x
 *      Center y
 *      Radius
 *  Beneath the fields, there must be a button with the text "Redraw circles".
 *  When clicking and holding on the circle, the user must be able to move the position of the circle.
 *  Moving the circle causes the information about the circle to update.
 *  When clicking on the "Redraw Circles" button, the circles return to their original size and position.
 *
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.control.TableView;



public class DanielAssignment3 extends Application {


    @Override
    public void start (Stage stage) {

        Circle circle1 = new Circle(50, Color.RED);
        Circle circle2 = new Circle(50, Color.BLUE);
        GridPane grid = new GridPane();

        grid.add(circle1, 0, 0);
        grid.add(circle2, 1, 0);

        Scene scene = new Scene(grid);
        stage.setScene(scene);
        stage.show();
    }


    public static void main (String[] args) {
        launch();
    }
}
