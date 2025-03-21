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
import javax.swing.JComboBox;
// import javax.swing.JCheckBox;
import javax.swing.JLabel;
// import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JFrame;

import model.Photo;
import model.PhotoManager;

// Reference:
// - https://github.students.cs.ubc.ca/CPSC210/C3-LectureLabStarter/
// - For learning how to scaling an image down 
//    - https://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel
// - https://www.youtube.com/watch?v=Nx4YC5_HFsk
// - https://docs.oracle.com/javase/tutorial/2d/images/drawimage.html 
public class ViewFrame extends JFrame {
    private final double imageDownScaler = 0.1;

    private JPanel pane;
    
    private PhotoManager pm;
    private Photo curPhoto;
    private JLabel curImageLabel;
    private int curIndex = 0;

    private String[] ratings = { "1", "2", "3", "4", "5" };
    private String[] deleteChoice = { "No", "Yes" };
    
    @SuppressWarnings("methodlength")
    public ViewFrame(PhotoManager photoManager) {
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


        add(ratingComboBox(), BorderLayout.SOUTH);
        add(toDeleteComboBox(), BorderLayout.WEST);
        add(nextButton, BorderLayout.NORTH);
        add(pane);

        pack();
        setVisible(true);
    }

    private JComboBox<String> toDeleteComboBox() {
        JComboBox<String> toDeleteComboBox = new JComboBox<String>(deleteChoice);
        toDeleteComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String isToDelete = (String) toDeleteComboBox.getSelectedItem();
                
                if (isToDelete.equals("Yes")) {
                    pm.getPhotos().get(curIndex).setToDelete(true);
                } else {
                    pm.getPhotos().get(curIndex).setToDelete(false);
                }
            }
        });
        return toDeleteComboBox;
    }

    private JComboBox<String> ratingComboBox() {
        JComboBox<String> ratingComboBox = new JComboBox<String>(ratings);
        ratingComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ratingChoice = (String) ratingComboBox.getSelectedItem();
                int rating = ratingChoice.charAt(0) - '0';
                pm.getPhotos().get(curIndex).setRating(rating);
            }
        });
        return ratingComboBox;
    }

    private void nextPhoto() {
        if (curImageLabel != null) {
            pane.remove(curImageLabel);
        }

        curImageLabel = new JLabel(fetchPhoto(), SwingConstants.CENTER);

        pane.add(curImageLabel);
    }

    private ImageIcon fetchPhoto() { 
        curPhoto = pm.getPhotos().get(curIndex);
        curIndex = (curIndex + 1) % pm.getPhotos().size();

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

        return new ImageIcon(scaledImg);
    }

    private void drawPhoto() {
        pane.add(curImageLabel);
        validate();
        repaint();
    }

}
