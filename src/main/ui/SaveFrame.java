package ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

import model.PhotoManager;

public class SaveFrame extends JFrame {
    private PhotoManager pm;

    public SaveFrame(PhotoManager photoManager) {
        super("Save Program");
        
        pm = photoManager;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(200,200));

        JTextField fileNameField = new JTextField();       
        
        fileNameField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fileName = fileNameField.getText();
            }
        });

        add(fileNameField);


        pack();
        setVisible(true);
    }
}
