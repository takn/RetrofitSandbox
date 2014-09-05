package com.studio1r.retrofitsandbox.util;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by nelsonramirez on 9/4/14.
 */
public class DataUtils {
    /**
     * UTIL METHODS
     */

    public static String toJson(Serializable s) {
        Gson gson = new Gson();
        return gson.toJson(s);

    }

    /**
     * Convert string to given type
     *
     * @return instance of type
     */
    public static final <V> V fromJson(String json, Class<V> type) {
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }
}
