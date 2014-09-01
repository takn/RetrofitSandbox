package com.studio1r.retrofitsandbox.api.requests;

/**
 * Posts EventBus based requests that are received and Executed by the
 * {@link com.studio1r.retrofitsandbox.api.MKRApi} MRKApi
 * Created by nelsonramirez on 9/1/14.
 */
public class VideoDetailRequest extends BaseDataRequest {
    public String id;

    /**
     * Request video details
     *
     * @param id the id of the video you want details for.
     */
    public VideoDetailRequest(String id) {
        this.id = id;
    }
}
