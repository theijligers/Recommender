
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerSimilarRatings {
    
    public void printAverageRatings() {
        FourthRatings fourthRate = new FourthRatings();
        MovieDatabase movies = new MovieDatabase();
        RaterDatabase raters = new RaterDatabase();
        movies.initialize("ratedmovies_short.csv");
        raters.initialize("ratings_short.csv");
        System.out.println("Number of movies is "+movies.size());
        System.out.println("Number of raters is "+raters.size());
        int minimalNumRatings = 1;
        ArrayList<Rating> averageRatings = fourthRate.getAverageRatings(minimalNumRatings);
        System.out.println("Found "+averageRatings.size()+" movies.");
        System.out.println("Average rating for movies with a minimum amount of "+minimalNumRatings+" ratings is: ");
        for (Rating r : averageRatings) {
            System.out.println("Average rating is: "+r.getValue()+"\t"+"Movie title is: "+movies.getTitle(r.getItem()));
        }    
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
        FourthRatings fourthRate = new FourthRatings();
        MovieDatabase movies = new MovieDatabase();
        RaterDatabase raters = new RaterDatabase();
        movies.initialize("ratedmovies_short.csv");
        raters.initialize("ratings_short.csv");
        System.out.println("Number of movies is "+movies.size());
        System.out.println("Number of raters is "+raters.size());
        int minimalNumRatings = 1;
        int year = 1980;
        String genre = "Romance";
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new YearAfterFilter(year));
        allFilters.addFilter(new GenreFilter(genre));
        ArrayList<Rating> averageRatings = fourthRate.getAverageRatingsByFilter(minimalNumRatings, allFilters);
        System.out.println("Found "+averageRatings.size()+" movies.");
        System.out.println("Average rating for movies with a minimum amount of "+minimalNumRatings+" ratings is: ");
        for (Rating r : averageRatings) {
            System.out.println("Average rating is: "+r.getValue()+"\t"+"Year is: "+movies.getYear(r.getItem())+"\t"+
                "Movie title is: "+movies.getTitle(r.getItem())+"\n"+"Genres are: "+movies.getGenres(r.getItem()));
        } 
    }
    
    public void printSimilarRatings() {
        FourthRatings fourthRate = new FourthRatings();
        MovieDatabase movies = new MovieDatabase();
        RaterDatabase raters = new RaterDatabase();
        movies.initialize("ratedmoviesfull.csv");
        raters.initialize("ratings.csv");
        // movies.initialize("ratedmovies_short.csv");
        // raters.initialize("ratings_short.csv");
        System.out.println("Number of movies is "+movies.size());
        System.out.println("Number of raters is "+raters.size());
        String rater_id = "71";
        int minimalNumRatings = 5;
        int topSimilarRaters = 20;
        ArrayList<Rating> similarRatings = fourthRate.getSimilarRatings(rater_id, topSimilarRaters, minimalNumRatings);
        System.out.println("Found "+similarRatings.size()+" movies.");
        System.out.println("Minimum amount of ratings is: "+minimalNumRatings+"\n"+"Top similar raters is: "+topSimilarRaters);
        for (Rating r : similarRatings) {
            System.out.println(r.getValue()+"\t"+movies.getTitle(r.getItem()));
        } 
    }
    
    public void printSimilarRatingsByGenre() {
        FourthRatings fourthRate = new FourthRatings();
        MovieDatabase movies = new MovieDatabase();
        RaterDatabase raters = new RaterDatabase();
        movies.initialize("ratedmoviesfull.csv");
        raters.initialize("ratings.csv");
        // movies.initialize("ratedmovies_short.csv");
        // raters.initialize("ratings_short.csv");
        System.out.println("Number of movies is "+movies.size());
        System.out.println("Number of raters is "+raters.size());
        String genre = "Mystery";
        GenreFilter genreFilter = new GenreFilter(genre);
        String rater_id = "964";
        int minimalNumRatings = 5;
        int topSimilarRaters = 20;
        ArrayList<Rating> similarRatings = fourthRate.getSimilarRatingsByFilter(rater_id, topSimilarRaters, minimalNumRatings, genreFilter);
        System.out.println("Found "+similarRatings.size()+" movies.");
        System.out.println("Minimum amount of ratings is: "+minimalNumRatings+"\n"+"Top similar raters is: "+topSimilarRaters);
        for (Rating r : similarRatings) {
            System.out.println(r.getValue()+"\t"+movies.getTitle(r.getItem()));
        } 
    }
    
    public void printSimilarRatingsByDirector() {
        FourthRatings fourthRate = new FourthRatings();
        MovieDatabase movies = new MovieDatabase();
        RaterDatabase raters = new RaterDatabase();
        movies.initialize("ratedmoviesfull.csv");
        raters.initialize("ratings.csv");
        // movies.initialize("ratedmovies_short.csv");
        // raters.initialize("ratings_short.csv");
        System.out.println("Number of movies is "+movies.size());
        System.out.println("Number of raters is "+raters.size());
        String directors = "Clint Eastwood,J.J. Abrams,Alfred Hitchcok,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";
        DirectorsFilter directorsFilter = new DirectorsFilter(directors);
        String rater_id = "120";
        int minimalNumRatings = 2;
        int topSimilarRaters = 10;
        ArrayList<Rating> similarRatings = fourthRate.getSimilarRatingsByFilter(rater_id, topSimilarRaters, minimalNumRatings, directorsFilter);
        System.out.println("Found "+similarRatings.size()+" movies.");
        System.out.println("Minimum amount of ratings is: "+minimalNumRatings+"\n"+"Top similar raters is: "+topSimilarRaters);
        for (Rating r : similarRatings) {
            System.out.println(r.getValue()+"\t"+movies.getTitle(r.getItem()));
        } 
    }
    
    public void printSimilarRatingsByGenreAndMinutes() {
        FourthRatings fourthRate = new FourthRatings();
        MovieDatabase movies = new MovieDatabase();
        RaterDatabase raters = new RaterDatabase();
        movies.initialize("ratedmoviesfull.csv");
        raters.initialize("ratings.csv");
        // movies.initialize("ratedmovies_short.csv");
        // raters.initialize("ratings_short.csv");
        System.out.println("Number of movies is "+movies.size());
        System.out.println("Number of raters is "+raters.size());
        String genre = "Drama";
        int minMinutes = 80;
        int maxMinutes = 160;
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new MinutesFilter(minMinutes, maxMinutes));
        allFilters.addFilter(new GenreFilter(genre));
        String rater_id = "168";
        int minimalNumRatings = 3;
        int topSimilarRaters = 10;
        ArrayList<Rating> similarRatings = fourthRate.getSimilarRatingsByFilter(rater_id, topSimilarRaters, minimalNumRatings, allFilters);
        System.out.println("Found "+similarRatings.size()+" movies.");
        System.out.println("Minimum amount of ratings is: "+minimalNumRatings+"\n"+"Top similar raters is: "+topSimilarRaters);
        for (Rating r : similarRatings) {
            System.out.println(r.getValue()+"\t"+movies.getTitle(r.getItem()));
        } 
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes() {
        FourthRatings fourthRate = new FourthRatings();
        MovieDatabase movies = new MovieDatabase();
        RaterDatabase raters = new RaterDatabase();
        movies.initialize("ratedmoviesfull.csv");
        raters.initialize("ratings.csv");
        // movies.initialize("ratedmovies_short.csv");
        // raters.initialize("ratings_short.csv");
        System.out.println("Number of movies is "+movies.size());
        System.out.println("Number of raters is "+raters.size());
        int year = 1975;
        int minMinutes = 70;
        int maxMinutes = 200;
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new MinutesFilter(minMinutes, maxMinutes));
        allFilters.addFilter(new YearAfterFilter(year));
        String rater_id = "314";
        int minimalNumRatings = 5;
        int topSimilarRaters = 10;
        ArrayList<Rating> similarRatings = fourthRate.getSimilarRatingsByFilter(rater_id, topSimilarRaters, minimalNumRatings, allFilters);
        System.out.println("Found "+similarRatings.size()+" movies.");
        System.out.println("Minimum amount of ratings is: "+minimalNumRatings+"\n"+"Top similar raters is: "+topSimilarRaters);
        for (Rating r : similarRatings) {
            System.out.println(r.getValue()+"\t"+movies.getTitle(r.getItem()));
        } 
    }
    
}
