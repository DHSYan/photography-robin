package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Photo;
import model.PhotoManager;

// import org.json.*;

public class JsonReader {
    private String path;
    private String jsonData;

    // EFFECTS: Constructs a JSON Reader 
    public JsonReader(String path) {
    }

    
    // EFFECTS: reads source file as string and returns it as String
    private String readFile(String source) throws IOException {
    }


    // MODIFIES: this
    // EFFECTS: stores the JSON content as String 
    public void open() throws IOException {
    }

    public PhotoManager parseJson(String jsonContent) {
    }


}
