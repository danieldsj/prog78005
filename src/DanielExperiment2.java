import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.util.Random;

public class DanielExperiment2 extends Application {

    Random random = new Random();

    BorderPane borderPane = new BorderPane();

    Label lblTop = new Label("Top");
    Label lblBottom = new Label("Bottom");
    Label lblLeft = new Label("Left");
    Label lblRight = new Label("Right");
    Label lblCenter = new Label("Center");

    Button btnEventHandlerClass = new Button("Event Hander Class");
    Button btnAnonymousInnerClass = new Button("Anonymous Inner Class");
    Button btnLambdaExpression = new Button("Lambda Expression");

    HBox hbButtons = new HBox();

    public void start(Stage stage) {

        borderPane.setBottom(lblBottom);
        borderPane.setTop(lblTop);
        borderPane.setLeft(lblLeft);
        borderPane.setRight(lblRight);

        hbButtons.getChildren().addAll(btnEventHandlerClass, btnAnonymousInnerClass, btnLambdaExpression);
        hbButtons.setAlignment(Pos.CENTER);
        borderPane.setCenter(hbButtons);

        borderPane.setAlignment(lblBottom, Pos.CENTER);
        borderPane.setAlignment(lblTop, Pos.CENTER);
        borderPane.setAlignment(lblLeft, Pos.CENTER);
        borderPane.setAlignment(lblRight, Pos.CENTER);
        borderPane.setAlignment(lblCenter, Pos.CENTER);

        EventHandlerClass handlerClass = new EventHandlerClass();
        btnEventHandlerClass.setOnAction(handlerClass);

        btnAnonymousInnerClass.setOnAction(
                new EventHandler() {

                    public void handle(Event event) {
                        System.out.println("AnonymousInnerClass");
                    }
                });

        btnLambdaExpression.setOnAction((e) -> {System.out.println("LambdaExpression");});

        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.setWidth(800);
        stage.setHeight(600);
        stage.show();

    }
}


class EventHandlerClass implements EventHandler {

    @Override
    public void handle(Event event) {
        System.out.println("EventHandlerClass");
    }
}
