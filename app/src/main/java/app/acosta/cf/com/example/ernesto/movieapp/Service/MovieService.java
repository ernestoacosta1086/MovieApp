package app.acosta.cf.com.example.ernesto.movieapp.Service;

import app.acosta.cf.com.example.ernesto.movieapp.Models.Movies;
import app.acosta.cf.com.example.ernesto.movieapp.Util.Constants;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ernesto on 9/3/2016.
 */
public interface MovieService {

    public static String BASE_URL = Constants.BASE_URL;


    //Haicneod peticion por parámetros
    @GET("discover/movie/")
    Call<Movies> getMovieList(@Query("api_key") String api_key,
                              @Query("vote_average.gte") String rating,
                              @Query("vote_count.gte") String votes,
                              @Query("primary_release_year") String year,
                              @Query("sort_by") String sort_by);


    // Haciendo petición por Objeto
//    @GET("discover/movie/")
//    Call<Movies> getMovieList(@Query("api_key") String api_key,
//                              @Body Filter filter);

    class Factory {
        private static MovieService service;

        public static MovieService getInstance() {
            if (service == null) {

//                Cliente de OkHttp Cliente aun no se para que sirve
//                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//                OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
//                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                service = retrofit.create(MovieService.class);

                return service;
            } else {
                return service;
            }


        }
    }
}
