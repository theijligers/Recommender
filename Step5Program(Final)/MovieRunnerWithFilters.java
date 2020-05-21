
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerWithFilters {
    
    public void printAverageRatings() {
        ThirdRatings thirdRate = new ThirdRatings("ratings_short.csv");
        MovieDatabase movies = new MovieDatabase();
        movies.initialize("ratedmovies_short.csv");
        System.out.println("Number of movies is "+movies.size());
        System.out.println("Number of raters is "+thirdRate.getRaterSize());
        int minimalNumRatings = 1;
        ArrayList<Rating> averageRatings = thirdRate.getAverageRatings(minimalNumRatings);
        System.out.println("Found "+averageRatings.size()+" movies.");
        System.out.println("Average rating for movies with a minimum amount of "+minimalNumRatings+" ratings is: ");
        for (Rating r : averageRatings) {
            System.out.println("Average rating is: "+r.getValue()+"\t"+"Movie title is: "+movies.getTitle(r.getItem()));
        }    
    }
    
    public void printAverageRatingsByYear() {
        ThirdRatings thirdRate = new ThirdRatings("ratings_short.csv");
        MovieDatabase movies = new MovieDatabase();
        movies.initialize("ratedmovies_short.csv");
        System.out.println("Number of movies is "+movies.size());
        System.out.println("Number of raters is "+thirdRate.getRaterSize());
        int minimalNumRatings = 1;
        int year = 2000;
        ArrayList<Rating> averageRatings = thirdRate.getAverageRatingsByFilter(minimalNumRatings, new YearAfterFilter(year));
        System.out.println("Found "+averageRatings.size()+" movies.");
        System.out.println("Average rating for movies with a minimum amount of "+minimalNumRatings+" ratings is: ");
        for (Rating r : averageRatings) {
            System.out.println("Average rating is: "+r.getValue()+"\t"+"Movie title is: "+movies.getTitle(r.getItem()));
        }    
    }
    
    public void printAverageRatingsByGenre() {
        ThirdRatings thirdRate = new ThirdRatings("ratings_short.csv");
        MovieDatabase movies = new MovieDatabase();
        movies.initialize("ratedmovies_short.csv");
        System.out.println("Number of movies is "+movies.size());
        System.out.println("Number of raters is "+thirdRate.getRaterSize());
        int minimalNumRatings = 1;
        String genre = "Crime";
        ArrayList<Rating> averageRatings = thirdRate.getAverageRatingsByFilter(minimalNumRatings, new GenreFilter(genre));
        System.out.println("Found "+averageRatings.size()+" movies.");
        System.out.println("Average rating for movies with a minimum amount of "+minimalNumRatings+" ratings is: ");
        for (Rating r : averageRatings) {
            System.out.println("Average rating is: "+r.getValue()+"\t"+
            "Movie title is: "+movies.getTitle(r.getItem())+"\n"+"Genres are: "+movies.getGenres(r.getItem()));
        } 
    }
    
    public void printAverageRatingsByMinutes() {
        ThirdRatings thirdRate = new ThirdRatings("ratings_short.csv");
        MovieDatabase movies = new MovieDatabase();
        movies.initialize("ratedmovies_short.csv");
        System.out.println("Number of movies is "+movies.size());
        System.out.println("Number of raters is "+thirdRate.getRaterSize());
        int minimalNumRatings = 1;
        int minMinutes = 110;
        int maxMinutes = 170;
        ArrayList<Rating> averageRatings = thirdRate.getAverageRatingsByFilter(minimalNumRatings, new MinutesFilter(minMinutes, maxMinutes));
        System.out.println("Found "+averageRatings.size()+" movies.");
        System.out.println("Average rating for movies with a minimum amount of "+minimalNumRatings+" ratings is: ");
        for (Rating r : averageRatings) {
            System.out.println("Average rating is: "+r.getValue()+"\t"+"Time is: "+movies.getMinutes(r.getItem())+"\t"+
            "Movie title is: "+movies.getTitle(r.getItem()));
        } 
    }
    
    public void printAverageRatingsByDirectors() {
        ThirdRatings thirdRate = new ThirdRatings("ratings_short.csv");
        MovieDatabase movies = new MovieDatabase();
        movies.initialize("ratedmovies_short.csv");
        System.out.println("Number of movies is "+movies.size());
        System.out.println("Number of raters is "+thirdRate.getRaterSize());
        int minimalNumRatings = 1;
        String directors = "Charles Chaplin,Michael Mann,Spike Jonze";
        ArrayList<Rating> averageRatings = thirdRate.getAverageRatingsByFilter(minimalNumRatings, new DirectorsFilter(directors));
        System.out.println("Found "+averageRatings.size()+" movies.");
        System.out.println("Average rating for movies with a minimum amount of "+minimalNumRatings+" ratings is: ");
        for (Rating r : averageRatings) {
            System.out.println("Average rating is: "+r.getValue()+"\t"+"Movie title is: "+movies.getTitle(r.getItem())+"\t"+
            "Director is: "+movies.getDirector(r.getItem()));
        } 
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
        ThirdRatings thirdRate = new ThirdRatings("ratings_short.csv");
        MovieDatabase movies = new MovieDatabase();
        movies.initialize("ratedmovies_short.csv");
        System.out.println("Number of movies is "+movies.size());
        System.out.println("Number of raters is "+thirdRate.getRaterSize());
        int minimalNumRatings = 1;
        int year = 1980;
        String genre = "Romance";
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new YearAfterFilter(year));
        allFilters.addFilter(new GenreFilter(genre));
        ArrayList<Rating> averageRatings = thirdRate.getAverageRatingsByFilter(minimalNumRatings, allFilters);
        System.out.println("Found "+averageRatings.size()+" movies.");
        System.out.println("Average rating for movies with a minimum amount of "+minimalNumRatings+" ratings is: ");
        for (Rating r : averageRatings) {
            System.out.println("Average rating is: "+r.getValue()+"\t"+"Year is: "+movies.getYear(r.getItem())+"\t"+
                "Movie title is: "+movies.getTitle(r.getItem())+"\n"+"Genres are: "+movies.getGenres(r.getItem()));
        } 
    }
    
    public void printAverageRatingsByDirectorsAndMinutes() {
        ThirdRatings thirdRate = new ThirdRatings("ratings_short.csv");
        MovieDatabase movies = new MovieDatabase();
        movies.initialize("ratedmovies_short.csv");
        System.out.println("Number of movies is "+movies.size());
        System.out.println("Number of raters is "+thirdRate.getRaterSize());
        int minimalNumRatings = 1;
        int minMinutes = 30;
        int maxMinutes = 170;
        String directors = "Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola";
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new MinutesFilter(minMinutes, maxMinutes));
        allFilters.addFilter(new DirectorsFilter(directors));
        ArrayList<Rating> averageRatings = thirdRate.getAverageRatingsByFilter(minimalNumRatings, allFilters);
        System.out.println("Found "+averageRatings.size()+" movies.");
        System.out.println("Average rating for movies with a minimum amount of "+minimalNumRatings+" ratings is: ");
        for (Rating r : averageRatings) {
            System.out.println("Average rating is: "+r.getValue()+"\t"+"Minutes is: "+movies.getMinutes(r.getItem())+"\t"+
                "Movie title is: "+movies.getTitle(r.getItem())+"\n"+"Director is: "+movies.getDirector(r.getItem()));
        } 
    }
    
}
