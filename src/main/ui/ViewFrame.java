package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JFrame;

import model.Photo;
import model.PhotoManager;

// Reference:
// - https://github.students.cs.ubc.ca/CPSC210/C3-LectureLabStarter/
public class ViewFrame extends JFrame {
    private final double imageDownScaler = 0.1;

    private JPanel pane;
    
    private PhotoManager pm;
    private Photo curPhoto;
    private ImageIcon curImageIcon;
    private JLabel curImageLabel;
    private int curIndex = 0;
    

    public ViewFrame(PhotoManager photoManager) {
        super("Viewing Photos");

        pm = photoManager;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // vp = new ViewPanel(pm);
        // add(vp);

        firstPhoto();

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nextPhoto();
                drawPhoto();
            }
        });

        add(nextButton, BorderLayout.NORTH);


        setSize(new Dimension(1000,1000));
        pack();
        setVisible(true);
    }

    private void firstPhoto() {
        curPhoto = pm.getPhotos().getFirst();

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(curPhoto.getPath()));
        } catch (IOException e) {
            // pass
        }

        Image scaledImg = img.getScaledInstance(
                (int) (img.getWidth() * imageDownScaler),
                (int) (img.getHeight() * imageDownScaler),
                Image.SCALE_SMOOTH);

        pane = new JPanel();
        pane.setSize(new Dimension(500, 500));
        pane.setMaximumSize(new Dimension(1000,1000));
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.add(Box.createVerticalStrut(15));
        curImageIcon = new ImageIcon(scaledImg);
        curImageLabel = new JLabel(curImageIcon, SwingConstants.CENTER);
        curImageLabel.setSize((int) (curImageIcon.getIconWidth() * imageDownScaler),
                (int) (curImageIcon.getIconHeight() * imageDownScaler));
        pane.add(curImageLabel);
        curImageLabel.setMaximumSize(new Dimension(1000, 1000));        
        add(pane);
    }

    private void nextPhoto() { 
        pane.remove(curImageLabel);
        curIndex = (curIndex + 1) % pm.getPhotos().size();
        curPhoto = pm.getPhotos().get(curIndex);
        curImageIcon = new ImageIcon(curPhoto.getPath());
        curImageLabel = new JLabel(curImageIcon, SwingConstants.CENTER);
        curImageLabel.setSize((int) (curImageIcon.getIconWidth() * imageDownScaler),
                (int) (curImageIcon.getIconHeight() * imageDownScaler));
        curImageLabel.setMaximumSize(new Dimension(1000, 1000));        
    }

    private void drawPhoto() {
        pane.add(curImageLabel);
        validate();
        repaint();
    }

}
