package ui;

import java.awt.Dimension;

import javax.swing.*;

import model.Photo;

public class PhotoManagerGUI extends JFrame {
    private MenuPanel menu;

    public PhotoManagerGUI() {
        // JFrame frame = new JFrame();
        super("Photo Manager");

        menu = new MenuPanel();
        add(menu);

        pack();
        
        setSize(new Dimension(1000,1000));
        setVisible(true);
    }


    public static void main(String[] args) {
        new PhotoManagerGUI();
    }
    

}
