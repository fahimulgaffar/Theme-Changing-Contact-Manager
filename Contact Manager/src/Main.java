

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
  @Override
  public void start(Stage primaryStage) {
    try {
      Pane root = (Pane) FXMLLoader.load(getClass().getResource("Sample.fxml"));
      Scene scene = new Scene(root, 720, 640);
      scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      primaryStage.setScene(scene);
      primaryStage.setTitle("Contact Manager By Fahimul Gaffar Light Mode");
      primaryStage.getIcons().add(new Image("file:///C://Users//Gaffar//Downloads//Contacts-icon.png"));
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
