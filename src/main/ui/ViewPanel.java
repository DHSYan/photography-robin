package ui;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import model.PhotoManager;

public class ViewPanel extends JPanel {
    private JButton nextBtn;
    private JButton tagDelBtn;
    private JMenuItem ratingSelectMenu;

    private ImageIcon curPhotoFile;

    public ViewPanel(PhotoManager pm) {
        setBackground(Color.GRAY);
        setPreferredSize(new Dimension(200, 200));
    }

    @Override
    protected void paintComponent(Graphics g) { 
        super.paintComponent(g);
        curPhotoFile = new ImageIcon("./photos/IMG_1672.JPG");

        g.drawImage(curPhotoFile.getImage(), 100, 100, null);

    }
}
