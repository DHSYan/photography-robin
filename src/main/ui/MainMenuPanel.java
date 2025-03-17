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

// Represents the panel in which the Main Menu is displayed
public class MainMenuPanel extends JPanel {

    private JLabel introLbl;

    private JButton addBtn;
    private JButton delBtn;
    private JButton saveBtn;
    private JButton loadBtn;

    public MainMenuPanel(PhotoManager pm) {
        JFileChooser fc = new JFileChooser();

        setBackground(Color.GRAY);
        setPreferredSize(new Dimension(200, 200));
        introLbl = new JLabel("Welcome to PhotoManager!");

        addBtn = new JButton("Add Photos");

        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                int returnVal = fc.showOpenDialog(MainMenuPanel.this);
                File seleceted = fc.getSelectedFile();
                pm.addPhoto(new Photo(seleceted.getPath()));
            }
        });


        delBtn = new JButton("Show To Delete Photos");
        saveBtn = new JButton("Save Data");
        loadBtn = new JButton("Load Data");

        add(introLbl);
        add(addBtn);
        add(delBtn);
        add(saveBtn);
        add(loadBtn);
    }

}
