import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
