import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContentExtractorIMDB implements ContentExtractor {

    public List<Content> extractContent(String json) {

        // Obter os dados que interessam (title, poster, rating)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> atributteList = parser.parse(json);

        // Cria um arrayList
        List<Content> contents = new ArrayList<>();

        // Preencher a lista de atributos
        for (Map<String, String> att : atributteList) {
            String title = att.get("title");
            String imageUrl = att.get("image");

            var content = new Content(title, imageUrl);
            contents.add(content);
        }

        return contents;
    }
}
