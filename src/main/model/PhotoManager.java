package model;

import java.util.ArrayList;

// Photomanager managens Photos, giving ratings, 
// tagging each photo with specific information etc.
public class PhotoManager {
    private ArrayList<Photo> lop;

    // EFFECTS: creates an instance of the PhotoManager class, and initalizes 
    //          an empty array list for the list of the photos.
    public PhotoManager() {
        lop = new ArrayList<Photo>();
    }

    // MODIFIES: this 
    // EFFECTS: add Photo to the PhotoManager if it doesn't already exists, 
    //          returns true if photo was added, false otherwise.
    public boolean addPhoto(Photo p) {
        boolean result = false;
        if (!lop.contains(p)) {
            this.lop.add(p);
            result = true;
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
    }
    
}
