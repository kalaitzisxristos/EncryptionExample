public class Actions {

  public static void printActions() {
    System.out.println("Actions are:\n" +
            "• choose data file (example `dataset.txt`)\n" +
            "• choose configuration file (example `config.txt`)\n" +
            "• print data\n\n" +
            "You should first load files before try to encrypt any data.");
  }


  public Actions() {
    System.out.println("Action class called");
  }
}
