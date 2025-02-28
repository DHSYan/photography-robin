package persistence;

import model.PhotoManager;

import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONObject;

public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private PhotoManager pm;
    private String filePath;

    // EFFECTS: Constructs a JsonWriter class, opens a writer with path
    public JsonWriter(String path) {
        return;
    }

    // MODIFIES: this
    // EFFECTS: Writes to Json
    public void write(PhotoManager pm) throws IOException {
        return;
    }

    // MODIFIES: this 
    // EFFECTS: closes the writer
    public void close() {
        return;
    }
}

// Citation: 
// Much of the outline of this JsonWriter class is taken from the CPSC210/JsonSerializationDemo repo
