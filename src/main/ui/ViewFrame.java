package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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

import model.EventLog;
import model.Event;
import model.Photo;
import model.PhotoManager;

// Reference:
// - https://github.students.cs.ubc.ca/CPSC210/C3-LectureLabStarter/
// - For learning how to scaling an image down 
//    - https://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel
// - https://www.youtube.com/watch?v=Nx4YC5_HFsk
// - https://docs.oracle.com/javase/tutorial/2d/images/drawimage.html 

// ViewFrame represents the Frame that cycles through all the photos that 
// has been added to the photomanager
public class ViewFrame extends JFrame implements WindowListener {
    private final double imageDownScaler = 0.1;

    private JPanel pane;
    
    private PhotoManager pm;
    private Photo curPhoto;
    private JLabel curImageLabel;
    private int curIndex = 0;

    private String[] ratings = { "1", "2", "3", "4", "5" };
    private String[] deleteChoice = { "No", "Yes" };
    

    // MODIFIES: this
    // EFFECTS: constructs the viewing frame that shows all the photos
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

        addWindowListener(this);

        pack();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates and returns a ComboBox for tagging a photo 
    // as "to-be-deleted"
    private JComboBox<String> toDeleteComboBox() {
        JComboBox<String> toDeleteComboBox = new JComboBox<String>(deleteChoice);
        toDeleteComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String isToDelete = (String) toDeleteComboBox.getSelectedItem();
                
                if (isToDelete.equals("Yes")) {
                    curPhoto.setToDelete(true);
                } else {
                    curPhoto.setToDelete(false);
                }
            }
        });
        return toDeleteComboBox;
    }

    // MODIFIES: this
    // EFFECTS: creates and returns a ComboBox for giving a photo ratings
    private JComboBox<String> ratingComboBox() {
        JComboBox<String> ratingComboBox = new JComboBox<String>(ratings);
        ratingComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ratingChoice = (String) ratingComboBox.getSelectedItem();
                int rating = ratingChoice.charAt(0) - '0';
                curPhoto.setRating(rating);
            }
        });
        return ratingComboBox;
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

    // MODIFIES: this 
    // EFFECTS: refreshes the main frame to update the current photo displayed
    private void drawPhoto() {
        pane.add(curImageLabel);
        validate();
        repaint();
    }


    // Reference: 
    // The Window event listener referenced: 
    // https://docs.oracle.com/javase/tutorial/uiswing/events/windowlistener.html
    @Override
    public void windowOpened(WindowEvent e) {
        return;
    }


    @Override
    public void windowClosing(WindowEvent e) {
        for (Event each : EventLog.getInstance()) {
            System.out.println(each.getDate() + " : " + each.getDescription());
        }
    }


    @Override
    public void windowClosed(WindowEvent e) {
        System.out.print("HI");
        for (Event each : EventLog.getInstance()) {
            System.out.println(each.getDate() + ":" + each.getDescription());
        }
    }


    @Override
    public void windowIconified(WindowEvent e) {
        return;
    }


    @Override
    public void windowDeiconified(WindowEvent e) {
        return;
    }


    @Override
    public void windowActivated(WindowEvent e) {
        return;
    }


    @Override
    public void windowDeactivated(WindowEvent e) {
        return;
    }
}
