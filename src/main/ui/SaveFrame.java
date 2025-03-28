package ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JTextField;

import model.Event;
import model.EventLog;
import model.PhotoManager;
import persistence.JsonWriter;

// Reference
// - https://docs.oracle.com/javase/tutorial/uiswing/components/textfield.html

// SaveFrame Represents the window where it prompts user to save the current
// state of the program
public class SaveFrame extends JFrame implements WindowListener {
    private PhotoManager pm;
    private String fileName;

    // MODIFIES: this
    // EFFECTS: constructs the window 
    public SaveFrame(PhotoManager photoManager) {
        super("Save Program");
        
        pm = photoManager;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(200,200));

        JTextField fileNameField = new JTextField();       
        
        fileNameField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileName = fileNameField.getText();
                doSaveData();
            }
        });


        add(fileNameField);

        addWindowListener(this);

        pack();
        setVisible(true);
    }

    // EFFECTS: Writes the current state of the program to a  JSON file to the ./data/ directory, 
    //          returns true is the writing is successful, false otherwise
    public boolean doSaveData() {
        boolean status = false;

        JsonWriter writer = new JsonWriter("./data/" + fileName + ".json");
        try {
            writer.write(pm);
            status = true;
        } catch (IOException e) {
            System.out.println("Can't create a file, maybe you run out of disk space?");
        }

        return status;
    }

    // Reference: 
    // The Window event listener referenced: 
    // https://docs.oracle.com/javase/tutorial/uiswing/events/windowlistener.html
    @Override
    public void windowOpened(WindowEvent e) {
        return;
    }


    @Override
    public void windowClosing(WindowEvent e) {
        for (Event each : EventLog.getInstance()) {
            System.out.println(each.getDate() + " : " + each.getDescription());
        }
    }


    @Override
    public void windowClosed(WindowEvent e) {
        System.out.print("HI");
        for (Event each : EventLog.getInstance()) {
            System.out.println(each.getDate() + ":" + each.getDescription());
        }
    }


    @Override
    public void windowIconified(WindowEvent e) {
        return;
    }


    @Override
    public void windowDeiconified(WindowEvent e) {
        return;
    }


    @Override
    public void windowActivated(WindowEvent e) {
        return;
    }


    @Override
    public void windowDeactivated(WindowEvent e) {
        return;
    }
}
