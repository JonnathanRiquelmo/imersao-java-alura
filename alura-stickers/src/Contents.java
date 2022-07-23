public class Contents {
    
    private final String title;
    private final String imageUrl;  
    private final String imDbRating;
    private final String year;  
    
    public Contents(String title, String imageUrl, String imDbRating, String year) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.imDbRating = imDbRating;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getImDbRating() {
        return imDbRating;
    }

    public String getYear() {
        return year;
    }
    
}
