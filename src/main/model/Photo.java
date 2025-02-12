
package model;

public class Photo {
    // Path to the Photo on System
    private String path; 

    // Rating from 0 to 5, -1 means no rating given
    private int rating; 
    private static final int DEFAULT_RATING = -1;

    private String path; 
    private int rating; // Rating from 0 to 5, -1 means no rating given
    private boolean toDelete;

    public Photo(String path) {
        this.path = path;
        this.rating = DEFAULT_RATING;
        this.toDelete = false;
    }

    // REQUIRE: 0 <= rating <= 5
    public void setRating(int rating) {
        this.rating = rating;
    }

    public void removeRating() {
        this.rating = -1;
    }

    public void setToDelete() {
        this.toDelete = true;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isToDelete() {
        return this.toDelete;
    }

    public int getRating() {
        return this.rating;
    }

    public String getPath() {
        return path;
    }


}
