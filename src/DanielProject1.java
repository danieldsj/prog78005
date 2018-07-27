/**
 *
 * 21 - Card Game
 * The card game will allow you to utilize JavaFX  and Object Oriented Programming.
 *
 * Rules of the game:
 * Draw cards from the dealer's shuffled deck.
 * 21 is an automatic win for the dealer always wins on a draw
 * If the dealer gets 17 or more he must stand (cannot draw any more cards)
 *
 * Card values:
 * Jack, Queen King = 10
 * Ace can be 1 or 11
 * Cards 1 to 10 are the card value
 * Hold logic, when the player feels they have enough points to win (remember under 21).
 * The hold will allow the control to switch to the dealer
 * There should be two boxButtons - Draw & Hold
 * When switching to the dealer, the dealer should draw until a win (greater than the players score) or the dealers draw is greater than 21 (when the player has won).
 * Messaging should be presented when the player has won or lost.
 *
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Random;

public class DanielProject1 extends Application {

    // Fields (UI Related)
    GridPane pane = new GridPane();
    HBox boxButtons = new HBox();
    Button btnDraw = new Button("Draw");
    Button btnHold = new Button("Hold");

    // Fields (Game related)
    ArrayList<Card> deck = new ArrayList<>();
    Player dealer = new Player("Dealer");
    Player player = new Player("Player");
    Random random = new Random();

    @Override
    public void start(Stage stage) {

        // Setup the UI.
        pane.add(dealer.lblName, 0, 0, 1, 1);
        pane.add(dealer.boxHand, 0, 1, 1, 1);
        pane.add(player.lblName, 0, 2, 1, 1);
        pane.add(player.boxHand, 0, 3, 1, 1);
        boxButtons.getChildren().addAll(btnDraw, btnHold);
        pane.add(boxButtons, 0, 4, 1, 1);

        // Populate the deck with cards
        final String[] suits = {"clubs", "diamonds", "hearts", "spades"};
        final String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace"};
        for (int suitIndex = 0; suitIndex < suits.length; suitIndex++) {
            for (int valueIndex = 0; valueIndex < values.length; valueIndex++) {
                deck.add(new Card(suits[suitIndex],values[valueIndex]));
            }
        }

        // Add handlers for the buttons.
        btnDraw.setOnAction(e -> drawHandler());
        btnHold.setOnAction(e -> holdHandler());

        // Showtime.
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("DanielProject1");
        stage.setWidth(800);
        stage.setHeight(600);
        stage.show();
    }

    public void drawHandler() {
        player.draw(deck);
        player.render();
        player.getLblName().setText("Player (" + player.getPoints() + ")");
        if(player.hasLost()) {
            player.getLblName().setText("Player (" + player.getPoints() + ") LOSER!");
            dealer.getLblName().setText("Dealer (" + dealer.getPoints() + ") WINNER!");
        }
    }

    public void holdHandler() {
        while(dealer.getPoints() < 21 && dealer.getPoints() <= player.getPoints()) {
            dealer.draw(deck);
            dealer.render();
            dealer.getLblName().setText("Dealer (" + dealer.getPoints() + ")");
            if(dealer.hasLost()){
                player.getLblName().setText("Player (" + player.getPoints() + ") WINNER!");
                dealer.getLblName().setText("Dealer (" + dealer.getPoints() + ") LOSER!");
            } else if (dealer.getPoints() > player.getPoints()){
                player.getLblName().setText("Player (" + player.getPoints() + ") LOSER!");
                dealer.getLblName().setText("Dealer (" + dealer.getPoints() + ") WINNER!");
            } else if (dealer.getPoints() == 21) {
                player.getLblName().setText("Player (" + player.getPoints() + ") LOSER!");
                dealer.getLblName().setText("Dealer (" + dealer.getPoints() + ") WINNER!");
            }
        }
    }

    public static void main (String[] args) {
        launch(args);
    }
}


/**
 * This class is used to instantiate objects representing playing cards.
 */
class Card {

    // Fields
    private String suit;
    private String faceValue;
    private String filename;
    private int pointValue;
    private Image imageFile;
    private ImageView imageFileView;

    // Constructors
    Card(String suit, String faceValue) {
        this.suit = suit.toLowerCase();
        this.faceValue = faceValue.toLowerCase();
        this.filename = faceValue + "_of_" + suit + ".png";
        imageFile = new Image("static/" + this.filename, 200, 200,
                true, true);
        imageFileView = new ImageView(imageFile);

        // Calculate the point value of the card.  Assume aces are worth 11 points at first.
        switch(this.getFaceValue()) {
            case "jack": this.setPointValue(10); break;
            case "queen": this.setPointValue(10); break;
            case "king": this.setPointValue(10); break;
            case "ace": this.setPointValue(11); break;
            default: this.setPointValue(Integer.valueOf(this.getFaceValue())); break;
        }
    }

    // Getter methods
    public String getSuit() { return this.suit; }
    public String getFaceValue() { return this.faceValue; }
    public String getFilename() { return this.filename; }
    public Image getImageFile() { return this.imageFile;}
    public ImageView getImageFileView() { return this.imageFileView; }
    public int getPointValue() { return this.pointValue; }

    // Setter methods
    public void setPointValue(int value) { this.pointValue = value; }
}

/**
 * This class is used to instantiate objects representing players or dealers.
 */
class Player {

    // Fields
    ArrayList<Card> hand;
    Random random;
    HBox boxHand;
    Label lblName;
    String name;

    // Constructors
    Player(String name) {
        this.hand = new ArrayList<>();
        this.random = new Random();
        this.boxHand = new HBox();
        this.lblName = new Label(name);
        this.boxHand.setMinHeight(200);
        this.name = name;
    }

    // Getter methods
    public HBox getBoxHand() { return this.boxHand; }
    public Label getLblName() { return this.lblName; }
    public ArrayList<Card> getHand() { return this.hand; }


    // Methods
    public void draw(ArrayList<Card> deck) {
        int cardIndex = random.nextInt(deck.size() -1);
        this.hand.add(deck.get(cardIndex));
        deck.remove(cardIndex);
    }

    public void render() {
        for (Card card : this.getHand()) {
            if(!(this.getBoxHand().getChildren().contains(card.getImageFileView()))) {
                this.getBoxHand().getChildren().add(card.getImageFileView());
            }
        }
    }

    public int getPoints() {
        int result = 0;
        for(Card e: this.hand) {
            result += e.getPointValue();
        }
        return result;
    }

    public Boolean hasAces() {
        for(Card card: this.getHand()) {
            if (card.getPointValue() == 11) {
                return true;
            }
        }
        return false;
    }

    public Boolean hasLost() {

        // If we are above 21.
        if(this.getPoints() > 21){

            // While we still have aces.
            while(this.hasAces()) {

                // Iterate over each card.
                for(Card card: this.getHand()) {

                    // If it's an ace.
                    if(card.getPointValue() == 11) {

                        // Reduce it's value to 1.
                        card.setPointValue(1);
                        this.getLblName().setText(name + " (" + this.getPoints() + ")");
                        break;
                    }
                }

                // If it results in the number being less than or equal to 21, return false.
                if(this.getPoints() <= 21) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}