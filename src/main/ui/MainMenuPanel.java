package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Photo;
import model.PhotoManager;

// Reference: 
// - https://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html
// - https://docs.oracle.com/javase/tutorial/uiswing/examples/components/FileChooserDemoProject/src/components/FileChooserDemo.java

// Represents the panel in which the Main Menu is displayed
public class MainMenuPanel extends JPanel {
    private JLabel introLbl;
    private PhotoManager pm;
    JFileChooser fc;

    // MODIFIES: this
    // EFFECTS: constructs a JPanel that contains all the main menu items
    public MainMenuPanel(PhotoManager photoManager) {
        pm = photoManager;
        fc = new JFileChooser();

        setBackground(Color.GRAY);
        setPreferredSize(new Dimension(200, 200));
        introLbl = new JLabel("Welcome to PhotoManager!");

        add(introLbl);
        add(addBtn());
        add(viewBtn());
        add(delBtn());
        add(saveBtn());
        add(loadBtn());
    }
    
    // EFFECTS: creates and return a JButton for adding photos
    private JButton addBtn() {
        JButton addBtn = new JButton("Add Photos");
        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                // int returnVal = fc.showOpenDialog(MainMenuPanel.this);
                // fc.changeToParentDirectory();
                fc.showOpenDialog(MainMenuPanel.this);
                File seleceted = fc.getSelectedFile();
                pm.addPhoto(new Photo(seleceted.getPath()));
            }
        });
        return addBtn;
    }

    // EFFECTS: creates and return a JButton for viewing Photos
    private JButton viewBtn() {
        JButton viewBtn = new JButton("View Photos");
        viewBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewFrame(pm);
            }
        });
        return viewBtn;
    }

    // EFFECTS: creates and return a JButton for viewing To-be-deleted photos
    private JButton delBtn() {
        JButton delBtn = new JButton("Show To Delete Photos");
        delBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewDeleteFrame(pm);
            }
        });

        return delBtn;
    }

    // EFFECTS: creates and return a JButton for saving the state of the program
    private JButton saveBtn() {
        JButton saveBtn = new JButton("Save Data");
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SaveFrame(pm);
            }
        });
        
        return saveBtn;
    }

    // EFFECTS: creates and return a JButton for loading an existing state of the program
    private JButton loadBtn() {
        JButton loadBtn = new JButton("Load Data");
        loadBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoadFrame(pm);
            }
        });

        return loadBtn;
    }

}
