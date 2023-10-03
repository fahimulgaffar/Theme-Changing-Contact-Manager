package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Serializer {

  // path to the database
  private static String OS = System.getProperty("os.name").toLowerCase();

  // Windows uses double '\' ( 🤦‍♂️ ), linux and Mac OS (like sensible OSs) use
  // single '/' ❤
  public static final String databasePath = OS.indexOf("win") >= 0 ? System.getProperty("user.home") + "\\database.bin"
      : System.getProperty("user.home") + "/database.bin";

  // serializing the arraylist to the database file
  public static boolean serialize(String filePath, ArrayList<Person> personsList) {
    File databaseFile = new File(filePath);
    FileOutputStream fileOutputStream = null;
    ObjectOutputStream objectOutputStream = null;

    boolean success = false;
    try {
      fileOutputStream = new FileOutputStream(databaseFile);
      objectOutputStream = new ObjectOutputStream(fileOutputStream);
      objectOutputStream.writeObject(personsList);
      success = true;
    } catch (Exception exception) {
      success = false;
    }

    return success;
  }

  // deserializing the arraylist from the database file
  public static ArrayList<Person> deserialize(String filePath) {
    File databaseFile = new File(filePath);
    FileInputStream fileInputStream = null;
    ObjectInputStream objectInputStream = null;

    ArrayList<Person> list = null;

    try {
      fileInputStream = new FileInputStream(databaseFile);
      objectInputStream = new ObjectInputStream(fileInputStream);

      list = (ArrayList<Person>) objectInputStream.readObject();
    } catch (Exception exception) {
      System.out.println(exception.toString());
    }

    return list;
  }
}
