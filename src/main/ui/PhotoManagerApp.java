package ui;

import java.util.Scanner;

import model.PhotoManager;

public class PhotoManagerApp {
    private PhotoManager pm;
    private Scanner input;

    // EFFECTS: runs the PhotoManager Application
    public PhotoManagerApp() {
        runPhotoManager();
    }

    // MODIFIES: this
    // EFFECTS: process user input
    public void runPhotoManager() {
        boolean loop = true;
        String userCommand = null;

        System.out.println("Welcome to Photo Manager!\n");

        init();

        while (loop) {
            displayMenu();

            userCommand = input.next();
            
            if (userCommand.equals("q")) {
                loop = false;
            } else {
                processCommand(userCommand);
            }
        }

        System.out.println("\n Thanks for using Photo Manager");
    }

    // MODIFIES: this
    // EFFECTS: initializes PhotoManager 
    public void init() {
        input = new Scanner(System.in);
        pm = new PhotoManager();
    }
    
    // EFFECTS: displays menu, with options of command available
    public void displayMenu() {
        System.out.print("Menu:\n");
        System.out.print("\ta - add photos\n");
        System.out.print("\tv - view photos\n");
        // TODO
    }

    // MODIFIES: this
    // EFFECTS: process user command
    public void processCommand(String command) {
        // stub
    }

    // TODO
    // MODIFIES: this
    // EFFECTS: displays all the photo one by one, and asking for actions to be done 
    //          on the current photo
    public void doViewPhotos() {
        // stub
    }


}

// Referenced:
//   - TellerApp provided on the Project Description Page for Phase 1 for the 
//     overall structure of a UI component