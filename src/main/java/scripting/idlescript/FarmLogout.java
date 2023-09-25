package scripting.idlescript;

public class FarmLogout extends IdleScript {
  /**
   * This function is the entry point for the program. It takes an array of parameters and executes
   * script based on the values of the parameters. <br>
   * Parameters in this context can be from CLI parsing or in the script options parameters text box
   *
   * @param parameters an array of String values representing the parameters passed to the function
   */
  public int start(String[] parameters) {
    while (!controller.isLoggedIn()) return 100;

    controller.logout();
    controller.stop();
    System.exit(0);
    return 100;
  }
}
