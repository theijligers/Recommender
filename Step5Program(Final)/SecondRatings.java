
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings firstRate = new FirstRatings();
        myMovies = firstRate.loadMovies(moviefile);
        myRaters = firstRate.loadRaters(ratingsfile);
    }
    
    public int getMovieSize() {
        return myMovies.size();
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
        for (Rater rater : myRaters) {
            String movie_id = rater.getItemsRated().get(0);
            if (!movies.contains(movie_id)) {
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
    
    public String getTitle(String id) {
        for (Movie movie : myMovies) {
            if (movie.getID().equals(id)) {
                return movie.getTitle();
            }
        }
        return "ID was not found.";
    }
    
    public String getID(String title) {
        for (Movie movie : myMovies) {
            if (movie.getTitle().equals(title)) {
                return movie.getID();
            }
        }
        return "NO SUCH TITLE.";
    }
    
}
