package com.studio1r.retrofitsandbox.api;

import android.content.Context;
import android.util.Log;

import com.studio1r.retrofitsandbox.Constants;
import com.studio1r.retrofitsandbox.Observers.NetworkAwareObserver;
import com.studio1r.retrofitsandbox.api.model.VideoDetail;
import com.studio1r.retrofitsandbox.api.requests.VideoDetailRequest;
import com.studio1r.retrofitsandbox.api.responses.VideoDetailResponse;
import com.studio1r.retrofitsandbox.api.video.VideoDetailApiClient;

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
    private Context mContext;
    private VideoDetailApiClient mVidDetailClient;

    public MKRApi(Context context) {
        mContext = context;
        EventBus.getDefault().register(this);
    }

    public void destroy() {
        EventBus.getDefault().unregister(this);
        mContext = null;
    }

    public void onEventMainThread(VideoDetailRequest request) {
        Log.d(TAG, "Detail request received with id:" + request.id);

        if (mVidDetailClient == null) {
            //use mock or real here
            //TODO check memory cache
            //TODO check database
            if (Constants.USE_MOCK_DATA) {
                try {
                    mVidDetailClient = new VideoDetailApiClient(mContext);
                    mVidDetailClient.getVideoDetail(request.id)
                            .subscribeOn(Schedulers.io())
                            .observeOn(Schedulers.computation())
                            .subscribe(new InternalVideoDetailObserver());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
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
    public static class InternalVideoDetailObserver extends NetworkAwareObserver<VideoDetail> {
        @Override
        public void onCompleted() {
            Log.d("InternalVideoDetailObserver", "InternalVideoDetailObserver.onCompleted()");
        }

        @Override
        public void onNext(VideoDetail videoDetail) {
            Log.d("InternalVideoDetailObserver", "InternalVideoDetailObserver.onNext()");
            //TODO add to database
            EventBus.getDefault().post(new VideoDetailResponse(videoDetail));

        }
    }
    ///
}

