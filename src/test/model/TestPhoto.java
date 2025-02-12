package model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPhoto {
    private Photo p1;
    private Photo p2;
    private Photo p3;

    @BeforeEach 
    void setup() {
        p1 = new Photo("/home/tzen/cpsc210/ProjectStarter/photos/IMG_1672.JPG");
        p2 = new Photo("/home/tzen/cpsc210/ProjectStarter/photos/IMG_1778.JPG");
        p3 = new Photo("/home/tzen/cpsc210/ProjectStarter/photos/IMG_2221.JPG");
    }

    @Test
    public void testConstructor() {
        assertEquals("/home/tzen/cpsc210/ProjectStarter/photos/IMG_1672.JPG", p1.getPath());
        assertFalse(p1.isToDelete());
        assertEquals(-1, p1.getRating());
    }



}
