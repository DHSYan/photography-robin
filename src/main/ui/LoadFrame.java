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
import persistence.JsonReader;

// Reference
// - https://docs.oracle.com/javase/tutorial/uiswing/components/textfield.html

// Load Frame represents the Window that instructs user to load an
// existing state of the program.
public class LoadFrame extends JFrame implements WindowListener {
    private PhotoManager pm;
    private String fileName;

    // MODIFIES: this
    // EFFECTS: Constructs a JFrame
    public LoadFrame(PhotoManager photoManager) {
        super("Loading Data");

        pm = photoManager;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(200,200));

        JTextField fileNameField = new JTextField();       
        
        fileNameField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileName = fileNameField.getText();
                doLoadData();
            }
        });


        add(fileNameField);

        addWindowListener(this);

        pack();
        setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS: loads an Json file to the PhotoManager
    public boolean doLoadData() {
        boolean status = false;
        
        JsonReader reader = new JsonReader("./data/" + fileName + ".json");

        try {
            pm.override(reader.read());
            status = true;
        } catch (IOException e) {
            System.out.println("Can't find the file, typo?");
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
