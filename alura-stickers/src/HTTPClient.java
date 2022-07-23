import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// ** imdb API is down, so we can use other alternatives **
// https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060
// https://alura-imdb-api.herokuapp.com/movies
// https://api.mocki.io/v2/549a5d8b
// https://alura-filmes.herokuapp.com/conteudos
// https://raw.githubusercontent.com/alexfelipe/imersao-java/json/top250.json
// https://api.mocki.io/v2/549a5d8b/Top250Movies

public class HTTPClient {

    public String dataSearch(String url) {

        try {
            URI address = URI.create(url);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(address).GET().build();

            // HTTP Send and Response
            HttpResponse<String> response;

            response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String body = response.body();
            return body;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "url not found";
    }
}
