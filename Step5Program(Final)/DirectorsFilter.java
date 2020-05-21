
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class DirectorsFilter implements Filter{
    private String myDirectors;
    
    public DirectorsFilter(String directors) {
        myDirectors = directors;
    }
    
    @Override
    public boolean satisfies(String id) {
        int index = 0;
        int sIndex = 0;
        String director = "";
        int count = 0;
        for (int k=0; k<myDirectors.length(); k++) {
            if (myDirectors.substring(k, k+1).equals(",")){
                count++;
            }
            if (myDirectors.length()-k == 2) {
             break;
            }
        }
        ArrayList<String> myD = new ArrayList<String>();
        for (int i=0; i<count; i++) {
            myD.add(myDirectors.substring(sIndex, myDirectors.indexOf(",", sIndex)));
            sIndex = myDirectors.indexOf(",", sIndex)+1;
        }
        myD.add(myDirectors.substring(sIndex, myDirectors.length()));
        for (int t=0; t<=count; t++) {
            String currentDirector = myD.get(t);
            while (MovieDatabase.getDirector(id).indexOf(", ", index) != -1) {
                director = MovieDatabase.getDirector(id).substring(index, MovieDatabase.getDirector(id).indexOf(", ", index));
                if (director.equals(currentDirector)) {
                    return true;
                }
                index = MovieDatabase.getDirector(id).indexOf(", ", index)+2;
            }
            director = MovieDatabase.getDirector(id).substring(index);
            if (director.equals(currentDirector)) {
                return true;
            }
        }
        return false;
    }
}
