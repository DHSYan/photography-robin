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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JFrame;

import model.PhotoManager;

// Reference:
// - https://github.students.cs.ubc.ca/CPSC210/C3-LectureLabStarter/
// - For learning how to scaling an image down 
//    - https://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel
// - https://www.youtube.com/watch?v=Nx4YC5_HFsk
// - https://docs.oracle.com/javase/tutorial/2d/images/drawimage.html 

// ViewDeleteFrame represents the Frame that cycles through all the 
// to be deleted photos.
public class ViewDeleteFrame extends JFrame {
    private final double imageDownScaler = 0.1;

    private JPanel pane;
    
    private PhotoManager pm;
    private String curPhotoPath;
    private JLabel curImageLabel;
    private int curIndex = 0;

    // MODIFIES: this
    // EFFECTS: constructs the viewing frame that shows the to-be-deleted photos
    public ViewDeleteFrame(PhotoManager photoManager) {
        super("Viewing Photos");

        pm = photoManager;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.add(Box.createVerticalStrut(15)); 
        nextPhoto();

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nextPhoto();
                drawPhoto();
            }
        });

        add(nextButton, BorderLayout.NORTH);
        add(pane);

        pack();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: places photo as JLabel on the main panel
    private void nextPhoto() {
        if (curImageLabel != null) {
            pane.remove(curImageLabel);
        }

        curImageLabel = new JLabel(fetchPhoto(), SwingConstants.CENTER);

        pane.add(curImageLabel);
    }

    // MODIFIES: this
    // EFFECTS: Goes to the next photo in the cycle, and returns it as a ImageIcon
    private ImageIcon fetchPhoto() { 
        curPhotoPath = pm.filterToBeDeleted().get(curIndex);
        curIndex = (curIndex + 1) % pm.filterToBeDeleted().size();

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(curPhotoPath));
        } catch (IOException e) {
            // pass
        }

        Image scaledImg = img.getScaledInstance(
                (int) (img.getWidth() * imageDownScaler),
                (int) (img.getHeight() * imageDownScaler),
                Image.SCALE_SMOOTH);

        return new ImageIcon(scaledImg);
    }

    // MODIFIES: this 
    // EFFECTS: refreshes the main frame to update the current photo displayed
    private void drawPhoto() {
        pane.add(curImageLabel);
        validate();
        repaint();
    }

}
