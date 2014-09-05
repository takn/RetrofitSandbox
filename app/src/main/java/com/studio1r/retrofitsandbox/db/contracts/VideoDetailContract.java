package com.studio1r.retrofitsandbox.db.contracts;

import android.content.ContentValues;
import android.net.Uri;

import com.studio1r.retrofitsandbox.api.model.VideoDetailItem;
import com.studio1r.retrofitsandbox.util.DataUtils;
import com.tjeannin.provigen.annotation.Column;
import com.tjeannin.provigen.annotation.ContentUri;

/**
 * Created by nelsonramirez on 9/1/14.
 */
//CHECKSTYLE:OFF
public class VideoDetailContract extends MKRBaseContract {
    @ContentUri
    public static final Uri CONTENT_URI = Uri.parse("content://com.studio1r.retrofitsandbox/video");
    @Column(Column.Type.TEXT)
    public static final String CODE = "_code";


    public static ContentValues getContentValues(VideoDetailItem video) {
        ContentValues cv = new ContentValues();
        cv.put(VideoDetailContract.INTERNAL, video.code);
        cv.put(VideoDetailContract.CODE, video.code);
        cv.put(VideoDetailContract.SERIALIZED_OBJECT, DataUtils.toJson(video));
        return cv;
    }

}
//CHECKSTYLE:ON
