package model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPhoto {
    private Photo p1;
    private Photo p2;
    private Photo p3;
    private Photo p4;

    @BeforeEach 
    void setup() {
        p1 = new Photo("/home/tzen/cpsc210/ProjectStarter/photos/IMG_1672.JPG");
        p2 = new Photo("/home/tzen/cpsc210/ProjectStarter/photos/IMG_1778.JPG");
        p3 = new Photo("/home/tzen/cpsc210/ProjectStarter/photos/IMG_2221.JPG");
        p4 = new Photo("");
    }

    @Test
    public void testConstructor() {
        assertEquals("/home/tzen/cpsc210/ProjectStarter/photos/IMG_1672.JPG", p1.getPath());
        assertFalse(p1.getToDelete());
        assertEquals(-1, p1.getRating());
    }


    @Test
    public void testSetRating() {
        assertEquals(-1, p2.getRating());
        p2.setRating(3);
        assertEquals(3, p2.getRating());

        p2.setRating(0);
        assertEquals(0, p2.getRating());
    }

    @Test
    public void testRemoveRating() {
        p2.setRating(5);
        assertEquals(5, p2.getRating());

        p2.removeRating();
        assertEquals(-1, p2.getRating());

        assertEquals(-1, p3.getRating());
        p3.removeRating();
        assertEquals(-1, p3.getRating());
    }

    @Test
    public void testSetToDelete() {
        assertFalse(p1.getToDelete());
        assertFalse(p2.getToDelete());
        assertFalse(p3.getToDelete());

        p1.setToDelete();

        assertTrue(p1.getToDelete());
        assertFalse(p2.getToDelete());
        assertFalse(p3.getToDelete());

        p1.setToDelete();

        assertTrue(p1.getToDelete());
        assertFalse(p2.getToDelete());
        assertFalse(p3.getToDelete());

        p2.setToDelete();
        
        assertTrue(p1.getToDelete());
        assertTrue(p2.getToDelete());
        assertFalse(p3.getToDelete());
    }

    @Test
    public void testSetPath() {
        assertEquals("", p4.getPath());
        p4.setPath("/home/tzen/cpsc210/ProjectStarter/photos/IMG_2221.JPG");
        assertEquals("/home/tzen/cpsc210/ProjectStarter/photos/IMG_2221.JPG", p4.getPath());

        assertEquals("/home/tzen/cpsc210/ProjectStarter/photos/IMG_1672.JPG", p1.getPath());
        p1.setPath("/home/tzen/cpsc210/ProjectStarter/photos/IMG_1672.JPG");
        assertEquals("/home/tzen/cpsc210/ProjectStarter/photos/IMG_1672.JPG", p1.getPath());
        p1.setPath("");
        assertEquals("", p1.getPath());
        p1.setPath("");
        assertEquals("", p1.getPath());
        assertEquals("/home/tzen/cpsc210/ProjectStarter/photos/IMG_1778.JPG", p2.getPath());
    }

    @Test
    public void isToDelete() {
        assertFalse(p1.getToDelete());
        assertFalse(p2.getToDelete());
        assertFalse(p3.getToDelete());

        p1.setToDelete();

        assertTrue(p1.getToDelete());
        assertFalse(p2.getToDelete());
        assertFalse(p3.getToDelete());
    }

    @Test
    public void testGetRating() {
        assertEquals(-1, p1.getRating());
        assertEquals(-1, p2.getRating());
        assertEquals(-1, p3.getRating());

        p1.setRating(0);

        assertEquals(0, p1.getRating());
        assertEquals(-1, p2.getRating());
        assertEquals(-1, p3.getRating());

        p1.setRating(5);
        
        assertEquals(5, p1.getRating());
        assertEquals(-1, p2.getRating());
        assertEquals(-1, p3.getRating());

        p3.setRating(3);

        assertEquals(5, p1.getRating());
        assertEquals(-1, p2.getRating());
        assertEquals(3, p3.getRating());
    }

    @Test
    public void testGetPath() {
        assertEquals("/home/tzen/cpsc210/ProjectStarter/photos/IMG_1672.JPG", p1.getPath());
        assertEquals("/home/tzen/cpsc210/ProjectStarter/photos/IMG_1778.JPG", p2.getPath());
        assertEquals("/home/tzen/cpsc210/ProjectStarter/photos/IMG_2221.JPG", p3.getPath());

        p1.setPath("hello");
        
        assertEquals("hello", p1.getPath());
        assertEquals("/home/tzen/cpsc210/ProjectStarter/photos/IMG_1778.JPG", p2.getPath());
        assertEquals("/home/tzen/cpsc210/ProjectStarter/photos/IMG_2221.JPG", p3.getPath());

        p1.setPath("");

        assertEquals("", p1.getPath());
        assertEquals("/home/tzen/cpsc210/ProjectStarter/photos/IMG_1778.JPG", p2.getPath());
        assertEquals("/home/tzen/cpsc210/ProjectStarter/photos/IMG_2221.JPG", p3.getPath());

        p3.setPath("bruH");

        assertEquals("", p1.getPath());
        assertEquals("/home/tzen/cpsc210/ProjectStarter/photos/IMG_1778.JPG", p2.getPath());
        assertEquals("bruH", p3.getPath());
    }

    

}
