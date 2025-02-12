
package model;

public class Photo {
    private static final int DEFAULT_RATING = -1;

    private String path; 
    private int rating; // Rating from 0 to 5, -1 means no rating given
    private boolean toDelete;

    // EFFECTS: Constructs an instance of the Photo class, with the specified path,
    //          default initial rating, and default deleteing value.
    public Photo(String path) {
        this.path = path;
        this.rating = DEFAULT_RATING;
        this.toDelete = false;
    }

    // REQUIRES: 0 <= rating <= 5
    // MODIFIES: this
    // EFFECTS: sets a new rating of the Photo
    public void setRating(int rating) {
        this.rating = rating;
    }

    // MODIFIES: this
    // EFFECTS: sets the rating to the default inital value of rating
    public void removeRating() {
        this.rating = DEFAULT_RATING;
    }

    // MODIFIES: this
    // EFFECTS: turn on the to be deleted flag.
    public void setToDelete() {
        this.toDelete = true;
    }

    // MODIFIES: this
    // EFFECTS: sets the path of the photo
    public void setPath(String path) {
        this.path = path;
    }

    // EFFECTS: retrieves the to be deleted status of the photo
    public boolean getToDelete() {
        return this.toDelete;
    }

    // EFFECTS: returns the rating of the photo
    public int getRating() {
        return this.rating;
    }

    // EFFECTS: returns the path of the photo
    public String getPath() {
        return path;
    }

}
