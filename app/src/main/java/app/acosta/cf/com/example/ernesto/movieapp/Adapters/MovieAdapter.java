package app.acosta.cf.com.example.ernesto.movieapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.squareup.picasso.Picasso;

import java.util.List;

import app.acosta.cf.com.example.ernesto.movieapp.Activities.DetailActivity;
import app.acosta.cf.com.example.ernesto.movieapp.Models.Result;
import app.acosta.cf.com.example.ernesto.movieapp.R;
import app.acosta.cf.com.example.ernesto.movieapp.Util.Constants;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Ernesto on 18/1/2016.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    Context context;
    List<Result> results;
    LayoutInflater inflater;

    public MovieAdapter(Context context, List<Result> results) {
        this.context = context;
        this.results = results;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int position) {

        View view = inflater.inflate(R.layout.item_movie, parent, false);
        MovieHolder holder = new MovieHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, final int position) {

        holder.posterRatingBar.setRating((float) results.get(position).getVoteAverage());
        holder.posterRatingBar.setContentDescription(results.get(position).getVoteAverage() + "");

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(Constants.MOVIE_EXTRA_KEY, results.get(position));
                context.startActivity(intent);
            }
        });

        if (results.get(position).getPosterPath() != null && !results.get(position).getPosterPath().isEmpty()
                && !results.get(position).getPosterPath().equals("null")) {
            Picasso.with(context)
                    .load(Constants.BASE_IMAGE_URL + results.get(position).getPosterPath()).error(R.drawable.default_poster)
                    .into(holder.imageView);
        }

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.rating_poster)
        RatingBar posterRatingBar;
        @Bind(R.id.poster_picture)
        ImageView imageView;

        public MovieHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

//
//
//    private List<Result> results;
//    private Context context;
//
//
//
//    public MovieAdapter(List<Result> results, Context context) {
//        this.results = results;
//        this.context = context;
//    }
//
//    @Override
//    public int getCount() {
//
//    }
//
//    @Override
//    public Result getItem(int position) {
//        return results.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//
//        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, null);
//        ButterKnife.bind(this, itemView);
//
//
//        return itemView;
//    }
//}
