
package news.agoda.com.sample.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsResponseEntity {

    private String status;
    private String copyright;
    private String section;
    @SerializedName("last_updated")
    private String lastUpdated;
    @SerializedName("num_results")
    private Integer numResults;
    @SerializedName("results")
    private List<NewsEntity> results;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Integer getNumResults() {
        return numResults;
    }

    public void setNumResults(Integer numResults) {
        this.numResults = numResults;
    }

    public List<NewsEntity> getResults() {
        return results;
    }

    public void setResults(List<NewsEntity> results) {
        this.results = results;
    }
}
