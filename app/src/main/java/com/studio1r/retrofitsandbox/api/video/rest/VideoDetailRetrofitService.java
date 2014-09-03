package com.studio1r.retrofitsandbox.api.video.rest;

import com.studio1r.retrofitsandbox.api.model.VideoDetail;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by nelsonramirez on 8/29/14.
 */
public interface VideoDetailRetrofitService {

    /**
     * http://{host}/{version}/{sitecode}/video/{video_identifier}
     *
     * @return returns details for a single video
     */
    @GET("/video/{video_identifier}")
    Observable<VideoDetail> getVideo(@Path("video_identifier") String id,
    @Query("authorization") String auth);
}


