package persistance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import model.PhotoManager;
import persistence.JsonReader;
import model.Photo;


public class TestJsonReader {
    private Photo p1;
    private Photo p2;
    private Photo p3;
    private Photo p4;

    @BeforeEach 
    void setup() {
        // Reflect test1.json and test2.json
        p1 = new Photo("./photos/IMG_1672.JPG");
        p1.setRating(0);
        p1.setToDelete();

        p2 = new Photo("./photos/IMG_1778.JPG");
        p2.setRating(3);

        p3 = new Photo("./photos/IMG_2221.JPG");
        p3.setRating(5);;
        p3.setToDelete();

        p4 = new Photo("");
    }

    void testJsonReaderInvalidFile() {
        JsonReader reader = new JsonReader("./data/invalid.json");
        try {
            PhotoManager pm = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        } catch (Exception e) {
            fail();
        }
    }


    @Test
    // One photo entry
    void testJsonReaderSimple() {
        JsonReader reader = new JsonReader("./data/test1.json");
        ArrayList<Photo> target = new ArrayList<Photo>();
        target.add(p1);

        try {
            PhotoManager pm = reader.read();
            assertEquals(pm.getPhotos().size(), 1);
            assertTrue(pm.getPhotos().equals(target));
        } catch (Exception e) {
            fail();
        }
    }


    // Three Photo entry
    void testJsonReaderHard() {

    }



    
}
