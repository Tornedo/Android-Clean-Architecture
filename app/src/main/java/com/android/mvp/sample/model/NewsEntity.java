package news.agoda.com.sample.model;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import news.agoda.com.sample.utils.EmptyStringAsNullTypeAdapter;

/**
 * This represents a news item
 */
public class NewsEntity {

    private String title;
    @SerializedName("abstract")
    private String summary;
    @SerializedName("url")
    private String articleUrl;
    private String byline;
    @SerializedName("published_date")
    private String publishedDate;
    @JsonAdapter(EmptyStringAsNullTypeAdapter.class)
    private List<MediaEntity> multimedia;


    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public String getByline() {
        return byline;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public List<MediaEntity> getMediaEntity() {
        return multimedia;
    }
}
