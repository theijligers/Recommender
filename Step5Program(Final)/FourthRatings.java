
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class FourthRatings {
    
    private double dotProduct(Rater me, Rater r) {
        double product = 0.0;
        for (String movie_id_me : me.getItemsRated()) {
            for (String movie_id_r : r.getItemsRated()) {
                if (movie_id_me.equals(movie_id_r)) {
                    double meRating = me.getRating(movie_id_me);
                    meRating = meRating-5.0;
                    double rRating = r.getRating(movie_id_r);
                    rRating = rRating-5.0;
                    product = product+(meRating*rRating);
                }
            }
        }
        return product;
    }
    
    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        for (Rater rater : RaterDatabase.getRaters()) {
            if(!rater.getID().equals(id)) {
                double dotProduct = dotProduct(RaterDatabase.getRater(id), rater);
                if (dotProduct>=0) {
                    ratings.add(new Rating(rater.getID(), dotProduct));
                }
            }
        }
        Collections.sort(ratings, Collections.reverseOrder());
        return ratings;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRatings, int minimalRaters) {
        //Type Rating of movie_id with their weighted average ratings.
        ArrayList<Rating> list = new ArrayList<Rating>();
        //Type Rating of rater_id with their similarity rating,
        //ranked from highest similarity rating to lowest, and
        //has no negative value similarity ratings.
        ArrayList<Rating> similarRaters = getSimilarities(id);
        //Type Rating of movie_id with their average ratings with a minimum amount of rater minimalRaters.
        ArrayList<Rating> averageRatings = getAverageRatings(minimalRaters);
        ArrayList<String> movieDatabase = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
        HashMap<String, Integer> rCounter = new HashMap<String, Integer>();
        for (Rating avgRating : averageRatings) {
            String movie_id = avgRating.getItem();
            for (int k=0; k<numSimilarRatings; k++) {
                String simRater = similarRaters.get(k).getItem();
                for (String m : RaterDatabase.getRater(simRater).getItemsRated()) {
                    if (m.equals(movie_id) && rCounter.containsKey(movie_id)) {
                        rCounter.put(movie_id, rCounter.get(movie_id)+1);
                    }
                    else if (m.equals(movie_id)) {
                        rCounter.put(movie_id, 1);
                    }
                }
            }
        }
        double weightedTotal = 0;
        double weightedAverage = 0;
        for (String m : movieDatabase) {
            for (int k=0; k<numSimilarRatings; k++) {
                String rater_id = similarRaters.get(k).getItem();
                for (String movie_id : RaterDatabase.getRater(rater_id).getItemsRated()) {
                    if (m.equals(movie_id)) {
                        if (rCounter.containsKey(movie_id) && rCounter.get(movie_id)>=minimalRaters) {
                            double rating = RaterDatabase.getRater(rater_id).getRating(movie_id);
                            weightedTotal = similarRaters.get(k).getValue()*rating + weightedTotal;
                            weightedAverage = weightedTotal/rCounter.get(m);
                        }
                    }
                }
            }
            if (weightedAverage != 0.0) {
                    list.add(new Rating(m, weightedAverage));
            }
            weightedTotal = 0;
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRatings, int minimalRaters, Filter filterCriteria) {
        //Type Rating of movie_id with their weighted average ratings.
        ArrayList<Rating> list = new ArrayList<Rating>();
        //Type Rating of rater_id with their similarity rating,
        //ranked from highest similarity rating to lowest, and
        //has no negative value similarity ratings.
        ArrayList<Rating> similarRaters = getSimilarities(id);
        //Type Rating of movie_id with their average ratings with a minimum amount of rater minimalRaters.
        ArrayList<Rating> averageRatings = getAverageRatings(minimalRaters);
        ArrayList<String> movieDatabase = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
        HashMap<String, Integer> rCounter = new HashMap<String, Integer>();
        for (Rating avgRating : averageRatings) {
            String movie_id = avgRating.getItem();
            for (int k=0; k<numSimilarRatings; k++) {
                String simRater = similarRaters.get(k).getItem();
                for (String m : RaterDatabase.getRater(simRater).getItemsRated()) {
                    if (m.equals(movie_id) && rCounter.containsKey(movie_id)) {
                        rCounter.put(movie_id, rCounter.get(movie_id)+1);
                    }
                    else if (m.equals(movie_id)) {
                        rCounter.put(movie_id, 1);
                    }
                }
            }
        }
        double weightedTotal = 0;
        double weightedAverage = 0;
        for (String m : movieDatabase) {
            for (int k=0; k<numSimilarRatings; k++) {
                String rater_id = similarRaters.get(k).getItem();
                for (String movie_id : RaterDatabase.getRater(rater_id).getItemsRated()) {
                    if (m.equals(movie_id)) {
                        if (rCounter.containsKey(movie_id) && rCounter.get(movie_id)>=minimalRaters) {
                            double rating = RaterDatabase.getRater(rater_id).getRating(movie_id);
                            weightedTotal = similarRaters.get(k).getValue()*rating + weightedTotal;
                            weightedAverage = weightedTotal/rCounter.get(m);
                        }
                    }
                }
            }
            if (weightedAverage != 0.0) {
                    list.add(new Rating(m, weightedAverage));
            }
            weightedTotal = 0;
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }
    
    public double getAverageByID(String id, int minimalRaters) {
        ArrayList<String> raters = new ArrayList<String>();
        int movieCount = 0;
        double ratingTotal = 0.0;
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
        for (Rater rater : myRaters) {
            for (String movie_id : rater.getItemsRated()) {
                if (id.equals(movie_id)) {
                    movieCount++;
                }
            }
        }
        if (movieCount>=minimalRaters) {
            for (Rater rater : myRaters) {
                for (String movie_id : rater.getItemsRated()) {
                    if(id.equals(movie_id)) {
                        ratingTotal = ratingTotal + rater.getRating(movie_id);
                    }
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
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
        for (Rater rater : myRaters) {
            for (String movie_id : rater.getItemsRated()) {
                if (!movies.contains(movie_id) && movieDatabase.contains(movie_id)) {
                    movies.add(movie_id);
                    double averageByID = getAverageByID(movie_id, minimalRaters);
                    if (averageByID != 0.0) {
                        Rating ratings = new Rating(movie_id, averageByID);
                        averageRatings.add(ratings);
                    }
                }
            }
        }
        return averageRatings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter( int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> averageRatings = new ArrayList<Rating>();
        ArrayList<String> movies = new ArrayList<String>();
        ArrayList<String> movieDatabase = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
        for (Rater rater : myRaters) {
            for (String movie_id : rater.getItemsRated()) {
                if (!movies.contains(movie_id) && movieDatabase.contains(movie_id)) {
                    movies.add(movie_id);
                    double averageByID = getAverageByID(movie_id, minimalRaters);
                    if (averageByID != 0.0) {
                        Rating ratings = new Rating(movie_id, averageByID);
                        averageRatings.add(ratings);
                    }
                }
            }
        }
        return averageRatings;
    }
    
}
