package com.studio1r.retrofitsandbox.api;

import android.content.Context;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.studio1r.retrofitsandbox.Constants;
import com.studio1r.retrofitsandbox.Observers.NetworkAwareObserver;
import com.studio1r.retrofitsandbox.api.clients.UserFeedApiClient;
import com.studio1r.retrofitsandbox.api.clients.VideoDetailApiClient;
import com.studio1r.retrofitsandbox.api.model.Feed;
import com.studio1r.retrofitsandbox.api.model.VideoDetailItem;
import com.studio1r.retrofitsandbox.api.requests.UserFeedRequest;
import com.studio1r.retrofitsandbox.api.requests.VideoDetailRequest;
import com.studio1r.retrofitsandbox.api.responses.UserFeedResponse;
import com.studio1r.retrofitsandbox.api.responses.VideoDetailResponse;
import com.studio1r.retrofitsandbox.db.DBHelper;

import de.greenrobot.event.EventBus;
import rx.schedulers.Schedulers;

/**
 * Retrieves data and manages caches.
 * Requests are furbished in the following order:
 * 1. Is it in the data lru mem cache?
 * 2. Is it in the db? Provigen content provider
 * 3. Ask Retrofit for the data.
 * Created by nelsonramirez on 9/1/14.
 */
public class MKRApi {

    public static String TAG = MKRApi.class.getName();
    private final LruCache<String, VideoDetailItem> mVideoLRUCache;
    private Context mContext;
    private VideoDetailApiClient mVidDetailClient;
    private UserFeedApiClient mUserFeedClient;

    public MKRApi(Context context) {
        mContext = context;
        EventBus.getDefault().register(this);
        mVideoLRUCache = new LruCache<String, VideoDetailItem>(Constants.MAX_CACHE_ENTRIES);
    }

    public void destroy() {
        EventBus.getDefault().unregister(this);
        mContext = null;
    }

    public void onEvent(UserFeedRequest request) {
        if (mUserFeedClient == null) {
            mUserFeedClient = new UserFeedApiClient(mContext);
        }
        mUserFeedClient.getUserFeed().subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(new InternalUserFeedObserver());
    }

    //TODO need to set expiration rules.
    public void onEvent(VideoDetailRequest request) {
        Log.d(TAG, "Detail request received with id:" + request.id);
        //TODO abstract cache logic so we don't have to repeat it all the time. although most
        //requests will be different
        VideoDetailItem vid = mVideoLRUCache.get(request.id);
        if (vid != null) {
            Log.d(TAG, "lru cache hit for::" + request.id);
            EventBus.getDefault().post(new VideoDetailResponse<VideoDetailItem>(vid));
            return;
        }

        vid = DBHelper.getVideoDetail(mContext, request.id);
        if (vid != null) {
            Log.d(TAG, "database cache hit for::" + request.id);
            EventBus.getDefault().post(new VideoDetailResponse<VideoDetailItem>(vid));
            return;
        } else {
            mVidDetailClient = new VideoDetailApiClient(mContext);
            mVidDetailClient.getVideoDetail(request.id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.computation())
                    .subscribe(new InternalVideoDetailObserver());

        }


    }

    /**
     * INTERNAL OBSERVERS
     */

    class InternalVideoDetailObserver extends NetworkAwareObserver<VideoDetailItem> {
        @Override
        public void onCompleted() {
            Log.i("InternalVideoDetailObserver", "InternalVideoDetailObserver.onCompleted()");
        }

        @Override
        public void onNext(VideoDetailItem videoDetailItem) {
            //add results to LRU cache
            mVideoLRUCache.put(videoDetailItem.code, videoDetailItem);
            //add results to database
            DBHelper.insertVideoDetail(mContext, videoDetailItem);
            EventBus.getDefault().post(new VideoDetailResponse<VideoDetailItem>(videoDetailItem));

        }
    }

    class InternalUserFeedObserver extends NetworkAwareObserver<Feed> {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onNext(Feed feed) {
            //TODO we probably don't need to wrap the data.. could just post it, but it may be
            //useful for error handling.
            EventBus.getDefault().postSticky(new UserFeedResponse<Feed>(feed));
        }
    }
}

