package com.studio1r.retrofitsandbox.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;
import com.studio1r.retrofitsandbox.api.model.MKRBaseModel;
import com.studio1r.retrofitsandbox.api.model.VideoDetailItem;
import com.studio1r.retrofitsandbox.db.contracts.VideoDetailContract;
import com.studio1r.retrofitsandbox.util.DataUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

/**
 * Created by nelsonramirez on 9/2/14.
 */
public class DBHelper {

    private static final String TAG = "DBHelper";


    public static void insertVideoDetail(Context context, VideoDetailItem video) {
        //no need for update logic. Provigen Constraint was defined in DBHelper
        context.getContentResolver().insert(VideoDetailContract.CONTENT_URI,
                VideoDetailContract.getContentValues(video));
    }

    //TODO these methods can most likely be generic

    /**
     * Provides a generic way to insert models into the given Uri
     *
     * @param context
     * @param uri     -
     * @param data    all Models sublcass MKRBaseModel and implement necessary methods to serialize
     */
    private static void insertDataToUri(Context context, Uri uri, MKRBaseModel data) {

    }


    public static VideoDetailItem getVideoDetail(Context context, String id) {
        Cursor cursor = context.getContentResolver().query(VideoDetailContract.CONTENT_URI,
                null, VideoDetailContract.CODE + " = ? ", new String[]{id}, "");
        if (cursor != null && cursor.moveToFirst()) {
            VideoDetailItem detail = DataUtils.fromJson(cursor.getString(
                            cursor.getColumnIndex(VideoDetailContract.SERIALIZED_OBJECT)),
                    VideoDetailItem.class);
            cursor.close();
            return detail;
        }
        return null;

    }

    /**
     * Instert a list of videoDetails into the DB
     *
     * @param mContext
     * @param videoDetailItemList
     */
    public static void bulkVideoInsert(Context mContext, List<VideoDetailItem> videoDetailItemList) {
        ContentValues[] cVs = new ContentValues[videoDetailItemList.size()];
        for (int i = 0; i < videoDetailItemList.size(); i++) {
            VideoDetailItem videoDetailItem = videoDetailItemList.get(i);
            cVs[i] = VideoDetailContract.getContentValues(videoDetailItem);
        }
        mContext.getContentResolver().bulkInsert(VideoDetailContract.CONTENT_URI, cVs);
    }


}
