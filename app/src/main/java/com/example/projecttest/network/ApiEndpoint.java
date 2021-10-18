package com.example.projecttest.network;


import com.example.projecttest.PostModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;

public interface ApiEndpoint {

    String getDataPosts = "typicode/demo/posts";

    @GET(getDataPosts)
    Observable<Response<List<PostModel>>> getDataPosts();

}
