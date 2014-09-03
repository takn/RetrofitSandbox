package com.studio1r.retrofitsandbox.api.userfeed.rest;


import com.studio1r.retrofitsandbox.api.model.Feed;

import retrofit.http.GET;
import rx.Observable;

/**
 * Created by nelsonramirez on 9/3/14.
 */
public interface UserFeedRetrofitService {

    @GET("/userFeed")
    Observable<Feed> getUserFeed();
}
