package ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {

    private JLabel introLbl;

    private JButton addBtn;
    private JButton delBtn;
    private JButton saveBtn;
    private JButton loadBtn;

    public MenuPanel() {
        setBackground(Color.GRAY);
        setPreferredSize(new Dimension(200, 200));
        introLbl = new JLabel("Welcome to PhotoManager!");

        addBtn = new JButton("Add Photos");
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
