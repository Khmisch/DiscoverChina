package com.example.myapplication.network.service;

import android.provider.Contacts;

import com.example.myapplication.model.Photo;
import com.example.myapplication.model.RelatedPhotos;
import com.example.myapplication.model.ResultProfiles;
import com.example.myapplication.model.Search;
import com.example.myapplication.model.Topic;
import com.example.myapplication.model.profile.ProfileResp;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PhotoService {

    String ACCESS_KEY = "tJazrzFH2q4OV1hKlvfFYvCxzegtvjhUkRhNx6E6ekY";

    @Headers("Authorization: Client-ID " + ACCESS_KEY)
    @GET("photos")
    Call<ArrayList<Photo>> getPhotos(@Query("page") int page, @Query("per_page") int per_page);

    @Headers("Authorization: Client-ID " + ACCESS_KEY)
    @GET("photos/{id}/related")
    Call<RelatedPhotos> getRelatedPhotos(@Path("id") String id);

    @Headers("Authorization: Client-ID " + ACCESS_KEY)
    @GET("search/photos")
    Call<Search> searchPhoto(@Query("query") String query, @Query("page") int page, @Query("per_page") int per_page);

    @Headers("Authorization: Client-ID " + ACCESS_KEY)
    @GET("search/users")
    Call<ResultProfiles> getSearchProfile(@Query("page") int page, @Query("query") String query, @Query("per_page") int perPage);

    @Headers("Authorization: Client-ID " + ACCESS_KEY)
    @GET("photos/random")
    Call<ArrayList<Photo>> getRandomPhotos(@Query("query") String query, @Query("orientation") String orientation, @Query("count") int count);

    @Headers("Authorization: Client-ID " + ACCESS_KEY)
    @GET("topics")
    Call<ArrayList<Topic>> getTopics(@Query("page") int page, @Query("per_page") int perPage);

    @Headers("Authorization: Client-ID " + ACCESS_KEY)
    @GET("users/{username}")
    Call<ProfileResp> getUser(@Path("username") String username);
}
