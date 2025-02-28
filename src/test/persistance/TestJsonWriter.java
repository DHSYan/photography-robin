package persistance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Photo;
import model.PhotoManager;
import persistence.JsonReader;
import persistence.JsonWriter;

public class TestJsonWriter {
    private JsonWriter writer;
    private PhotoManager pm1;
    private PhotoManager pm2;
    private Photo p1;
    private Photo p2;
    private Photo p3;

    @BeforeEach
    void setup() {
        pm1 = new PhotoManager();
        pm2 = new PhotoManager();

        
        p1 = new Photo("./photos/IMG_1672.JPG");
        p1.setRating(0);
        p1.setToDelete();

        p2 = new Photo("./photos/IMG_1778.JPG");
        p2.setRating(3);

        p3 = new Photo("./photos/IMG_2221.JPG");
        p3.setRating(5);;
        p3.setToDelete();


        pm1.addPhoto(p1);

        pm2.addPhoto(p1);
        pm2.addPhoto(p2);
        pm2.addPhoto(p3);
    }

    @Test
    // illegal file name
    public void testInvalidFile() { 
        try {
            writer = new JsonWriter("./data/snha:oe\0lr:.cuh.archlrch");
            writer.write(pm1);
            fail("IOException should've been thrown");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriteEmpty() {
        try {
            writer = new JsonWriter("./data/write0.json");
            writer.write(new PhotoManager());

            JsonReader reader = new JsonReader("./data/write0.json");
            PhotoManager check = reader.read();
            assertEquals(check.getPhotos().size(), 0);
        } catch (Exception e) {
            fail("No exceptions should be thown");
        }
    }

    @Test
    public void testWriteSimple() { 
        try {
            writer = new JsonWriter("./data/write1.json");

            writer.write(pm1);

            JsonReader reader = new JsonReader("./data/write1.json");

            PhotoManager check = reader.read();
            
            Photo checkP = check.findPhoto(p1.getPath());
            assertEquals(check.getPhotos().size(), 1);
            assertNull(check.findPhoto("nothing"));
            assertNotNull(checkP);
            assertEquals(checkP.getPath(), p1.getPath());
            assertEquals(checkP.getRating(), p1.getRating());
            assertEquals(checkP.getToDelete(), p1.getToDelete());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testWriteComplex() {
        try {
            writer = new JsonWriter("./data/write2.json");

            writer.write(pm2);

            JsonReader reader = new JsonReader("./data/write2.json");

            PhotoManager check = reader.read();
            
            assertEquals(check.getPhotos().size(), 3);

            Photo cp1 = check.findPhoto(p1.getPath());
            assertEquals(cp1.getPath(), p1.getPath());
            assertEquals(cp1.getRating(), p1.getRating());
            assertEquals(cp1.getToDelete(), p1.getToDelete());
            
            Photo cp2 = check.findPhoto(p2.getPath());
            assertEquals(cp2.getPath(), p2.getPath());
            assertEquals(cp2.getRating(), p2.getRating());
            assertEquals(cp2.getToDelete(), p2.getToDelete());
            
            Photo cp3 = check.findPhoto(p3.getPath());
            assertEquals(cp3.getPath(), p3.getPath());
            assertEquals(cp3.getRating(), p3.getRating());
            assertEquals(cp3.getToDelete(), p3.getToDelete());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}
// Citation: 
// Much of the outline of this JsonWriter class is taken from the CPSC210/JsonSerializationDemo repo