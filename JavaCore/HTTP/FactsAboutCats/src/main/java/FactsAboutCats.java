import com.fasterxml.jackson.annotation.JsonProperty;

public class FactsAboutCats {
    private String id;
    private String text;
    private String type;
    private String user;
    private int upvotes;

    public FactsAboutCats(
            @JsonProperty("id") String id,
            @JsonProperty("text") String text,
            @JsonProperty("type") String type,
            @JsonProperty("user") String user,
            @JsonProperty("upvotes") int upvotes) {
        this.id = id;
        this.type = type;
        this.text = text;
        this.upvotes = upvotes;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }

    public String getUser() {
        return user;
    }

    public int getUpvotes() {
        return upvotes;
    }

    @Override
    public String toString() {
        return "Fact id - " + id +
                "\nFact  text - " + text +
                "\nType - " + type +
                "\nUser - " + user +
                "\nUpvotes - " + upvotes + "\n";
    }
}
