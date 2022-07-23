import java.io.InputStream;
import java.net.URL;
import java.util.List;

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
        String data = httpConnection.dataSearch(url);

        Extractor extractor = new Extractor();

        List<Contents> contentList = extractor.dataExtract(data);

        // Data processing (mapper)
        for (Contents content : contentList) {
            System.out.println("Title: \u001b[1m " + content.getTitle() + "\u001b[0m (" + content.getYear() + ")");
            System.out.println("Rating: " + content.getImageUrl()
                    + " | \u001b[40;1m "
                    + IMDbRatingStars((int) Math.floor(Double.parseDouble(content.getImDbRating())))
                    + " \u001b[0m");
            System.out.println("Poster: " + content.getImageUrl() + "\n");
        }

        StickerGen generator = new StickerGen();

        for (Contents content : contentList) {
            InputStream inputStream = new URL(content.getImageUrl()).openStream();
            String fileName = "img/" + content.getTitle() + ".png";
            generator.createSticker(inputStream, fileName);
            System.out.println("Retrieving \'" + content.getTitle() + "\' poster...");
        }
        System.out.println("\n Finished !");
    }
}
