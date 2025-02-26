package persistence;

import model.PhotoManager;

import java.io.PrintWriter;

import org.json.JSONObject;

public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private PhotoManager pm;

    // EFFECTS: Constructs a JsonWriter class, opens a writer with path
    void JsonWriter(String path) {
        // Stub
    }

    // MODIFIES: this
    // EFFECTS: Writes to Json
    void write() {
        return;
    }

    // MODIFIES: this 
    // EFFECTS: closes the writer
    void close() {
        return;
    }
}

// Citation: 
// Much of the outline of this JsonWriter class is taken from the CPSC210/JsonSerializationDemo repo
