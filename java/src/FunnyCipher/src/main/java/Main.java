// JavaFX Lukas Pahommovs 221RDB047 14.grupa

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) {

    TextField text;
    Button btnAdd, btnRun, btnDelete, btnClear;
    ListView<String> leftList, rightList;
    VBox vbox;
    HBox btnBoxAR, btnBoxDC, listsBox;
    Scene scene;

    text = new TextField();
    btnAdd = new Button("Add");
    btnRun = new Button("Run");
    btnDelete = new Button("Delete");
    btnClear = new Button("Clear");

    btnBoxAR = new HBox(btnAdd, btnRun);
    btnBoxAR.setAlignment(Pos.CENTER);
    btnBoxAR.setSpacing(20);
    btnBoxDC = new HBox(btnDelete, btnClear);
    btnBoxDC.setAlignment(Pos.CENTER);
    btnBoxDC.setSpacing(20);

    leftList = new ListView<String>();
    leftList.setPrefSize(250, 150);

    rightList = new ListView<String>();
    rightList.setPrefSize(250, 150);

    listsBox = new HBox(leftList, rightList);
    listsBox.setAlignment(Pos.CENTER);
    listsBox.setSpacing(20);

    vbox = new VBox(text, btnBoxAR, listsBox, btnBoxDC);
    vbox.setSpacing(20);
    vbox.setPadding(new Insets(15));
    vbox.setAlignment(Pos.CENTER);

    scene = new Scene(vbox, 300, 350);

    btnAdd.setOnAction(handler -> {
      String textString = text.getText();
      if (textString.length() > 0) {
        leftList.getItems().add(textString);
        text.clear();
      }
    });

    btnRun.setOnAction(handler -> {
      for (String string : leftList.getItems()) {
        String cipheredString = cipherString(string);
        rightList.getItems().add(cipheredString);
      }
    });

    btnDelete.setOnAction(handler -> {
      int index = leftList.getSelectionModel().getSelectedIndex();
      if (index >= 0) {
        leftList.getItems().remove(index);
      }
    });

    btnClear.setOnAction(handler -> {
      rightList.getItems().clear();
    });

    primaryStage.setTitle("Controls");
    primaryStage.setScene(scene);
    primaryStage.show();

  }

  String cipherString(String string) {
    String cipheredString = "";
    Integer stringLength = string.length();
    String end = "";
    if (stringLength % 2 != 0) {
      end = "" + string.charAt(stringLength / 2 );
    }

    for (int index = 0; index < stringLength/2; index += 1) {
      String first = "" + string.charAt(index);
      String second = "" + string.charAt(stringLength-1-index);
      cipheredString =  cipheredString+first+second;
    }
    cipheredString =  cipheredString+end;
    return cipheredString;
  }

  public static void main(String[] args) {
    launch(args);
  }
}
