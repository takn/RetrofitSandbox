package com.studio1r.retrofitsandbox.api.search;

import android.database.Observable;


import com.studio1r.retrofitsandbox.api.APIConfiguration;
import com.studio1r.retrofitsandbox.api.search.model.SearchResult;
import com.studio1r.retrofitsandbox.api.search.rest.SearchRetrofitService;

import java.util.Map;

import retrofit.RestAdapter;

/**
 * Created by nelsonramirez on 8/29/14.
 */
public class SearchApiClient {
    private final SearchRetrofitService mSearchClient;

    public SearchApiClient() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(APIConfiguration.getEndpoint())
                .setRequestInterceptor(APIConfiguration.requestInterceptor)
                .build();

        restAdapter.setLogLevel(RestAdapter.LogLevel.BASIC);
        mSearchClient = restAdapter.create(
                SearchRetrofitService.class);
    }

    /**
     * @param searchTerm - the search term. no validation is done on this string!
     * @param options -filtering options for the search
     * @return
     */
    public Observable<SearchResult> performSearch(String searchTerm, Map<String, String> options) {
        return mSearchClient.performSearch(searchTerm, options);
    }

}
