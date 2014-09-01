package com.studio1r.retrofitsandbox.Observers;

import android.util.Log;

import rx.Observer;

/**
 * Created by nelsonramirez on 9/1/14.
 */
public abstract class NetworkAwareObserver<T> implements Observer<T> {
    @Override
    public abstract void onCompleted();

    @Override
    public void onError(Throwable e) {
        //TODO network related error handling here
        Log.e("NETWORKAWARE", "Network aware observer error");
    }

    @Override
    public abstract void onNext(T t);
}
