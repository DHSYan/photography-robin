package persistance;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.net.URL;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import model.Photo;
import model.PhotoManager;
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
        p1 = new Photo("./photos/IMG_1672.JPG");
        p2 = new Photo("./photos/IMG_1778.JPG");
        p3 = new Photo("./photos/IMG_2221.JPG");
        pm1 = new PhotoManager();
        pm2 = new PhotoManager();


        pm1.addPhoto(p1);

        pm2.addPhoto(p1);
        pm2.addPhoto(p2);
        pm2.addPhoto(p3);
    }

    @Test
    void testInvalidFile() { 
        try {
            writer = new JsonWriter("./data/snhaoelrcuh.archlrch");
            writer.write("test");
            fail("IOException should've been thrown");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWrite() { 
    }

    @Test
    void testClose() {
    }

}
// Citation: 
// Much of the outline of this JsonWriter class is taken from the CPSC210/JsonSerializationDemo repo