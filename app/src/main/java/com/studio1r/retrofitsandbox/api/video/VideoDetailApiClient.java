package com.studio1r.retrofitsandbox.api.video;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.studio1r.retrofitsandbox.BuildConfig;
import com.studio1r.retrofitsandbox.api.APIConfiguration;
import com.studio1r.retrofitsandbox.api.model.VideoDetail;
import com.studio1r.retrofitsandbox.api.video.rest.VideoDetailRetrofitService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;

import retrofit.RestAdapter;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by nelsonramirez on 8/29/14.
 */
public class VideoDetailApiClient {
    private final VideoDetailRetrofitService mVideoDetailClient;
    private WeakReference<Context> context;
    private boolean isMock;

    public VideoDetailApiClient() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(APIConfiguration.getEndpoint())
                .setRequestInterceptor(APIConfiguration.requestInterceptor)
                .build();

        restAdapter.setLogLevel(RestAdapter.LogLevel.BASIC);
        mVideoDetailClient = restAdapter.create(
                VideoDetailRetrofitService.class);
    }

    /**
     * This constructor is used for dev only. It will return mocked objects pulled from resources
     *
     * @param context
     */
    public VideoDetailApiClient(Context context) throws IllegalAccessException {
        if (BuildConfig.DEBUG) {
            this.context = new WeakReference<Context>(context);
            isMock = true;
            mVideoDetailClient = null;
        } else {
            throw new IllegalAccessException("you can only use mocks in debug builds." +
                    "this is for your own good!");
        }
    }


    public Observable<VideoDetail> getVideoDetail(String id) {
        if (isMock) {
            return new MockVideoDetail(this.context.get()).getVideo(id);
        } else {
            return mVideoDetailClient.getVideo(id);
        }
    }

    private static class MockVideoDetail implements VideoDetailRetrofitService {

        private VideoDetail mVideoDetail;

        public MockVideoDetail(Context context) {
            try {
//                InputStream is = ;
                BufferedReader br = new BufferedReader(new InputStreamReader(context.
                        getAssets().open("videodetail.json")));
                Gson gson = new Gson();
                mVideoDetail = gson.fromJson(br, VideoDetail.class);
            } catch (IOException e) {
                Log.e("ERR", "could not load file!!");
                e.printStackTrace();
            }
        }

        @Override
        public Observable<VideoDetail> getVideo(@Path("video_identifier") String id) {
            //read json file
            return Observable.from(mVideoDetail);
        }
    }

}
