
/**
 * Write a description of GenreFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class GenreFilter implements Filter{
    private String myGenre;
    
    public GenreFilter(String genre) {
        myGenre = genre;
    }
    
    @Override
    public boolean satisfies(String id) {
        int index = 0;
        String genre = "";
        while (MovieDatabase.getGenres(id).indexOf(", ", index) != -1) {
            genre = MovieDatabase.getGenres(id).substring(index, MovieDatabase.getGenres(id).indexOf(", ", index));
            if (genre.equals(myGenre)) {
                return true;
            }
            index = MovieDatabase.getGenres(id).indexOf(", ", index)+2;
        }
        genre = MovieDatabase.getGenres(id).substring(index);
        if (genre.equals(myGenre)) {
            return true;
        }
        return false;
    }
}
