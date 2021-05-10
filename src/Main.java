import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
  private static final String CONF_FILE_NAME = "config.txt";
  private static final String DATA_FILE_NAME = "dataset.txt";

  public static void main(String[] args) {

    //create a key
    String sKey="12334567890qwert";  // 16 byte or 129 bit value

    // Declare datasetLines array
    ArrayList<String> datasetLines = new ArrayList<>();
    ArrayList<String> configLines = new ArrayList<>();

    // Load all datasetLines from `DATA_FILE_NAME` to `datasetLines`
    loadFiles(datasetLines, DATA_FILE_NAME);

    // Parse header
    String[] header = datasetLines.get(0).split("\t+");
    datasetLines.remove(0);

    // Parse data
    String[][] data = new String[datasetLines.size()][0];
    for (int i = 0; i < datasetLines.size(); i++) {
      data[i] = datasetLines.get(i).split("\t+");
    }

    // Print data
    printData(header, data);

    //read `config.txt`
    // Load all datasetLines from `CONF_FILE_NAME` to `datasetLines`
    loadFiles(configLines, CONF_FILE_NAME);

    // Print data
    for (String field : configLines) {
      System.out.println(field);

    }

    //Encrypt data that being asked at `config.txt`
    System.out.println("\n\nEncrypt data that being asked at `config.txt`");

    for (String field : configLines) {
      for (int i = 0; i < header.length; i++) {
        try {
          if (header[i].equals(field)){
            for (String[] line : data) {
              line[i] = AES.encrypt(line[i], sKey);

              System.out.println(line[i]);
            }
          }
        } catch (ArrayIndexOutOfBoundsException e) {}
      }
      System.out.println("");
    }

    //decrypt
    for (String field : configLines) {
      for (int i = 0; i < header.length; i++) {
        try {
          if (header[i].equals(field)){
            for (String[] line : data) {
              line[i] = AES.decrypt(line[i], sKey);

              System.out.println(line[i]);
            }
          }
        } catch (ArrayIndexOutOfBoundsException e) {}
      }
      System.out.println("");
    }
  }

  private static void loadFiles(ArrayList<String> lines, String dataFileName) {
    try {
      File file = new File(dataFileName);
      Scanner dataReader = new Scanner(file);
      while (dataReader.hasNextLine()) {
        String line = dataReader.nextLine();
        lines.add(line);
      }
      dataReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  private static void printData(String[] header, String[][] data) {
    for (String[] line : data) {
      for (int i = 0; i < header.length; i++) {
        try {
          String field = line[i];
          System.out.print(header[i] + ": ");
          System.out.println(field);
        } catch (ArrayIndexOutOfBoundsException e) {}
      }
      System.out.println("");
    }
  }


}
