package com.studio1r.retrofitsandbox.api.clients;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.studio1r.retrofitsandbox.Constants;
import com.studio1r.retrofitsandbox.api.APIConfiguration;
import com.studio1r.retrofitsandbox.api.model.Feed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;

import retrofit.RestAdapter;
import retrofit.http.GET;
import rx.Observable;

/**
 * Created by nelsonramirez on 9/3/14.
 */
public class UserFeedApiClient {

    public interface UserFeedRetrofitService {

        @GET("/userFeed")
        Observable<Feed> getUserFeed();
    }

    private UserFeedRetrofitService mUserFeedClient;
    private WeakReference<Context> mWeakContext;
    private boolean isMock;


    public UserFeedApiClient(Context context) {
        if (Constants.USE_MOCK_DATA) {
            this.mWeakContext = new WeakReference<Context>(context);
            isMock = true;
            mUserFeedClient = null;
        } else {
            mUserFeedClient = APIConfiguration.getRestAdapter(context).create(
                    UserFeedRetrofitService.class);
        }
    }

    public Observable<Feed> getUserFeed() {
        if (isMock) {
            return new MockUserFeed(mWeakContext.get()).getUserFeed();
        } else {
            return mUserFeedClient.getUserFeed();
        }
    }

    private class MockUserFeed implements UserFeedRetrofitService {

        private Feed mFeed;

        public MockUserFeed(Context context) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(context.
                        getAssets().open("feed.json")));
                Gson gson = new Gson();
                mFeed = gson.fromJson(br, Feed.class);
            } catch (IOException e) {
                Log.e("ERR", "could not load file!!");
                e.printStackTrace();
            }
        }

        @Override
        public Observable<Feed> getUserFeed() {
            return Observable.from(mFeed);
        }
    }
}
