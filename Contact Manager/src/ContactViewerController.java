package application;

import javafx.fxml.FXML;

import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ContactViewerController {

  @FXML
  private Text FirstNameLabelDetailedView;
  @FXML
  private Text LastNameLabelDetailedView;
  @FXML
  private Text MobileNumberLabelDetailedView;
  @FXML
  private Text HomeNumberLabelDetailedView;
  @FXML
  private Text EmailLabelDetailedView;
  @FXML
  private Text DateCreatedOnLabelDetailedView;
  @FXML
  private Text EmergencyContactLabelDetailedView;
  @FXML
  private ImageView ImageViewerDetailedView;
  @FXML
  private Button CloseButton;

  private String ImageLocation = null;

  // Event Listener on Button[#CloseButton].onAction
  @FXML
  public void CloseButtonHandler(ActionEvent event) {
    Stage primaryStage = (Stage) this.CloseButton.getScene().getWindow();
    primaryStage.close();
  }

  public void TransferPersonObject(Person person) {
    this.FirstNameLabelDetailedView.setText(person.getFirstName());
    this.LastNameLabelDetailedView.setText(person.getLastName());
    this.MobileNumberLabelDetailedView.setText(person.getMobileNumber());
    this.HomeNumberLabelDetailedView.setText(person.getHomeNumber());
    this.EmailLabelDetailedView.setText(person.getEmail());
    this.DateCreatedOnLabelDetailedView.setText(person.getDate());
    this.EmergencyContactLabelDetailedView.setText(person.getTickBox());
    this.ImageLocation = person.getImageLocation();
    if (ImageLocation != null) {
      this.ImageViewerDetailedView.setImage(new Image("file://" + ImageLocation));
    }
  }
}
