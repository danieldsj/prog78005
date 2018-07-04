/**
 * JavaFX Basics - Display Images
 * Written by Daniel de Sao Jose
 *
 * Requirements:
 *  Complete Exercise 14.1
 *  Display four images
 *  Four images must be in a grid pane.
 *  Four images must be displayed in two rows of two columns.
 *
 */

// Import Dependencies
import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.ArrayList;

/**
 * This is a JavaFX application, and therefore I am extending the Application class.
 */
public class DanielAssignment1 extends Application {

    /**
     * As a subclass of Application, I must override the start method.
     *
     * @param theStage The default stage for a JavaFX application.
     */
    @Override
    public void start (Stage theStage) {

        // Image objects to store the image.
        ArrayList<Image> imageArray = new ArrayList<> ();
        for(int i = 0; i < 4; i++) {
            imageArray.add(new Image("cow" + i + ".jpg",
                    300,
                    300,
                    false,
                    false));
        }

        // The Image object musts be placed in ImageView objects.
        ArrayList<ImageView> imageViewArray = new ArrayList<> ();
        for(int i = 0; i < imageArray.size(); i++) {
            imageViewArray.add(new ImageView(imageArray.get(i)));
        }

        // The ImageView object must be placed in a Pane object.
        GridPane theGridPane = new GridPane();

        // We need to add the ImageView objects to the GridPane object and provide a row and column.
        int index = 0;
        while(index < imageViewArray.size()){
            for(int row = 0; row < 2; row++) {
                for(int column = 0; column < 2; column++) {
                    theGridPane.add(imageViewArray.get(index), row, column);
                    index++;
                }
            }
        }

        // We need to set the Scene object to use our Pane object.
        Scene theScene = new Scene(theGridPane);

        // We need to set the Stage with the Scene containing the images.
        theStage.setScene(theScene);

        // We need to show the stage.
        theStage.show();

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
