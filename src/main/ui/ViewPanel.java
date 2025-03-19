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
import javax.swing.JFrame;

import model.PhotoManager;
import model.Photo;

public class ViewPanel extends JPanel {
    private final double imageDownScaler = 50;


    private JButton nextBtn;
    private JButton tagDelBtn;
    private JMenuItem ratingSelectMenu;

    private Photo curPhoto;
    private JPanel curPane;
    private JFrame curFrame;
    private int currentIndex;

    public ViewPanel(PhotoManager pm) {
        setBackground(Color.GRAY);
        setPreferredSize(new Dimension(1000, 1000));

        currentIndex = 0;
        ArrayList<Photo> listOfPhotos = pm.getPhotos();
        curPhoto = listOfPhotos.get(currentIndex);


        curFrame = new JFrame();
        nextBtn = new JButton("Next");
        
        nextBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                curFrame.setVisible(false);
                curFrame.remove(curPane);
                currentIndex = (currentIndex + 1) % listOfPhotos.size();
                curPhoto = listOfPhotos.get(currentIndex);
                curPane = renderImage(curPhoto);
                curFrame.add(curPane);
                curFrame.setVisible(true);
                
            }
        });

        add(nextBtn);
        curPane = renderImage(curPhoto);
        curFrame.add(curPane);
        curFrame.setVisible(true);
    }

    private JPanel renderImage(Photo p) { 
        
        JPanel pane = new JPanel() {
            // Reference: 
            // - https://www.youtube.com/watch?v=Nx4YC5_HFsk
            // - https://docs.oracle.com/javase/tutorial/2d/images/drawimage.html 
            @Override
            protected void paintComponent(Graphics g) { 
                super.paintComponent(g);
                ImageIcon curPhotoFile = new ImageIcon(p.getPath());

                g.drawImage(curPhotoFile.getImage(), 0, 0,
                        (int) (curPhotoFile.getIconWidth() * imageDownScaler),
                        (int) (curPhotoFile.getIconHeight() * imageDownScaler),
                        null);

            }
        };
        return pane;
    }
}
