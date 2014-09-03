package com.studio1r.retrofitsandbox.api.responses;

/**
 * Created by nelsonramirez on 9/3/14.
 */
public class BaseResponse<T> {
    public T data;

    public T getData() {
        return data;
    }

    public BaseResponse(T data) {
        this.data = data;
    }
}
