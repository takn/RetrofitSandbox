package com.studio1r.retrofitsandbox.api;

import android.content.Context;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.studio1r.retrofitsandbox.Constants;
import com.studio1r.retrofitsandbox.Observers.NetworkAwareObserver;
import com.studio1r.retrofitsandbox.api.model.VideoDetail;
import com.studio1r.retrofitsandbox.api.requests.VideoDetailRequest;
import com.studio1r.retrofitsandbox.api.responses.VideoDetailResponse;
import com.studio1r.retrofitsandbox.api.video.VideoDetailApiClient;
import com.studio1r.retrofitsandbox.db.DBHelper;

import de.greenrobot.event.EventBus;
import rx.schedulers.Schedulers;

/**
 * Retrieves data and manages caches.
 * Requests are furbished in the following order:
 * 1. Is it in the data mem cache?
 * 2. Is it in the db?
 * 3. Ask Retrofit for the data.
 * Created by nelsonramirez on 9/1/14.
 */
public class MKRApi {

    public static String TAG = MKRApi.class.getName();
    private final LruCache<String, VideoDetail> mVideoLRUCache;
    private Context mContext;
    private VideoDetailApiClient mVidDetailClient;

    public MKRApi(Context context) {
        mContext = context;
        EventBus.getDefault().register(this);
        mVideoLRUCache = new LruCache<String, VideoDetail>(Constants.MAX_CACHE_ENTRIES);
    }

    public void destroy() {
        EventBus.getDefault().unregister(this);
        mContext = null;
    }

    public void onEventMainThread(VideoDetailRequest request) {
        Log.d(TAG, "Detail request received with id:" + request.id);

        if (Constants.USE_MOCK_DATA) {
            try {
                //TODO when we pass in a context the api client furnishes mock data,
                //but this is confusing! make a proper MockClient.
                mVidDetailClient = new VideoDetailApiClient(mContext);
                mVidDetailClient.getVideoDetail(request.id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.computation())
                        .subscribe(new InternalVideoDetailObserver());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            VideoDetail vid = mVideoLRUCache.get(request.id);
            if (vid != null) {
                Log.d(TAG, "lru cache hit for::" + request.id);
                EventBus.getDefault().post(new VideoDetailResponse(vid));
                return;
            }

            vid = DBHelper.getVideoDetail(mContext, request.id);
            if (vid != null) {
                Log.d(TAG, "database cache hit for::" + request.id);
                EventBus.getDefault().post(new VideoDetailResponse(vid));
                return;
            } else {
                mVidDetailClient = new VideoDetailApiClient();
                mVidDetailClient.getVideoDetail(request.id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.computation())
                        .subscribe(new InternalVideoDetailObserver());

            }
        }

    }

    /**
     * INTERNAL OBSERVERS
     */

    class InternalVideoDetailObserver extends NetworkAwareObserver<VideoDetail> {
        @Override
        public void onCompleted() {
            Log.d("InternalVideoDetailObserver", "InternalVideoDetailObserver.onCompleted()");
        }

        @Override
        public void onNext(VideoDetail videoDetail) {
            Log.d("InternalVideoDetailObserver", "InternalVideoDetailObserver.onNext()");
            //add results to LRU cache
            mVideoLRUCache.put(videoDetail.code, videoDetail);
            //add results to database
            DBHelper.insertVideoDetail(mContext, videoDetail);
            EventBus.getDefault().post(new VideoDetailResponse(videoDetail));

        }
    }
    ///
}

