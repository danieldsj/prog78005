import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;


public class DanielExperiment1 extends Application {

    @Override
    public void start (Stage myStage) {

        // An application automatically gets a single default Stage object.
        // A Stage object can have one scene at a time and is set with the setScene method.
        // A Scene object has only one pane or control set with the constructor.
        // A Scene cannot have nodes such as images and shapes.
        // Images and shapes must be added to a pane before being added to a scene.
        // Panes can be nested.

        // Create an image and an ImageView object (Node).
        Image image = new Image("cow.jpg", 600,
                600, false, false);
        ImageView imageView = new ImageView();
        imageView.setImage(image);

        // Add the ImageView object (Node) to the Pane.
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(imageView);

        // Create a Scene object with the Pane object.
        Scene scene = new Scene(stackPane, 600, 600);
        imageView.fitHeightProperty().bind(scene.heightProperty());
        imageView.fitWidthProperty().bind(scene.widthProperty());

        // Load the scene to the stage
        myStage.setTitle("This is a mooing application!");
        myStage.setScene(scene);
        myStage.show();
    }


    public static void main (String[] args) {
        launch();
    }

}
