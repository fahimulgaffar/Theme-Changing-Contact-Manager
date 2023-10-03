package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ListView;

import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;

public class SampleController {
  @FXML
  private ListView<Person> ListViewer;
  @FXML
  private TextField FirstNameTextField;
  @FXML
  private TextField LastNameTextField;
  @FXML
  private TextField MobileTextField;
  @FXML
  private TextField HomeTextField;
  @FXML
  private TextField EmailTextField;
  @FXML
  private CheckBox TickBox;
  @FXML
  private ImageView ImageViewer;
  @FXML
  private ToggleButton DarkMode;

  private String selectedFilePath = null;

  private ArrayList<Person> PersonList = null;

  private ObservableList<Person> ObserveAblePersonList = null;

  int index = -1;

  @FXML
  public void ChooseContactPictureHandler(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    Stage primaryStage = (Stage) this.ImageViewer.getScene().getWindow();
    File selectedFile = fileChooser.showOpenDialog(primaryStage);

    if (selectedFile != null) {
      selectedFilePath = selectedFile.toURI().getPath();
      ImageViewer.setImage(new Image("file://" + selectedFilePath));
    }
  }

  @FXML
  public void AddContactHandler(ActionEvent event) {
    String FirstName = FirstNameTextField.getText();
    String LastName = LastNameTextField.getText();
    String MobileNumber = MobileTextField.getText();
    String HomeNumber = HomeTextField.getText();
    String Email = EmailTextField.getText();
    String ImageLocation = selectedFilePath;
    boolean tickbox = TickBox.isSelected();

    try {
      if (FirstName.isEmpty()) {
        throw new NoDataException("First Name Can not be Empty");
      }
      if (LastName.isEmpty()) {
        throw new NoDataException("Last Name Can not be Empty");
      }
      if (MobileNumber.isEmpty() && HomeNumber.isEmpty()) {
        throw new NoDataException("Contact must have atleast one number");
      }
      if (Email.isEmpty()) {
        throw new NoDataException("Email Adress Can not be Empty");
      }
      if (FirstName.length() < 3) {
        throw new NameException("First Name must be atleast 3 characters long");
      }
      if (LastName.length() < 3) {
        throw new NameException("Last Name must be atleast 3 characters long");
      }
      if (MobileNumber.length() != 11 && HomeNumber.isEmpty()) {
        throw new PhoneNumberException("Number Must be exactly 11 digits long");
      }
      if (HomeNumber.length() != 11 && MobileNumber.isEmpty()) {
        throw new PhoneNumberException("Number Must be exactly 11 digits long");
      }
      if (MobileNumber.length() == 11) {
        for (int i = 0; i < 11; i++) {
          if (Character.isDigit(MobileNumber.charAt(i))) {
          } else {
            throw new PhoneNumberException("Mobile Number must be only numerical values");
          }
        }
      }
      if (HomeNumber.length() == 11) {
        for (int i = 0; i < 11; i++) {
          if (Character.isDigit(HomeNumber.charAt(i))) {
          } else {
            throw new PhoneNumberException("Home Number must be only numerical values");
          }
        }
      }
      if (Email.length() < 5) {
        throw new EmailException("Enter a Valid Email Address");
      }
      if (!Email.substring(Email.length() - 4, Email.length()).equals(".com")) {
        throw new EmailException("Enter a Valid Email Address");
      }
      if (index == -1) {
        this.PersonList.add(new Person(FirstName, LastName, MobileNumber, HomeNumber, Email, ImageLocation, tickbox));
        this.ObserveAblePersonList
            .add(new Person(FirstName, LastName, MobileNumber, HomeNumber, Email, ImageLocation, tickbox));
      } else {
        this.PersonList.remove(index);
        this.ObserveAblePersonList.remove(index);
        this.PersonList.add(index,
            new Person(FirstName, LastName, MobileNumber, HomeNumber, Email, ImageLocation, tickbox));
        this.ObserveAblePersonList.add(index,
            new Person(FirstName, LastName, MobileNumber, HomeNumber, Email, ImageLocation, tickbox));
      }
      boolean A = Serializer.serialize(Serializer.databasePath, this.PersonList);

      FirstNameTextField.clear();
      LastNameTextField.clear();
      MobileTextField.clear();
      HomeTextField.clear();
      EmailTextField.clear();
      TickBox.setSelected(false);
      ImageViewer.setImage(null);
      index = -1;

    } catch (NoDataException | NameException | PhoneNumberException | EmailException exception) {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Error Box");
      alert.setHeaderText("Please fix the error:");
      alert.setContentText(exception.getMessage());
      alert.showAndWait();
    }
  }

  @FXML
  public void RemoveContactHandler(ActionEvent event) {
    index = ListViewer.getSelectionModel().getSelectedIndex();
    if (index == -1) {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Error Box");
      alert.setHeaderText("Please fix the error:");
      alert.setContentText("No Contact Selected");
      alert.showAndWait();
    } else {
      this.PersonList.remove(index);
      this.ObserveAblePersonList.remove(index);
      boolean A = Serializer.serialize(Serializer.databasePath, this.PersonList);
    }
    index = -1;
  }


  @FXML
  public void ViewContactHandler(ActionEvent event) {
    index = ListViewer.getSelectionModel().getSelectedIndex();
    if (index == -1) {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Error Box");
      alert.setHeaderText("Please fix the error:");
      alert.setContentText("No Contact Selected");
      alert.showAndWait();
    } else {
      try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ContactViewer.fxml"));
        Pane root = (Pane) loader.load();
        ContactViewerController ViewController = loader.getController();
        ViewController.TransferPersonObject(this.PersonList.get(index));
        Stage primaryStage = (Stage) this.ImageViewer.getScene().getWindow();
        if (primaryStage.getTitle().equals("Contact Manager By Fahimul Gaffar Light Mode")) {
          Stage stage = new Stage();
          stage.setScene(new Scene(root));
          stage.setTitle("Detailed View Light Mode");
          stage.getIcons().add(new Image("file:///C://Users//Gaffar//Downloads//Contacts-icon.png"));
          stage.getScene().getStylesheets().clear();
          stage.getScene().setUserAgentStylesheet(null);
          stage.getScene().getStylesheets().add(getClass().getResource("application.css").toExternalForm());
          stage.show();
        } else {
          Stage stage = new Stage();
          stage.setScene(new Scene(root));
          stage.setTitle("Detailed View Dark Mode");
          stage.getIcons().add(new Image("file:///C://Users//Gaffar//Downloads//Contacts-icon.png"));
          stage.getScene().getStylesheets().clear();
          stage.getScene().setUserAgentStylesheet(null);
          stage.getScene().getStylesheets().add(getClass().getResource("Dark Mode.css").toExternalForm());
          stage.show();
        }
      } catch (IOException ex) {
        System.err.println(ex);
      }
    }
  }

  // Event Listener on Button.onAction
  @FXML
  public void ClearListHandler(ActionEvent event) {
    index = ListViewer.getSelectionModel().getSelectedIndex();
    if (index == -1) {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Error Box");
      alert.setHeaderText("Please fix the error:");
      alert.setContentText("No Contact Selected");
      alert.showAndWait();
    } else {
      this.FirstNameTextField.setText(this.PersonList.get(index).getFirstName());
      this.LastNameTextField.setText(this.PersonList.get(index).getLastName());
      this.MobileTextField.setText(this.PersonList.get(index).getMobileNumber());
      this.HomeTextField.setText(this.PersonList.get(index).getHomeNumber());
      this.EmailTextField.setText(this.PersonList.get(index).getEmail());
      this.TickBox.setSelected(this.PersonList.get(index).isTickbox());
      ImageViewer.setImage(new Image("file://" + this.PersonList.get(index).getImageLocation()));
    }
  }

  @FXML
  public void ResetHandler(ActionEvent event) {
    PersonList.clear();
    ObserveAblePersonList.clear();
    boolean A = Serializer.serialize(Serializer.databasePath, PersonList);
    FirstNameTextField.clear();
    LastNameTextField.clear();
    MobileTextField.clear();
    HomeTextField.clear();
    EmailTextField.clear();
    TickBox.setSelected(false);
    ImageViewer.setImage(null);
  }

  @FXML
  public void DarkModeHandler(ActionEvent event) {
    Stage primaryStage = (Stage) this.ImageViewer.getScene().getWindow();
    if (primaryStage.getTitle().equals("Contact Manager By Fahimul Gaffar Light Mode")) {
      DarkMode.setText("Light Mode");
      primaryStage.setTitle("Contact Manager By Fahimul Gaffar Dark Mode");
      primaryStage.getScene().getStylesheets().clear();
      primaryStage.getScene().setUserAgentStylesheet(null);
      primaryStage.getScene().getStylesheets().add(getClass().getResource("Dark Mode.css").toExternalForm());
    } else {
      DarkMode.setText("Dark Mode");
      primaryStage.setTitle("Contact Manager By Fahimul Gaffar Light Mode");
      primaryStage.getScene().getStylesheets().clear();
      primaryStage.getScene().setUserAgentStylesheet(null);
      primaryStage.getScene().getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    }
  }

  @FXML
  public void initialize() {
    this.PersonList = Serializer.deserialize(Serializer.databasePath);
    if (this.PersonList == null) {
      this.PersonList = new ArrayList<>();
    }
    this.ObserveAblePersonList = FXCollections.observableArrayList(PersonList);
    this.ListViewer.setItems(ObserveAblePersonList);
  }
}
