
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import java.io.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename) {
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        FileResource file = new FileResource("data/"+filename);
        CSVParser parse = file.getCSVParser();
        for (CSVRecord rec : parse) {
            Movie movie = new Movie(rec.get("id"), rec.get("title"), rec.get("year"), rec.get("genre"),
                rec.get("director"), rec.get("country"), rec.get("poster"), Integer.parseInt(rec.get("minutes")));
            //System.out.println("id = "+rec.get("id")+"\t"+"title = "+rec.get("title")+"\t"+"year = "+rec.get("year")+"\t"+
            //    "genre = "+rec.get("genre")+"\t"+"director = "+rec.get("director")+"\t"+"country = "+rec.get("country")+"\t"+
            //    "poster = "+rec.get("poster")+"\t"+"minutes = "+rec.get("minutes"));
            movieList.add(movie);
        }
        return movieList;
    }
    
    public ArrayList<Rater> loadRaters(String filename) {
        ArrayList<Rater> raterList = new ArrayList<Rater>();
        FileResource file = new FileResource("data/"+filename);
        CSVParser parse = file.getCSVParser();
        // Original Code that works with FirstRatings test methods.
        for (CSVRecord rec : parse) {
            Rater rater = new EfficientRater(rec.get("rater_id"));
            rater.addRating(rec.get("movie_id"), Integer.parseInt(rec.get("rating")));
            raterList.add(rater);
        }
        return raterList;
    }
    
    public void testLoadRaters() {
        //ArrayList<Rater> raterList = loadRaters("ratings_short.csv");
        ArrayList<Rater> raterList = loadRaters("ratings.csv");
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        ArrayList<Rater> raterArray = new ArrayList<Rater>();
        int raterIDCount = 0;
        int raterID = 1;
        for (int k=0; k<raterList.size(); k++){
            if (Integer.parseInt(raterList.get(k).getID()) == raterID && k != raterList.size()-1) {
                raterIDCount++;
            }
            else if (k == raterList.size()-1) {
                raterIDCount++;
                map.put(raterID, raterIDCount);
            }
            else {
                map.put(raterID, raterIDCount);
                raterIDCount = 1;
                raterID++;
            }
        }
        System.out.println("Number of raters in file = "+map.size());
        // raterID = 1;
        // raterIDCount = 0;
        // System.out.println("Rater "+"\""+raterID+"\""+" has done "+map.get(raterID)+" ratings.");
        // for (int k=0; k<raterList.size(); k++){
            // if (Integer.parseInt(raterList.get(k).getID()) == raterID && k != raterList.size()-1) {
                // raterIDCount++;
            // }
            // else if (k == raterList.size()-1) {
                // raterIDCount++;
            // }
            // else {
                // raterIDCount = 1;
                // raterID++;
                // System.out.println("Rater "+"\""+raterID+"\""+" has done "+map.get(raterID)+" ratings.");
            // }
            // System.out.println("Movie ID "+raterList.get(k).getItemsRated().get(0)+"\t"+
                // "Rating is "+raterList.get(k).getRating(raterList.get(k).getItemsRated().get(0)));
        // }
        System.out.println("Rater "+"\""+193+"\""+" has done "+map.get(193)+" ratings.");
        ArrayList<Integer> maxNumRater = new ArrayList<Integer>();
        int maxNumRatings = 0;
        for (int key : map.keySet()) {
            if (map.get(key)>maxNumRatings) {
                maxNumRatings = map.get(key);
            }
        }
        for (int key : map.keySet()) {
            if (map.get(key) == maxNumRatings) {
                maxNumRater.add(key);
            }
        }
        System.out.println("These Raters have submitted a maximum of "+maxNumRatings+" sperate ratings:");
        for (int r=0; r<maxNumRater.size(); r++) {
            System.out.println(maxNumRater.get(r));
        }
            // System.out.println("Movie ID "+raterList.get(k).getItemsRated().get(0)+"\t"+
                // "Rating is "+raterList.get(k).getRating(raterList.get(k).getItemsRated().get(0)));
        String movieTarget = "1798709";
        String movieID = "";
        String rater_ID = "";
        double movieRating = 0.0;
        int movieIDCount = 0;
        ArrayList<String> moviesRated = new ArrayList<String>();
        for (int k=0; k<raterList.size(); k++){
            rater_ID = raterList.get(k).getID();
            movieID = raterList.get(k).getItemsRated().get(0);
            movieRating = raterList.get(k).getRating(movieID);
            if (movieTarget.equals(movieID)) {
                movieIDCount++;
            }
            if (!moviesRated.contains(movieID)) {
                moviesRated.add(movieID);
            }
        }
        System.out.println(movieIDCount+" different raters rated movie "+movieTarget);
        System.out.println("Number of different movies rated is "+moviesRated.size());
    }
    
    public void testLoadMovies() {
        //ArrayList<Movie> movieList = loadMovies("ratedmovies_testcsv.csv");
        ArrayList<Movie> movieList = loadMovies("ratedmoviesfull.csv");
        System.out.println("Number of movies in file = "+movieList.size());
        int genreCount = 0;
        int minuteCount = 0;
        int maxMoviesByDirector = 0;
        HashMap<String, Integer> directors = new HashMap<String, Integer>();
        for (int k=0; k<movieList.size(); k++){
            //System.out.println(movieList.get(k));
            String genreList = movieList.get(k).getGenres();
            int index = 0;
            String genre = "";
            while (genreList.indexOf(", ", index) != -1) {
                genre = genreList.substring(index, genreList.indexOf(", ", index));
                if (genre.equals("Comedy")) {
                    genreCount++;
                }
                index = genreList.indexOf(", ", index)+2;
            }
            genre = genreList.substring(index);
            if (genre.equals("Comedy")) {
                    genreCount++;
            }
            if (movieList.get(k).getMinutes() > 150) {
                minuteCount++;
            }
            // String DirectorList = movieList.get(k).getDirector();
            // int dIndex = 0;
            // String sDirector = "";
            // while (DirectorList.indexOf(", ", dIndex) != -1) {
                // sDirector = DirectorList.substring(dIndex, DirectorList.indexOf(", ", dIndex));
                // System.out.println(sDirector);
                // if (directors.containsKey(sDirector)) {
                    // directors.put(sDirector, directors.get(sDirector)+1);
                // }
                // if (!directors.containsKey(sDirector)) {
                    // directors.put(sDirector, 1);
                // }
                // if (directors.get(sDirector)>maxMoviesByDirector) {
                    // maxMoviesByDirector = directors.get(sDirector);
                // }
                // dIndex = DirectorList.indexOf(", ", dIndex)+2;
            // }
            // sDirector = DirectorList.substring(dIndex);
            if (directors.containsKey(movieList.get(k).getDirector())) {
                directors.put(movieList.get(k).getDirector(), directors.get(movieList.get(k).getDirector())+1);
            }
            if (!directors.containsKey(movieList.get(k).getDirector())) {
                directors.put(movieList.get(k).getDirector(), 1);
            }
            if (directors.get(movieList.get(k).getDirector())>maxMoviesByDirector) {
                maxMoviesByDirector = directors.get(movieList.get(k).getDirector());
            }
            
        }
        System.out.println("Number of Movies with genre Comedy = "+genreCount);
        System.out.println("Number of Movies with minutes over 150 = "+minuteCount);
        System.out.println("Max number of Movies by Director = "+maxMoviesByDirector);
        ArrayList<String> maxMovieDirectors = new ArrayList<String>();
        for (String key : directors.keySet()) {
            if (directors.get(key) == maxMoviesByDirector) {
                maxMovieDirectors.add(key);
            }
        }
        System.out.println("Directors with "+maxMoviesByDirector+" number of movies "+maxMovieDirectors);
    }
}
