import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
  private static final String CONF_FILE_NAME = "config.txt";
  private static final String DATA_FILE_NAME = "dataset.txt";

  public static void main(String[] args) {

    Anonymizer anonymizer = new Anonymizer(CONF_FILE_NAME, DATA_FILE_NAME);

    anonymizer.start();

    anonymizer.chooseDataFile();
    anonymizer.chooseConfigFile();
    String[] header = anonymizer.createHeader();
    String[][] data = anonymizer.parseData();
    String sKey = anonymizer.createKey();
    data = anonymizer.encrypt(header, data, sKey);
    anonymizer.printData();
  }
}