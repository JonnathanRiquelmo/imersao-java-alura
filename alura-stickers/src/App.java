import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

// ** imdb API is down, so we can use other alternatives **
// https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060
// https://alura-imdb-api.herokuapp.com/movies
// https://api.mocki.io/v2/549a5d8b
// https://alura-filmes.herokuapp.com/conteudos
// https://raw.githubusercontent.com/alexfelipe/imersao-java/json/top250.json
// https://api.mocki.io/v2/549a5d8b/Top250Movies

public class App {

    // Produces stars in terminal per movie rating
    public static String IMDbRatingStars(Integer rating) {
        String stars = "";
        for (int i = 0; i < rating; i++) {
            stars += "\u2B50";
        }
        return stars;
    }

    public static void main(String[] args) throws Exception {

        // HTTP Connection and Request
        String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
        
        HTTPClient httpConnection = new HTTPClient();
        
        // Data extraction (parser)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> moviesList = parser.parse(httpConnection.dataSearch(url));

        // Data processing (mapper)
        for (Map<String, String> movie : moviesList) {
            System.out.println("Title: \u001b[1m " + movie.get("title") + "\u001b[0m (" + movie.get("year") + ")");
            System.out.println("Rating: " + movie.get("imDbRating") 
            +" | \u001b[40;1m "
            + IMDbRatingStars((int) Math.floor(Double.parseDouble(movie.get("imDbRating"))))
            + " \u001b[0m" 
            );
            System.out.println("Poster: " + movie.get("image") + "\n");
        }

        StickerGen generator = new StickerGen();

        for (Map<String,String> movie : moviesList) {
            String urlImage = movie.get("image");
            String title = movie.get("title");

            InputStream inputStream = new URL(urlImage).openStream();
            String fileName = "img/" + title + ".png";

            generator.createSticker(inputStream, fileName);

            System.out.println("Retrieving \'" + title + "\' poster...");
        }

        System.out.println("\n Finished !");
    }
}
