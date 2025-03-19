package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import model.PhotoManager;
import model.Photo;

public class ViewPanel extends JPanel {
    private final double imageDownScaler = 0.15;


    private JButton nextBtn;
    private JButton tagDelBtn;
    private JMenuItem ratingSelectMenu;

    private ImageIcon curPhotoFile;
    private String curImagePath;
    private Photo curPhoto;
    private int currentIndex;

    public ViewPanel(PhotoManager pm) {
        setBackground(Color.GRAY);

        setPreferredSize(new Dimension(1000, 1000));

        currentIndex = 0;

        ArrayList<Photo> listOfPhotos = pm.getPhotos();

        nextBtn = new JButton("Next");
        nextBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                curPhoto = listOfPhotos.get(currentIndex);
                currentIndex++;
            }
        });

        add(nextBtn);
    }

    // Reference: 
    // - https://www.youtube.com/watch?v=Nx4YC5_HFsk
    // - https://docs.oracle.com/javase/tutorial/2d/images/drawimage.html 
    @Override
    protected void paintComponent(Graphics g) { 
        super.paintComponent(g);
        curPhotoFile = new ImageIcon(curPhoto.getPath());

        g.drawImage(curPhotoFile.getImage(), 0, 0,
                (int) (curPhotoFile.getIconWidth() * imageDownScaler),
                (int) (curPhotoFile.getIconHeight() * imageDownScaler),
                null);

    }
}
