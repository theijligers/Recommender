
import java.util.*;

/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RecommendationRunner implements Recommender{
    public ArrayList<String> getItemsToRate(){
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> movieDatabase = MovieDatabase.filterBy(new TrueFilter());
        Random random = new Random();
        int r = random.nextInt(movieDatabase.size());
        ArrayList<Integer> count = new ArrayList<Integer>();
        int counter = 0;
        for (int k=r; k<movieDatabase.size(); k=r) {
            if (counter==20) break;
            if (!count.contains(k)) {
                list.add(movieDatabase.get(k));
                r = random.nextInt(MovieDatabase.size());
                counter++;
                count.add(k);
            }
        }
        return list;
    }
    
    public void printRecommendationsFor(String webRaterID) {
        FourthRatings fourthRate = new FourthRatings();
        int minimalNumRatings = 5;
        int topSimilarRaters = 20;
        ArrayList<Rating> similarRatings = fourthRate.getSimilarRatings(webRaterID, topSimilarRaters, minimalNumRatings);
        int count = 0;
        StringBuilder string = new StringBuilder("<table>"+"\n"+
                "<tr>"+"\n"+
                  "<th>Rank</th>"+"\n"+
                  "<th>Poster</th>"+" align="+"\""+"middle"+"\""+"\n"+
                  "<th>Movie</th>"+" align="+"\""+"middle"+"\""+"\n"+
                "</tr>");
        if (similarRatings.size()>0) {
            for (Rating r : similarRatings) {
                if(count==20) break;
                string.append("<tr>"+"\n");
                string.append("<td>"+"\n");
                string.append((count+1)+"."+"\n");
                string.append("</td>"+"\n");
                string.append("<td>"+"\n");
                string.append("<a"+" href="+"\n");
                string.append("data/"+MovieDatabase.getPoster(r.getItem()).substring(7)+">"+"\n");
                string.append("<img style="+"\""+"display:block;"+"\""+"src="+"data/"+MovieDatabase.getPoster(r.getItem()).substring(7)+"\n");
                string.append(" height="+"\""+"100"+"\""+" align="+"\""+"middle"+"\""+">"+"\n");
                string.append("</a>"+"\n");
                string.append("</td>"+"\n");
                string.append("<td>"+"\n");
                string.append(MovieDatabase.getYear(r.getItem())+" &nbsp;&nbsp; <a href="+"\""+"http://www.imdb.com/title/tt"+r.getItem()+"/"+"\""+">"+MovieDatabase.getTitle(r.getItem())+"</a>"+"\n");
                string.append("</td>"+"\n");
                string.append("</tr>"+"\n");
                //System.out.println(MovieDatabase.getPoster(r.getItem())+MovieDatabase.getYear(r.getItem())+"\t"+MovieDatabase.getTitle(r.getItem()));
                count++;
            }
            System.out.println(string);
        }
        else System.out.println("No movies to recommend. Try submitting more reviews");
    }
}
