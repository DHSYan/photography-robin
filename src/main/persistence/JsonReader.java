package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Photo;
import model.PhotoManager;

import org.json.*;

public class JsonReader {
    private String path;
    private String jsonData;

    // EFFECTS: Constructs a JSON Reader 
    public JsonReader(String path) {
        this.path = path;
    }

    
    // EFFECTS: reads source file as string and returns it as String
    private String readFile() throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    public PhotoManager parse(String data) {
        PhotoManager res = new PhotoManager();

        return res;
    }

    public PhotoManager read() throws IOException {
    }

}
