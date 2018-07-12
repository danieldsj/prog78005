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
import java.util.Random;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.util.ArrayList;


public class DanielAssignment2 extends Application {

    /**
     * Since this is a subclass of a JavaFX Application, we must override the start method.
     *
     * @param stage The default Stage object.
     */
    @Override
    public void start (Stage stage) {

        // We need to create a GridPane object for the scene.
        GridPane grid = new GridPane();

        // Populate row zero with images of random cards.
        randomCardsOnGrid(grid, 0);

        // Create a refresh button.
        Button button = new Button("Refresh");

        // Make sure the button is centered.
        grid.setHalignment(button, HPos.CENTER);

        // When clicked, the button should randomize cards again.
        button.setOnAction((new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                randomCardsOnGrid(grid, 0);
            }
        }));

        // Add the button to the grid.
        grid.add(button, 0, 1, 4, 1);

        // Create a new Scene object using the GridPane object.
        Scene scene = new Scene(grid);

        // Set the Stage object to use the Scene object above.
        stage.setScene(scene);

        // Provide a friendly title.
        stage.setTitle("Daniel Assignment 2");

        // Show the Stage object.
        stage.show();
    }

    /**
     * Given a GridPane object and an int representing a row number, populate that row with images of 4 random playing
     * cards.
     *
     * @param grid The GridPane object where we will add images of 4 random playing cards.
     * @param row The row on which the playing cards will be added to.
     */
    public void randomCardsOnGrid(GridPane grid, int row) {

        // Instance of random class to generate random numbers.
        Random random = new Random();

        // Four suits and thirteen face values.
        final String[] suits = {"clubs", "diamonds", "hearts", "spades"};
        final String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace"};

        // Generate a single list of cards to randomize over.
        ArrayList<String> filenames = new ArrayList<>();
        for (int suitIndex = 0; suitIndex < suits.length; suitIndex++) {
            for (int valueIndex = 0; valueIndex < values.length; valueIndex++) {
                filenames.add(values[valueIndex] + "_of_" + suits[suitIndex] + ".png");
                // System.out.println(values[valueIndex] + "_of_" + suits[suitIndex] + ".png");
            }
        }

        // Variable for the filenames.
        String randomFilename;
        String fullPath;

        // Randomize 4 numbers and add the correspon ding image file, to the grid.
        for(int i = 0; i < 4; i++) {
            randomFilename = filenames.get(random.nextInt(filenames.size()));

            try {

                // Attempt to get the file from the local "static" folder.
                fullPath = "stati/" + randomFilename;
                grid.add(new ImageView(new Image(fullPath, 200, 200,
                        true, true)), i, 0 );

            } catch(IllegalArgumentException e) {

                // On exception, attempt ot get the file from the github repo.
                fullPath = "https://raw.githubusercontent.com/danieldsj/prog78005/master/src/static/" + randomFilename;
                grid.add(new ImageView(new Image(fullPath, 200, 200,
                        true, true)), i, row );
            }
        }
    }


    /**
     * Launches the JavaFX application.
     *
     * @param args Optional command line arguments that are promptly ignored.
     */
    public static void main (String[] args) {
        launch();
    }
}
