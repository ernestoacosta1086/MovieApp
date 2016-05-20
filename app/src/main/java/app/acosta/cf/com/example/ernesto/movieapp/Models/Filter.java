package app.acosta.cf.com.example.ernesto.movieapp.Models;

/**
 * Created by Ernesto on 14/3/2016.
 */
public class Filter {

    private String releaseDate;
    private String voteCount;
    private String voteAverage;
    private String orderByRating;

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(String voteCount) {
        this.voteCount = voteCount;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getOrderByRating() {
        return orderByRating;
    }

    public void setOrderByRating(String orderByRating) {
        this.orderByRating = orderByRating;
    }

    public Filter(String voteAverage, String voteCount, String releaseDate, String orderByRating) {

        this.releaseDate = releaseDate;
        this.voteCount = voteCount;
        this.voteAverage = voteAverage;
        this.orderByRating = orderByRating;
    }

    public Filter() {

    }

}
