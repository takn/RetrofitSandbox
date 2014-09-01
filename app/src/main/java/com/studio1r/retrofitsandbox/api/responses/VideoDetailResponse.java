package com.studio1r.retrofitsandbox.api.responses;

import com.studio1r.retrofitsandbox.api.model.VideoDetail;

/**
 * Created by nelsonramirez on 9/1/14.
 */
public class VideoDetailResponse {
    public final VideoDetail data;

    public VideoDetailResponse(VideoDetail data) {
        this.data = data;
    }
}
