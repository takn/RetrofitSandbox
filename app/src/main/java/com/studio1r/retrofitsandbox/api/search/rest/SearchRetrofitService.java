package com.studio1r.retrofitsandbox.api.search.rest;

import android.database.Observable;

import com.studio1r.retrofitsandbox.api.search.model.SearchResult;

import java.util.Map;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.QueryMap;

/**
 * Created by nelsonramirez on 8/29/14.
 */
public interface SearchRetrofitService {

    /**
     * @param searchTerm - the query to be performed
     * @param filters -
     * @return Obvservable search body
     * //http://devmobileapi.makerstudios.com/v1/{sitecode}/
     * search?q={search_term}&order={sort_order}
     * // [&startat={star_iIndex}][&limitto={limit}]
     */
    @GET("/search?q={search_term}")
    Observable<SearchResult> performSearch(@Path("search_term") String searchTerm,
                                     @QueryMap Map<String, String> filters);
}


