package persistence;

import model.PhotoManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.json.JSONObject;

public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String path;

    // EFFECTS: Constructs a JsonWriter class, opens a writer with path
    public JsonWriter(String path) {
        this.path = path;
    }

    // MODIFIES: this
    private void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(path));
    }

    // MODIFIES: this
    // EFFECTS: Writes to Json
    public void write(PhotoManager pm) throws FileNotFoundException {
        open();
        JSONObject json = pm.toJson();
        writer.print(json.toString(TAB));
        writer.close();
    }
}
// Citation: 
// Much of the outline of this JsonWriter class is taken from the CPSC210/JsonSerializationDemo repo