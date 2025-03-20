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

    private JButton addBtn;
    private JButton delBtn;
    private JButton viewBtn;
    private JButton saveBtn;
    private JButton loadBtn;

    private PhotoManager pm;

    @SuppressWarnings("methodlength")
    public MainMenuPanel(PhotoManager photoManager) {
        pm = photoManager;

        JFileChooser fc = new JFileChooser();

        setBackground(Color.GRAY);
        setPreferredSize(new Dimension(200, 200));
        introLbl = new JLabel("Welcome to PhotoManager!");

        addBtn = new JButton("Add Photos");

        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                // int returnVal = fc.showOpenDialog(MainMenuPanel.this);
                fc.showOpenDialog(MainMenuPanel.this);
                File seleceted = fc.getSelectedFile();
                pm.addPhoto(new Photo(seleceted.getPath()));
            }
        });


        delBtn = new JButton("Show To Delete Photos");
        viewBtn = new JButton("View Photos");

        viewBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewFrame(pm);
            }
        });

        saveBtn = new JButton("Save Data");

        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SaveFrame(pm);
            }
        });


        
        loadBtn = new JButton("Load Data");
        
        loadBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoadFrame(pm);
            }
        });

        add(introLbl);
        add(addBtn);
        add(viewBtn);
        add(delBtn);
        add(saveBtn);
        add(loadBtn);
    }
    

}
