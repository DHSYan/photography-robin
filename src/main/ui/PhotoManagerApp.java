package ui;

import java.io.IOException;
import java.util.Scanner;

import model.Photo;
import model.PhotoManager;
import persistence.JsonReader;

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
        System.out.print("\td - view to be deleted photos\n");
        System.out.print("\tl - load existing data\n");
        System.out.println("\tq - Quit");
    }

    // MODIFIES: this
    // EFFECTS: process user command
    public void processCommand(String command) {
        if (command.equals("a")) { 
            doAddPhoto();
        } else if (command.equals("v")) {
            doViewPhotos();
        } else if (command.equals("d")) { 
            viewToBeDeleted();
        } else if (command.equals("l")) {
            doLoadData();
        } else {
            System.out.println("Invalid Command!");
        }
    }

    // MODIFIES: this 
    // EFFECTS: loads an existing data, returns true if sucessful
    
    public boolean doLoadData() {
        boolean status = false;
        System.out.println("Enter the relative path of the json file");
        String path = input.next();
        JsonReader reader = new JsonReader(path);

        try {
            pm = reader.read();
            status = true;
        } catch (IOException e) {
            System.out.println("Can't find the file, typo?");
        }
        return status;
    }


    // MODIFIES: this
    // EFFECTS: displays all the photo one by one, and asking for actions to be done 
    //          on the current photo
    public void doViewPhotos() {
        for (Photo p : pm.getPhotos()) {
            photoViewer(p);
        }
    }

    // MODIFIES: p
    // EFFECTS: display and retrieves actions to be done on the photo
    public void photoViewer(Photo p) {
        boolean isDone = false;
        
        while (!isDone) {
            System.out.println(p.getPath());
            System.out.println("Options: ");
            System.out.println("\tn - next");
            System.out.println("\tr - give rating");
            System.out.println("\tt - set to delete flag");

            String photoViewerCommand = input.next();
            
            if (photoViewerCommand.equals("n")) {
                isDone = true;
            } else {
                if (photoViewerCommand.equals("t")) {
                    p.setToDelete();
                } else if (photoViewerCommand.equals("r")) {
                    String rating = input.next();
                    if (rating.length() != 1) {
                        System.out.println("Rating must be between 0 and 5");
                    } else { 
                        p.setRating(rating.charAt(0) - '0');
                    }
                } 
            }
        }

    }

    // MODIFIES: this
    // EFFECTS: add photo to PhotoManager
    public void doAddPhoto() {
        System.out.println("Enter the path of the photo");
        String path = input.next();

        pm.addPhoto(new Photo(path));
    }

    // EFFECTS: print all the photo's path whose deleted tag is on
    public void viewToBeDeleted() {
        for (String path : pm.filterToBeDeleted()) {
            System.out.println(path);
        }
    }

}

// Referenced:
//   - TellerApp provided on the Project Description Page for Phase 1 for the 
//     overall structure of a UI component