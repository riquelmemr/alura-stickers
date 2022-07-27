// import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
// import java.util.Properties;

public class App {

    public static final String STAR = "⭐";
    public static void main(String[] args) throws Exception {

        // Conectar os dados do imdb via HTTP
        // Properties prop = new Properties();
        // FileInputStream file = new FileInputStream("./properties/conf.properties");
        // prop.load(file);
        // var key = prop.getProperty("api.key");

        // String url = "https://api.mocki.io/v2/" + key;
        // var extractor = new ContentExtractorIMDB();

        // String url = "https://api.mocki.io/v2/" + key + "/NASA-APOD";
        // var extractor = new ContentExtractorNasa();

        String url = "http://localhost:8080/languages";
        var extractor = new ContentExtractorCreatedApi();

        var http = new HTTPClient();
        String json = http.SearchData(url);

        // Exibir e manipular os dados
        List<Content> contents = extractor.extractContent(json);

        var generator = new StickGenerator();

        for (int i = 0; i <= 3; i++) {

            Content content = contents.get(i);
            
            String title = content.getTitle();
            String imageUrl = content.getImageUrl();
            String fileName = title + ".png";

            InputStream inputStream = new URL(imageUrl).openStream();
            generator.creator(inputStream, fileName);

            System.out.println("\u001b[34m Título: \u001b[m" + "\u001b[35m" + title);
            System.out.println("\u001b[34m Poster: \u001b[m" + "\u001b[35m" + imageUrl);
            System.out.println();
        }
    }
}
