package com.studio1r.retrofitsandbox.api.userfeed;

import android.content.Context;

import com.studio1r.retrofitsandbox.BuildConfig;
import com.studio1r.retrofitsandbox.api.APIConfiguration;
import com.studio1r.retrofitsandbox.api.model.Feed;
import com.studio1r.retrofitsandbox.api.userfeed.rest.UserFeedRetrofitService;

import java.lang.ref.WeakReference;

import retrofit.RestAdapter;
import rx.Observable;

/**
 * Created by nelsonramirez on 9/3/14.
 */
public class UserFeedApiClient {
    private UserFeedRetrofitService mUserFeedClient;
    private WeakReference<Context> context;
    private boolean isMock;

    public UserFeedApiClient() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(APIConfiguration.getEndpoint())
                .build();

        restAdapter.setLogLevel(RestAdapter.LogLevel.BASIC);
        mUserFeedClient = restAdapter.create(
                UserFeedRetrofitService.class);
    }

    public UserFeedApiClient(Context context) throws IllegalAccessException {
        if (BuildConfig.DEBUG) {
            this.context = new WeakReference<Context>(context);
            isMock = true;
            mUserFeedClient = null;
        } else {
            throw new IllegalAccessException("you can only use mocks in debug builds." +
                    "this is for your own good!");
        }
    }

    public Observable<Feed> getUserFeed() {
        if (isMock) {
            return null;
        } else {
            return mUserFeedClient.getUserFeed();
        }
    }
}
