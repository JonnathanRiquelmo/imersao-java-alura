import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

// ** imdb API is down, so we can use other alternatives **
// https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060
// https://alura-imdb-api.herokuapp.com/movies
// https://api.mocki.io/v2/549a5d8b
// https://alura-filmes.herokuapp.com/conteudos
// https://raw.githubusercontent.com/alexfelipe/imersao-java/json/top250.json

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
        String url = "https://alura-filmes.herokuapp.com/conteudos";
        URI address = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(address).GET().build();

        // HTTP Send and Response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();
        // System.out.println(body);

        // Data extraction (parser)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> moviesList = parser.parse(body);

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
    }
}
