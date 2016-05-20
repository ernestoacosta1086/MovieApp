package app.acosta.cf.com.example.ernesto.movieapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import app.acosta.cf.com.example.ernesto.movieapp.Adapters.MovieAdapter;
import app.acosta.cf.com.example.ernesto.movieapp.BuildConfig;
import app.acosta.cf.com.example.ernesto.movieapp.Models.Filter;
import app.acosta.cf.com.example.ernesto.movieapp.Models.Movies;
import app.acosta.cf.com.example.ernesto.movieapp.R;
import app.acosta.cf.com.example.ernesto.movieapp.Service.MovieService;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class    MainActivity extends AppCompatActivity {

    MovieAdapter movieAdapter;
    Filter filter;
    Filter filterAux;
    SharedPreferences prefs;

    @Bind(R.id.recycler_movie)
    RecyclerView recycler;
    @Bind(R.id.toolbar)
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        toolbar.setSubtitle("I´m not surprise Motherfuckers");

        filterAux = new Filter();

        GridLayoutManager mGridLayoutManager = new GridLayoutManager(this, 2); // (Context context, int spanCount)

        recycler.setLayoutManager(mGridLayoutManager);

    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshMovies();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void refreshMovies() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String year = prefs.getString(getString(R.string.pref_key_year), getString(R.string.pref_value_year));
        String votes = prefs.getString(getString(R.string.pref_key_votes), getString(R.string.pref_label_values_votes_200));
        String rating = prefs.getString(getString(R.string.pref_key_rating), getString(R.string.pref_label_values_rating_2));
        String sortBy = prefs.getString(getString(R.string.pref_key_order), getString(R.string.pref_value_by_rating_desc));
        filter = new Filter(year, votes, rating, sortBy);

        if (!filterAux.equals(filter)) {
            filterAux = filter;
            MovieService.Factory.getInstance().getMovieList(BuildConfig.TMDB_API_KEY, rating, votes, year, sortBy)
                    .enqueue(new Callback<Movies>() {
                        @Override
                        public void onResponse(Call<Movies> call, Response<Movies> response) {
                            Movies movies = response.body();
                            movieAdapter = new MovieAdapter(getContextI(), movies.getResults());
                            recycler.setAdapter(movieAdapter);
                        }

                        @Override
                        public void onFailure(Call<Movies> call, Throwable t) {
                            Log.e("Failed", t.getMessage());
                        }
                    });
        }
    }

    private Context getContextI() {
        return this;
    }
}
