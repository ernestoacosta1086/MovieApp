package app.acosta.cf.com.example.ernesto.movieapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import app.acosta.cf.com.example.ernesto.movieapp.Models.Result;
import app.acosta.cf.com.example.ernesto.movieapp.R;
import app.acosta.cf.com.example.ernesto.movieapp.Util.Constants;
import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    private Result movie;

    @Bind(R.id.poster_picture)
    ImageView posterImageView;
    @Bind(R.id.title)
    TextView titleTextView;
    @Bind(R.id.text_ranking)
    RatingBar rankRatingBar;
    @Bind(R.id.text_overview)
    TextView overviewTextView;
    @Bind(R.id.text_date)
    TextView dateTextView;
    @Bind(R.id.back_image)
    ImageView backImageView;
    @Bind(R.id.text_vote_count)
    TextView votesTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

       ButterKnife.bind(this);

        movie = (Result) getIntent().getExtras().getSerializable(Constants.MOVIE_EXTRA_KEY);
//
        Picasso.with(this)
                .load(Constants.BASE_IMAGE_URL + movie.getPosterPath())
                .into(posterImageView);

        titleTextView.setText(movie.getTitle());

        Float rank = (float) movie.getPopularity();
        rankRatingBar.setRating(rank);

        overviewTextView.setText("Overview: " + movie.getOverview());

        Picasso.with(this)
                .load(Constants.BASE_IMAGE_URL + movie.getBackdropPath())
                .into(backImageView);

        dateTextView.setText(movie.getReleaseDate());

        votesTextView.setText("Votes Count: " + movie.getVoteCount());
    }
}
