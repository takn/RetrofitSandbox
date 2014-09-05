package com.studio1r.retrofitsandbox.api.clients;

import android.content.Context;
import android.database.Observable;

import com.studio1r.retrofitsandbox.api.APIConfiguration;
import com.studio1r.retrofitsandbox.api.model.Search;

import java.util.Map;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.QueryMap;

/**
 * Created by nelsonramirez on 8/29/14.
 */
public class SearchApiClient {

    public interface SearchRetrofitService {

        /**
         * @param searchTerm - the query to be performed
         * @param filters    - Filtering options. Supported options are:
         *                   order - asc,desc
         *                   startat - int
         *                   lmitto - int
         * @return Obvservable Search search body
         * <p/>
         * EXAMPLE endpoint
         * //http://devmobileapi.makerstudios.com/v1/{sitecode}/
         * search?q={search_term}&order={sort_order}
         * // [&startat={star_iIndex}][&limitto={limit}]
         */
        @GET("/search?q={search_term}")
        Observable<Search> performSearch(@Path("search_term") String searchTerm,
                                         @QueryMap Map<String, String> filters);
    }

    private final SearchRetrofitService mSearchClient;

    public SearchApiClient(Context context) {

        mSearchClient = APIConfiguration.getRestAdapter(context).create(
                SearchRetrofitService.class);
    }

    /**
     * @param searchTerm - the search term. no validation is done on this string!
     * @param options    -filtering options for the search
     * @return
     */
    public Observable<Search> performSearch(String searchTerm, Map<String, String> options) {
        return mSearchClient.performSearch(searchTerm, options);
    }

}
