package ui;

import java.awt.Dimension;

import javax.swing.*;

import model.PhotoManager;

// Structure of Code and Inspiration came from the following: 
// - https://github.students.cs.ubc.ca/CPSC210/C3-LectureLabStarter
// - https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
// - https://www.geeksforgeeks.org/introduction-to-java-swing/
// - https://www.cs.cmu.edu/~pattis/15-1XX/15-200/lectures/view/lecture.html
// - https://docs.oracle.com/javase/7/docs/api/javax/swing/package-summary.html

// PhotoManagerGUI represents the main window frame where the PhotoManager is displayed
public class PhotoManagerGUI extends JFrame {
    private PhotoManager pm;
    private MainMenuPanel menu;

    // MODIFIES: this
    // EFFECTS: Constructs the main frame where every part of the GUI is hosted.
    public PhotoManagerGUI() {
        super("Photo Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pm = new PhotoManager();

        menu = new MainMenuPanel(pm);
        add(menu);
        
        setSize(new Dimension(1000,1000));
        pack();
        setVisible(true);
    }


    // Run the GUI
    public static void main(String[] args) {
        new PhotoManagerGUI();
    }
    

}
