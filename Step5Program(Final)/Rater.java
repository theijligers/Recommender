
/**
 * Write a description of Rater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public interface Rater {
    public void addRating(String item, double rating);
    public boolean hasRating(String item);
    //A method getID with no parameters to get the ID of the rater.
    public String getID();
    //A method getRating that has one parameter item.
    //This method returns the double rating of this item if it is in myRatings.
    //Otherwise this method returns -1.
    public double getRating(String item);
    //A method numRatings that returns the number of ratings this rater has.
    public int numRatings();
    //A method getItemsRated that has no parameters.
    //This method returns an ArrayList of Strings representing a list of all the items that have been rated.
    public ArrayList<String> getItemsRated();
}
