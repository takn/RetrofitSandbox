package com.studio1r.retrofitsandbox.api.responses;

/**
 * Created by nelsonramirez on 9/1/14.
 */
public class UserFeedResponse<T> extends BaseResponse {
    public UserFeedResponse(T data) {
        super(data);
    }
}
