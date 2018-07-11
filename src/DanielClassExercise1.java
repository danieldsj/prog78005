/**
 * Written by Daniel de Sao Jose
 *
 * Requirements:
 *  Create a border pane
 *  A circle in the right
 *  A rectangle in the middle of the border pane,and set the rectangle to the left of the middle.
 *  An eclipse in the left side of the Border Pan rotated so that the ellipse is up and down.
 *  Two buttons in the bottom (no events) of the Border Pane.
 *  Two labels with text boxes in the top of the Border Pane.
 *
 *
 */


// Import many things.
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Ellipse;
import javafx.scene.control.Button;
import javafx.scene.control.Label;



public class DanielClassExercise1 extends Application {

    @Override
    public void start(Stage stage) {

        // Add custom pane to the scene.
        Scene scene = new Scene(new CustomBorderPane());

        // Set the stage with the scene.
        stage.setScene(scene);

        // Show the scene.
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}


class CustomBorderPane extends BorderPane {

    CustomBorderPane() {

        // Two buttons.
        Button button1 = new Button("Button One");
        Button button2 = new Button("Button Two");

        // Created a nested Hbox pane to house the two buttons.
        HBox bottomPane = new HBox();
        bottomPane.getChildren().add(button1);
        bottomPane.getChildren().add(button2);

        // Added the two buttons to the bottom pane.
        setBottom(bottomPane);

        // Two labels.
        Label label1 = new Label("Label One");
        Label label2 = new Label("Label Two");

        // Created a nested Hbox pane to house the two labels.
        HBox topPane = new HBox();
        topPane.getChildren().add(label1);
        topPane.getChildren().add(label2);

        // Added the two labels to the top pane.
        setTop(topPane);


        // A circle on the right.
        Circle circle = new Circle(50);
        setRight(circle);


        // A rectangle in the center.
        Rectangle rectangle = new Rectangle(50,100);
        setCenter(rectangle);


        // Ensure that the rectangle is left aligned.
        setAlignment(rectangle, Pos.CENTER_LEFT);
        setMargin(rectangle, new Insets(50, 50, 50, 100));


        // An eclipse on the left, but stretching vertically.
        Ellipse ellipse = new Ellipse(50, 100);
        setLeft(ellipse);

    }

}
