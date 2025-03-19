package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JFrame;

import model.Photo;
import model.PhotoManager;

public class ViewFrame extends JFrame {
    private final double imageDownScaler = 0.15;

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
        curIndex = 1;
        pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.add(Box.createVerticalStrut(15));
        curImageIcon = new ImageIcon(curPhoto.getPath());
        curImageLabel = new JLabel(curImageIcon);
        curImageLabel.setSize((int) (curImageIcon.getIconWidth() * imageDownScaler),
                (int) (curImageIcon.getIconHeight() * imageDownScaler));
        pane.add(curImageLabel);
        add(pane);
    }

    private void nextPhoto() { 
        pane.remove(curImageLabel);
        curIndex = (curIndex + 1) % pm.getPhotos().size();
        curPhoto = pm.getPhotos().get(curIndex);
        curImageIcon = new ImageIcon(curPhoto.getPath());
        curImageLabel = new JLabel(curImageIcon);
        curImageLabel.setSize((int) (curImageIcon.getIconWidth() * imageDownScaler),
                (int) (curImageIcon.getIconHeight() * imageDownScaler));
    }

    private void drawPhoto() {
        pane.add(curImageLabel);
        validate();
        repaint();
    }

}
