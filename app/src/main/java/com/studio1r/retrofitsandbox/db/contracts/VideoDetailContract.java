package com.studio1r.retrofitsandbox.db.contracts;

import android.net.Uri;

import com.tjeannin.provigen.annotation.ContentUri;

/**
 * Created by nelsonramirez on 9/1/14.
 */
//CHECKSTYLE:OFF
public class VideoDetailContract extends MKRBaseContract {
    @ContentUri
    public static final Uri CONTENT_URI = Uri.parse("content://com.studio1r.retrofitsandbox/video");

}
//CHECKSTYLE:ON
