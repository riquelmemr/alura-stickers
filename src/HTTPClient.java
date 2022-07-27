import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class HTTPClient {
    
    public String SearchData(String url) {

        try {
            var adress = URI.create(url);
            var client = HttpClient.newHttpClient();
            var request = HttpRequest.newBuilder(adress).GET().build();
            HttpResponse<String> reponse = client.send(request, BodyHandlers.ofString());
            String body = reponse.body();
            return body;

        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException(ex); 
        }
    }
}
