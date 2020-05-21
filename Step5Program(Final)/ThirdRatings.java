
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
        FirstRatings firstRate = new FirstRatings();
        myRaters = firstRate.loadRaters(ratingsfile);
    }
    
    public int getRaterSize() {
        ArrayList<String> raters = new ArrayList<String>();
        for (Rater id : myRaters) {
            String ID = id.getID();
            if (!raters.contains(ID)) {
                raters.add(ID);
            }
        }
        return raters.size();
    }
    
    public double getAverageByID(String id, int minimalRaters) {
        ArrayList<String> raters = new ArrayList<String>();
        int movieCount = 0;
        double ratingTotal = 0.0;
        for (Rater rater : myRaters) {
            ArrayList<String> movie_id = rater.getItemsRated();
            if (id.equals(movie_id.get(0))) {
                movieCount++;
            }
        }
        if (movieCount>=minimalRaters) {
            for (Rater rater : myRaters) {
                ArrayList<String> movie_id = rater.getItemsRated();
                if(id.equals(movie_id.get(0))) {
                    ratingTotal = ratingTotal + rater.getRating(movie_id.get(0));
                }
            }
        }
        double average = ratingTotal/movieCount;
        return average;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> averageRatings = new ArrayList<Rating>();
        ArrayList<String> movies = new ArrayList<String>();
        ArrayList<String> movieDatabase = MovieDatabase.filterBy(new TrueFilter());
        for (Rater rater : myRaters) {
            System.out.println("Current rater is "+rater);
            String movie_id = rater.getItemsRated().get(0);
            if (!movies.contains(movie_id) && movieDatabase.contains(movie_id)) {
                movies.add(movie_id);
                double averageByID = getAverageByID(movie_id, minimalRaters);
                if (averageByID != 0.0) {
                    Rating ratings = new Rating(movie_id, averageByID);
                    averageRatings.add(ratings);
                }
            }
        }
        return averageRatings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter( int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> averageRatings = new ArrayList<Rating>();
        ArrayList<String> movies = new ArrayList<String>();
        ArrayList<String> movieDatabase = MovieDatabase.filterBy(filterCriteria);
        for (Rater rater : myRaters) {
            String movie_id = rater.getItemsRated().get(0);
            if (!movies.contains(movie_id) && movieDatabase.contains(movie_id)) {
                movies.add(movie_id);
                double averageByID = getAverageByID(movie_id, minimalRaters);
                if (averageByID != 0.0) {
                    Rating ratings = new Rating(movie_id, averageByID);
                    averageRatings.add(ratings);
                }
            }
        }
        return averageRatings;
    }
    
}
