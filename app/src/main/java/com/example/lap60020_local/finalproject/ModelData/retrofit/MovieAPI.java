package com.example.lap60020_local.finalproject.ModelData.retrofit;

import com.example.lap60020_local.finalproject.ModelData.Entity.FavouriteBody;
import com.example.lap60020_local.finalproject.ModelData.Entity.FavouriteResponse;
import com.example.lap60020_local.finalproject.ModelData.Entity.GenreResponse;
import com.example.lap60020_local.finalproject.ModelData.Entity.Movie;
import com.example.lap60020_local.finalproject.ModelData.Entity.MovieResponse;
import com.example.lap60020_local.finalproject.ModelData.Entity.WatchlistBody;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieAPI {

    @GET("movie/popular")
    Observable<MovieResponse> providePopularMovies(@Query("page") int page, @Query("api_key") String apiKey);
    @GET("movie/top_rated")
    Observable<MovieResponse> provideTopratedMovies(@Query("page") int page, @Query("api_key") String apiKey);

    @GET("account/{account_id}/favorite/movies")
    Observable<MovieResponse> provideUserFavouriteMovies(@Path("account_id") int account_id, @Query("page") int page, @Query("session_id") String session_id, @Query("api_key") String apiKey);
    @GET("account/{account_id}/rated/movies")
    Observable<MovieResponse> provideUserRatedMovies(@Path("account_id") int account_id, @Query("page") int page, @Query("session_id") String session_id, @Query("api_key") String apiKey);
    @GET("account/{account_id}/watchlist/movies")
    Observable<MovieResponse> provideUserWatchListMovies(@Path("account_id") int account_id, @Query("page") int page, @Query("session_id") String session_id, @Query("api_key") String apiKey);

    @GET("movie/{id}")
    Observable<Movie> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
    @GET("search/movie")
    Observable<MovieResponse> getSearchMovies(@Query("query") String query, @Query("page") int page,@Query("api_key") String apikey);

    @GET("movie/{movie_id}/recommendations")
    Observable<MovieResponse> provideRecommendedMovies(@Path("movie_id") int id, @Query("page") int page, @Query("api_key") String apiKey);
    @GET("movie/{movie_id}/similar")
    Observable<MovieResponse> provideSimilarMovies(@Path("movie_id") int id, @Query("page") int page, @Query("api_key") String apiKey);

    @GET("genre/movie/list")
    Observable<GenreResponse> provideGenres(@Query("api_key") String apiKey);
    @GET("discover/movie")
    Observable<MovieResponse> provideMovieByGenre(@Query("page") int page,@Query("with_genres") int genre, @Query("api_key") String apiKey);

    @Headers({"Content-Type: application/json;charset=utf-8"})
    @POST("account/favorite")
    @FormUrlEncoded
    Observable<FavouriteResponse> maskAsFavourite(@Query("api_key") String apiKey,
                                                  @Query("session_id") String sessionID,
                                                  @Body FavouriteBody body);

    @Headers({"Content-Type: application/json;charset=utf-8"})
    @POST("account/watchlist")
    @FormUrlEncoded
    Observable<FavouriteResponse> addToWatchlist(@Query("api_key") String apiKey,
                                                 @Query("session_id") String sessionID,
                                                 @Body WatchlistBody body);
}