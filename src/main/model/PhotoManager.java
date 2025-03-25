package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;


// Photomanager managens Photos, giving ratings, 
// tagging each photo with specific information etc.
public class PhotoManager {
    private ArrayList<Photo> lop;

    // EFFECTS: creates an instance of the PhotoManager class, and initalizes 
    //          an empty array list for the list of the photos.
    public PhotoManager() {
        lop = new ArrayList<Photo>();
    }

    // MODIFIES: this, EventLog
    // EFFECTS: add Photo to the PhotoManager if it doesn't already exists, 
    //          returns true if photo was added, false otherwise.
    public boolean addPhoto(Photo p) {
        boolean result = false;
        if (!lop.contains(p)) {
            this.lop.add(p);
            result = true;
            
            EventLog.getInstance().logEvent(new Event("A new photo with path: " + p.getPath() + " was added"));
        }

        return result;
    }

    // REQUIRES: 0 <= rating <= 5
    // EFFECTS: returns a list of photo that matches the requested rating, 
    //          if the list of photo is empty, null is returned
    public ArrayList<Photo> filterRatedPhotos(int rating) {
        ArrayList<Photo> result = new ArrayList<Photo>();

        if (lop.size() == 0) {
            return null;
        }

        for (Photo p : lop) {
            if (p.getRating() == rating) {
                result.add(p);
            }
        }

        return result;
    }

    // EFFECTS: returns the photo whose have the given path, if no photo exists with this path, 
    //          null is returned
    public Photo findPhoto(String path) { 
        Photo result = null;

        for (Photo p : lop) {
            if (path.equals(p.getPath())) {
                result = p;
            }
        }

        return result;
    }

    // EFFECTS: returns a list of all the photos in the PhotoManager
    public ArrayList<Photo> getPhotos() {
        return lop;
    }

    // EFFECTS: returns a list of Photo's string that is tagged with being deleted
    public ArrayList<String> filterToBeDeleted() {
        ArrayList<String> result = new ArrayList<String>();

        for (Photo p : lop) {
            if (p.getToDelete() == true) {
                result.add(p.getPath());
            }
        }

        // EventLog.getInstance().logEvent(new Event("PhotoManager returned all the photos that is to be deleted"));

        return result;
    }

    public void override(PhotoManager pm) {
        lop = pm.getPhotos();
    }
    
    // The below was referenced from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/model/WorkRoom.javaj
    // EFFECTS: returns an JSONObject of PhotoManager
    public JSONObject toJson() {
        JSONObject res = new JSONObject();
        res.put("lop", lopToJson());

        // EventLog.getInstance().logEvent(new Event("Serialized PhotoManager"));
        
        return res;
    }

    // EFFECTS: returns an JSONArray for the list of photos in PhotoManager
    private JSONArray lopToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Photo p : lop) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

    

}
