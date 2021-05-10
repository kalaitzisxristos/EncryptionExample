import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Anonymizer {

  private  String CONF_FILE_NAME = "config.txt";
  private  String DATA_FILE_NAME = "dataset.txt";
  private ArrayList<String> datasetLines = new ArrayList<>();
  private ArrayList<String> configLines = new ArrayList<>();

  public Anonymizer(String configFileName, String dataFileName) {
    System.out.println("Starting anonymizer...");
    CONF_FILE_NAME = configFileName;
    DATA_FILE_NAME = dataFileName;
  }

  public void start() {
    Actions.printActions();
  }


  public void chooseDataFile() {
    loadFiles(datasetLines, DATA_FILE_NAME);
    System.out.println("data file choosen.");
  }

  public void chooseConfigFile() {
    loadFiles(configLines, CONF_FILE_NAME);
    System.out.println("Configuration file choosen.");
  }

  public String[] createHeader() {
    System.out.println("Creating header....");
    String[] header = datasetLines.get(0).split("\t+");
    datasetLines.remove(0);
    System.out.println("Header created succesfully.");
    return header;

  }

  public String[][] parseData() {
    System.out.println("Parsing data...");
    String[][] data = new String[datasetLines.size()][0];
    for (int i = 0; i < datasetLines.size(); i++) {
      data[i] = datasetLines.get(i).split("\t+");
    }
    System.out.println("Data parsed succesfully.");
    return data;
  }

  public String[][] encrypt(String[] header, String[][] data, String sKey) {
    System.out.println("Encrypting data...");
    for (String field : configLines) {
      for (int i = 0; i < header.length; i++) {
        try {
          if (header[i].equals(field)){
            for (String[] line : data) {
              String encrypted = AES.encrypt(line[i], sKey);
              line[i].replace(line[i], encrypted);

              System.out.println(encrypted + " = " + line[i]);
            }
          }
        } catch (ArrayIndexOutOfBoundsException e) {}
      }
      System.out.println("");
    }
    System.out.println("data encrypted succesfully.");
    return data;
  }

  public String createKey() {
    return "12334567890qwert";
  }

  public void printData() {
    for (String field : configLines) {
      System.out.println(field);
    }
    for (String line : datasetLines) {
      System.out.println(line);
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







}
