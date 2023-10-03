package application;

import java.io.Serializable;
import java.time.LocalDate;

public class Person implements Serializable {
  @Override
  public String toString() {
    return FirstName + " " + LastName;
  }

  private String FirstName;
  private String LastName;
  private String MobileNumber;
  private String HomeNumber;
  private String Email;
  private String ImageLocation;
  private boolean Tickbox;
  private String date;

  public Person(String FirstName, String LastName, String MobileNumber, String HomeNumber, String Email,
      String ImageLocation, boolean Tickbox) {
    this.FirstName = FirstName;
    this.LastName = LastName;
    this.MobileNumber = MobileNumber;
    this.HomeNumber = HomeNumber;
    this.Email = Email;
    this.ImageLocation = ImageLocation;
    this.Tickbox = Tickbox;
    this.date = this.getDate();
  }

  public String getDate() {
    return LocalDate.now().toString();
  }

  public String getFirstName() {
    return FirstName;
  }

  public String getTickBox() {
    if (Tickbox) {
      return "Yes";
    } else {
      return "No";
    }
  }

  public void setFirstName(String firstName) {
    FirstName = firstName;
  }

  public String getLastName() {
    return LastName;
  }

  public void setLastName(String lastName) {
    LastName = lastName;
  }

  public String getMobileNumber() {
    return MobileNumber;
  }

  public void setMobileNumber(String mobileNumber) {
    MobileNumber = mobileNumber;
  }

  public String getHomeNumber() {
    return HomeNumber;
  }

  public void setHomeNumber(String homeNumber) {
    HomeNumber = homeNumber;
  }

  public String getEmail() {
    return Email;
  }

  public void setEmail(String email) {
    Email = email;
  }

  public String getImageLocation() {
    return ImageLocation;
  }

  public void setImageLocation(String imageLocation) {
    ImageLocation = imageLocation;
  }

  public boolean isTickbox() {
    return Tickbox;
  }

  public void setTickbox(boolean tickbox) {
    Tickbox = tickbox;
  }
}
