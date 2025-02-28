package persistance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.PhotoManager;
import persistence.JsonReader;
import model.Photo;


public class TestJsonReader {
    private Photo p1;
    private Photo p2;
    private Photo p3;

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

        assertNotNull(p1);
        assertNotNull(p2);
        assertNotNull(p3);
    }


    @Test
    public void testJsonReaderInvalidFile() {
        JsonReader reader = new JsonReader("./data/invalid.json");
        try {
            reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        } catch (Exception e) {
            fail("An exception of such type shouldn't be thrown");
        }
    }
    
    @Test
    public void testEmptyJsonFile() {
        JsonReader reader = new JsonReader("./data/empty.json");
        try {
            PhotoManager pm = reader.read();
            assertEquals(pm.getPhotos().size(), 0);
        } catch (Exception e) {
            fail("Exceptions shouldn't been thrown");
        }
    }


    @Test
    // One photo entry
    public void testJsonReaderSimple() {
        JsonReader reader = new JsonReader("./data/test1.json");
        ArrayList<Photo> target = new ArrayList<Photo>();
        target.add(p1);

        Photo got;

        try {
            PhotoManager pm = reader.read();
            got = pm.findPhoto(p1.getPath());
            assertEquals(pm.getPhotos().size(), 1);
            assertNull(pm.findPhoto("nothing"));
            assertNotNull(got);
            assertEquals(got.getPath(), p1.getPath());
            assertEquals(got.getRating(), p1.getRating());
            assertEquals(got.getToDelete(), p1.getToDelete());
        } catch (Exception e) {
            fail("An exception shouldn't be thrown");
        }
    }


    @Test
    // Three Photo entry
    public void testJsonReaderHard() {
        JsonReader reader = new  JsonReader("./data/test2.json");
        try {
            PhotoManager pm = reader.read();
            assertEquals(pm.getPhotos().size(), 3);
            Photo got1 = pm.findPhoto(p1.getPath());
            Photo got2 = pm.findPhoto(p2.getPath());
            Photo got3 = pm.findPhoto(p3.getPath());
            assertNotNull(got1);
            assertEquals(got1.getPath(), p1.getPath());
            assertEquals(got1.getRating(), p1.getRating());
            assertEquals(got1.getToDelete(), p1.getToDelete());

            assertNotNull(got2);
            assertEquals(got2.getPath(), p2.getPath());
            assertEquals(got2.getRating(), p2.getRating());
            assertEquals(got2.getToDelete(), p2.getToDelete());

            assertNotNull(got3);
            assertEquals(got3.getPath(), p3.getPath());
            assertEquals(got3.getRating(), p3.getRating());
            assertEquals(got3.getToDelete(), p3.getToDelete());

        } catch (Exception e) {
            fail("An exception shouldn't be thrown");
        }

    }
    
}
