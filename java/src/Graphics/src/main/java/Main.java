// JavaFX Lukas Pahommovs 221RDB047 14.grupa

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.shape.Circle;

public class Main extends Application {

  Group group;

  @Override
  public void start(Stage primaryStage) {

    VBox vbox;
    Scene scene;

    Line topHorizontalLine = new Line(60, 0, 60, 180);
    topHorizontalLine.setStrokeWidth(5);
    topHorizontalLine.setStroke(Color.rgb(0, 80, 200, 0.90));
    Line bottomHorizontalLine = new Line(120, 0, 120, 180);
    bottomHorizontalLine.setStrokeWidth(5);
    bottomHorizontalLine.setStroke(Color.rgb(0, 80, 200, 0.90));

    Line leftVerticalLine = new Line(0, 60, 180, 60);
    leftVerticalLine.setStrokeWidth(5);
    leftVerticalLine.setStroke(Color.rgb(0, 80, 200, 0.90));
    Line rightVerticalLine = new Line(0, 120, 180, 120);
    rightVerticalLine.setStrokeWidth(5);
    rightVerticalLine.setStroke(Color.rgb(0, 80, 200, 0.90));

    group = new Group(topHorizontalLine, bottomHorizontalLine, leftVerticalLine, rightVerticalLine);
    vbox = new VBox(group);

    vbox.setAlignment(Pos.CENTER);

    scene = new Scene(vbox, 300, 350);

    EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        Integer x = (int) e.getX();
        Integer y = (int) e.getY();

        if (e.getButton() == MouseButton.PRIMARY) {
          Line toRightLine = new Line();
          Line toLeftLine = new Line();
          toRightLine.setStrokeWidth(5);
          toLeftLine.setStrokeWidth(5);
          toRightLine.setStroke(Color.rgb(0, 200, 80, 0.90));
          toLeftLine.setStroke(Color.rgb(0, 200, 80, 0.90));
          if (y < 100) {
            toRightLine.setStartY(15);
            toRightLine.setEndY(45);
            toLeftLine.setStartY(15);
            toLeftLine.setEndY(45);
          } else if(y > 100 && y < 160) {
            toRightLine.setStartY(75);
            toRightLine.setEndY(105);
            toLeftLine.setStartY(75);
            toLeftLine.setEndY(105);
          } else if(y > 160) {
            toRightLine.setStartY(135);
            toRightLine.setEndY(165);
            toLeftLine.setStartY(135);
            toLeftLine.setEndY(165);
          }
          if (x < 115) {
            toRightLine.setStartX(15);
            toRightLine.setEndX(45);
            toLeftLine.setStartX(45);
            toLeftLine.setEndX(15);
          } else if(x > 115 && x < 175) {
            toRightLine.setStartX(75);
            toRightLine.setEndX(105);
            toLeftLine.setStartX(105);
            toLeftLine.setEndX(75);
          } else if(x > 175) {
            toRightLine.setStartX(135);
            toRightLine.setEndX(165);
            toLeftLine.setStartX(165);
            toLeftLine.setEndX(135);
          }
          addToGroup(group, toRightLine);
          addToGroup(group, toLeftLine);
        }
        if (e.getButton() == MouseButton.SECONDARY) {
          Circle outerCircle = new Circle(20, Color.rgb(0, 200, 80, 0.90));
          Circle innerCircle = new Circle(15, Color.WHITE);
          if (y < 100) {
            outerCircle.setCenterY(30);
            innerCircle.setCenterY(30);
          } else if (y > 100 && y < 160) {
            outerCircle.setCenterY(90);
            innerCircle.setCenterY(90);
          } else if (y > 160) {
            outerCircle.setCenterY(150);
            innerCircle.setCenterY(150);
          }

          if (x < 115) {
            outerCircle.setCenterX(30);
            innerCircle.setCenterX(30);
          } else if (x > 115 && x < 175) {
            outerCircle.setCenterX(90);
            innerCircle.setCenterX(90);
          } else if (x > 175) {
            outerCircle.setCenterX(150);
            innerCircle.setCenterX(150);
          }
          addToGroup(group, outerCircle);
          addToGroup(group, innerCircle);
        }
        if (e.getClickCount()>1) group=new Group(topHorizontalLine, bottomHorizontalLine, leftVerticalLine, rightVerticalLine);
        // System.out.println("X: " + (int) x + " Y: " + (int) y + " Button: " + e.getButton());
        vbox.getChildren().remove(0);
        vbox.getChildren().add(0, group);

      }
    };

    // Registering the event filter
    scene.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);

    primaryStage.setTitle("Tic Tac Toe");
    primaryStage.setScene(scene);
    primaryStage.setWidth(300);
    primaryStage.setHeight(300);
    primaryStage.show();

  }

  void addToGroup(Group group, Circle circle) {
    group.getChildren().add(circle);
  }

  void addToGroup(Group group, Line line) {
    group.getChildren().add(line);
  }

  public static void main(String[] args) {
    launch(args);
  }
}
