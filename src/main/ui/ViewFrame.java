package ui;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JFrame;

import model.PhotoManager;

public class ViewFrame extends JFrame {

    private ViewPanel vp;

    public ViewFrame(PhotoManager pm) {
        super("Viewing Photos");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        vp = new ViewPanel(pm);

        add(vp);

        setSize(new Dimension(500,500));
        setVisible(true);
    }

}
