
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerAverage {
    
    public void printAverageRatings() {
        SecondRatings secondRate = new SecondRatings("ratedmovies_short.csv", "ratings_short.csv");
        System.out.println("Number of movies is "+secondRate.getMovieSize());
        System.out.println("Number of raters is "+secondRate.getRaterSize());
        int minimalNumRatings = 3;
        System.out.println("Average rating for movies with a minimum amount of "+minimalNumRatings+" ratings is: ");
        ArrayList<Rating> averageRatings = secondRate.getAverageRatings(minimalNumRatings);
        for (Rating r : averageRatings) {
            System.out.println("Average rating is: "+r.getValue()+"\t"+"Movie title is: "+secondRate.getTitle(r.getItem()));
        }
    }
    
    public void getAverageRatingOneMovie() {
        SecondRatings secondRate = new SecondRatings("ratedmovies_short.csv", "ratings_short.csv");
        int minimalNumRatings = 1;
        String movieTitle = "The Godfather";
        System.out.println("Average rating for movies with a minimum amount of "+minimalNumRatings+" ratings is: ");
        double averageRating = secondRate.getAverageByID(secondRate.getID(movieTitle), minimalNumRatings);
        System.out.println("Average rating for "+"\""+movieTitle+"\""+" is: "+averageRating);
        
    }
}
