package app.user;

import app.Admin;
import fileio.input.UserInput;

public abstract class UserFactory {
    private static UserFactory instance = null;

    /**
     * Constructor
     */
    private UserFactory() {
    }
    /**
     * Gets the instance of the user factory
     * @return the instance of the user factory
     */
    public static UserFactory getInstance() {
        if (instance == null) {
            instance = new UserFactory() {
            };
        }
        return instance;
    }
    /**
     * Creates a user
     * @param userInput the user input
     * @param type the type of the user
     * @return a string with the result of the creation
     */
    public String createUser(final UserInput userInput, final String type) {
     switch (type) {
         case "user":
             Admin.addUser(userInput);
             Admin.getUser(userInput.getUsername()).setPage("Home");
             break;
         case "artist":
             Admin.addArtist(userInput);
             break;
         case "host":
             Admin.addHost(userInput);
             break;
         default: return "Invalid type";
     }
     return "The username " + userInput.getUsername() + " has been added successfully.";
    }
}
