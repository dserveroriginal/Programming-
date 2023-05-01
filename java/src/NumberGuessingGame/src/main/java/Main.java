// JavaFX Lukas Pahommovs 221RDB047 14.grupa

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class Main extends Application 
{ 
  Integer randomNumber= (int)(Math.random() * 100 + 1);
  Integer attempts=7;

  @Override
  public void start(Stage primaryStage) {

    
    
    Label label1, label2; 
    TextField textField;
    Button btnOk, btnNewGame;
    VBox vbox;
    Scene scene;

    label1 = new Label("Input number (1-100):");
    textField = new TextField();
    label2 = new Label("You have 7 attempts to guess a number");
    textField.setMaxWidth(250);

    
    btnOk = new Button("OK"); 
    btnNewGame = new Button("New Game");

    btnOk.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e) {
        try {
          if (attempts<=0) {
            label2.setText("You lost!");
            return;
          }
          Integer inputNumber = Integer.parseInt(textField.getText());
          if (inputNumber<1 || inputNumber>100) {
            label2.setText("error");
            return;
          } 
          if (inputNumber.equals(randomNumber)) {
            label2.setText("You guessed the number!");
          } else if (inputNumber>randomNumber) {
            label2.setText("Your number is greater than the number");
          } else {
            label2.setText("Your number is less than the number");
          }
          setAttempts(attempts-1);
        } catch (Exception exeption) {
          label2.setText(exeption.getMessage());
        }
        textField.setText(""+randomNumber);
      }
    });

    btnNewGame.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e) {
        textField.setText("");
        label2.setText("You have 7 attempts to guess a number");
        setAttempts(7);
        setRandomNuber((int)(Math.random() * 100 + 1));
      }
    });

    

    
    

    vbox = new VBox(label1, textField, label2, btnOk, btnNewGame);
    vbox.setSpacing(20);
    vbox.setAlignment(Pos.CENTER);
    scene = new Scene(vbox, 300, 300);
    
    primaryStage.setTitle("Guess a number");
    primaryStage.setScene(scene);
    primaryStage.show();
  } 

  void setAttempts(Integer newAttempts) {
    attempts=newAttempts;
  }

  void setRandomNuber(Integer newRandomNumber) {
    randomNumber=newRandomNumber;
  }


    
  public static void main(String[] args) {
    launch(args);
  }
} 
