public class Main {
  private static final String CONF_FILE_NAME = "config.txt";
  private static final String DATA_FILE_NAME = "dataset.txt";

  public static void main(String[] args) {

    Anonymizer anonymizer = new Anonymizer(CONF_FILE_NAME, DATA_FILE_NAME);
    anonymizer.start(); //starting anonymizer: printing actions available
    anonymizer.chooseDataFile(); // choosing the data file (`.txt` OR `json` OR `svg`)
    anonymizer.chooseConfigFile(); // config file: the fields about to be encrypted
    String[] header = anonymizer.createHeader(); //creating header from the data
    String[][] data = anonymizer.parseData(); // creating `data` to host the data
    String sKey = anonymizer.createKey(); // creating a key to encrypt
    data = anonymizer.encrypt(header, data, sKey); //encrypting data at `data`
    anonymizer.printData(data); // printing `data`
    anonymizer.createEncryptedFile(data, header); //creating a third file
    //that has the encrypted data asked from `config.txt`
  }
}