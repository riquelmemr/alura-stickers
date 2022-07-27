import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContentExtractorCreatedApi {
    
    public List<Content> extractContent(String json) {

        // Obter os dados que interessam (title, poster, rating)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> atributteList = parser.parse(json);

        // Cria um arrayList
        List<Content> contents = new ArrayList<>();

        // Preencher a lista de atributos
        for (Map<String, String> att : atributteList) {
            String language = att.get("language");
            String logoUrl = att.get("logoUrl");

            var content = new Content(language, logoUrl);
            contents.add(content);
        }

        return contents;
    }
}
