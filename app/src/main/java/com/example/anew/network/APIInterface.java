package com.example.anew.network;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface APIInterface {

    static String apiKey="?api_key=0fd49f4bee1dab2327bfefef801584bd";

    //load popularMovies
    @GET("popular"+apiKey)
    Call<ResponseBody>loadPopular(@QueryMap HashMap<String, String> map);

    //load topRated
    @GET("top_rated"+apiKey)
    Call<ResponseBody>loadTopRated(@QueryMap HashMap<String, String> map);

    //load upcoming
    @GET("upcoming"+apiKey)
    Call<ResponseBody>loadUpcoming(@QueryMap HashMap<String, String> map);

    //load movieDetails
    @GET("{movie_id}"+apiKey)
    Call<ResponseBody>loadMovieDetails(@Path("movie_id")int movieId ,
                                       @QueryMap HashMap<String, String> map);


}
