
/**
 * Write a description of EfficientRater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class EfficientRater implements Rater{
    private String myID;
    private HashMap<String, Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String, Rating>();
    }

    //A method addRating that has two parameters, a String named item(movie_id) and a double named rating.
    //A new Rating is created and added to myRatings.
    public void addRating(String item, double rating) {
        myRatings.put(item, new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        if (myRatings.containsKey(item)){
            return true;
        }
        
        return false;
    }

    //A method getID with no parameters to get the ID of the rater.
    public String getID() {
        return myID;
    }

    //A method getRating that has one parameter item(movie_id).
    //This method returns the double rating of this item if it is in myRatings.
    //Otherwise this method returns -1.
    public double getRating(String item) {
        //for(int k=0; k < myRatings.size(); k++){
            if (myRatings.containsKey(item)){
                return myRatings.get(item).getValue();
            }
        //}
        
        return -1;
    }

    //A method numRatings that returns the number of ratings this rater has.
    public int numRatings() {
        return myRatings.size();
    }

    //A method getItemsRated that has no parameters.
    //This method returns an ArrayList of Strings representing a list of all the items that have been rated.
    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(String s : myRatings.keySet()){
            list.add(s);
        }
        
        return list;
    }

}
