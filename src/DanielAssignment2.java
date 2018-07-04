/**
 *
 * JavaFX Basics - Pick Four Cards
 * Written by Daniel de Sao Jose
 *
 * Requirements:
 *  Must have a refresh button that says "Refresh" on it.
 *  User must be able to click the button.
 *  Upon clicking the "Refresh" button, display 4 random cards from the 52 cards in a playing deck.
 *
 */

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import java.util.Random;


public class DanielAssignment2 extends Application {

    /**
     * Since this is a subclass of a JavaFX Application, we must override the start method.
     * @param stage
     */
    @Override
    public void start (Stage stage) {

        // Four suits.
        final String[] suits = {"clubs", "diamonds", "hearts", "spades"};

        // Thirteen face values.
        final String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace"};

        // Variables for the random numbers.
        int randomSuitIndex;
        int randomValueIndex;

        // Variables for the filenames.
        String filename;

        // We need to create a GridPane object for the scene.
        GridPane grid = new GridPane();
        Random random = new Random();

        // Randomize 4 numbers and add the corresponding image file, to the grid.
        for(int i = 0; i < 4; i++) {
            randomSuitIndex = random.nextInt(suits.length - 1);
            randomValueIndex = random.nextInt(values.length - 1);
            filename = "static/" + values[randomValueIndex] + "_of_" + suits[randomSuitIndex] + ".png";
            System.out.println(filename);
            grid.add(new ImageView(new Image(filename, 200, 200, true,
                    true)), i, 0 );
        }

        Button button = new Button("Refresh");
        button.setAlignment(Pos.CENTER);
        grid.add(button, 0, 1, 4, 1);
        grid.setHalignment(button, HPos.CENTER);

        Scene scene = new Scene(grid);
        stage.setScene(scene);
        stage.show();
    }

    public static void main (String[] args) {
        launch();
    }
}
