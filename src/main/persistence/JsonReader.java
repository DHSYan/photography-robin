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
        this.path = path;
    }

    
    // EFFECTS: reads source file as string and returns it as String
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }


    // MODIFIES: this
    // EFFECTS: stores the JSON content as String 
    public void open() throws IOException {
        jsonData = readFile(path);
    }

    public PhotoManager parseJson(String jsonContent) {
        PhotoManager res = new PhotoManager();

        return res;
    }


}
