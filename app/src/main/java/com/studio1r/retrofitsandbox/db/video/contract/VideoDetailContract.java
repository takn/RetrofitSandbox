package com.studio1r.retrofitsandbox.db.video.contract;

import android.net.Uri;

import com.tjeannin.provigen.ProviGenBaseContract;
import com.tjeannin.provigen.annotation.ContentUri;

/**
 * Created by nelsonramirez on 9/1/14.
 */
//CHECKSTYLE:OFF
public class VideoDetailContract implements ProviGenBaseContract {
    @ContentUri
    Uri CONTENT_URI = Uri.parse("content://com.studio1r.retrofitsandbox/video");

    //define db columns
}
//CHECKSTYLE:ON
