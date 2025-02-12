package model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPhotoManager {

    private PhotoManager empty;
    private PhotoManager pm1;
    private PhotoManager pm2;

    private Photo p1;
    private Photo p2;
    private Photo p3;

    private ArrayList<Photo> target1;
    private ArrayList<Photo> target2;
    private ArrayList<Photo> target3;

    @BeforeEach
    void runBefore() {
        empty = new PhotoManager();
        pm1 = new PhotoManager();
        pm2 = new PhotoManager();

        p1 = new Photo("/home/tzen/cpsc210/ProjectStarter/photos/IMG_1672.JPG");
        p2 = new Photo("/home/tzen/cpsc210/ProjectStarter/photos/IMG_1778.JPG");
        p3 = new Photo("/home/tzen/cpsc210/ProjectStarter/photos/IMG_2221.JPG");

        p1.setRating(0);
        p2.setRating(0);
        p3.setRating(5);

        pm1.addPhoto(p1);

        pm2.addPhoto(p1);
        pm2.addPhoto(p2);
        pm2.addPhoto(p3);
        
        target1 = new ArrayList<Photo>();
        target2 = new ArrayList<Photo>();
        target3 = new ArrayList<Photo>();
    }


    @Test
    void testConstructor() {
        assertEquals(0, empty.getPhotos().size());
        assertEquals(0, (new PhotoManager()).getPhotos().size());
    }

    @Test
    void testAddPhoto() {
        ArrayList<Photo> result = new ArrayList<Photo>();
        
        // add a photo that already exists
        assertFalse(pm1.addPhoto(p1));

        assertTrue(pm1.addPhoto(p3));
        assertTrue(pm1.getPhotos().contains(p3));

        assertTrue(pm1.addPhoto(p2));
        assertTrue(pm1.getPhotos().contains(p2));

        result.add(p1);
        result.add(p3);
        result.add(p2);

        assertTrue(result.equals(pm1.getPhotos()));
    }

    @Test
    void testFilterRatedPhotos() {
        assertNull(empty.filterRatedPhotos(0));
        assertNull(empty.filterRatedPhotos(1));

        assertTrue(target1.equals(pm1.filterRatedPhotos(1)));

        target1.add(p1);
        assertTrue(target1.equals(pm1.filterRatedPhotos(0)));

        target2.add(p1);
        target2.add(p2);
        assertTrue(target2.equals(pm2.filterRatedPhotos(0)));

        target3.add(p3);
        assertTrue(target3.equals(pm2.filterRatedPhotos(5)));
    }

    @Test
    void testGetPhotos() {
        assertTrue(target1.equals(empty.getPhotos()));

        target2.add(p1);
        assertTrue(target2.equals(pm1.getPhotos()));

        target3.add(p1);
        target3.add(p2);
        target3.add(p3);
        assertTrue(target3.equals(pm2.getPhotos()));
    }

}
