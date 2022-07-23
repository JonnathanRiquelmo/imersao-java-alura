import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Extractor {

    public List<Contents> dataExtract(String data) {

        // Data extraction (parser)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> attList = parser.parse(data);

        List<Contents> contentList = new ArrayList<>();

        for (Map<String, String> attributes : attList) {
            contentList.add(new Contents(attributes.get("title"), attributes.get("image"), attributes.get("imDbRating"), attributes.get("year")));
        }

        return contentList;
    }

}
