package com.studio1r.retrofitsandbox.api;

import android.content.Context;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.studio1r.retrofitsandbox.Constants;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class APIConfiguration {

    private final static String salt = "yeahbaby";
    private final static String protocol = "http";
    //localhost:8081
    // devmobileapi.makerstudios.com
    // stagemobileapi.makerstudios.com
    // mobileapi.makerstudios.com
    //TODO host based on dev setting...
    private final static String host = "stagemobileapi.makerstudios.com";
    //    private final static String host = "localhost:8081";
    private final static String path = Constants.VERSION;
    public final static String sitecode = Constants.SITE_CODE;

    private final static int DATABASE_TTL_MILLIS = 1000 * 60 * 60; //1HR
    private final static int MEMCACHE_TTL_MILLIS = 1000 * 60 * 60; //1HR
    private static OkHttpClient sOkHttpClient;
    private static RestAdapter sRestAdapter;

    /**
     * Check if given timestamp should be expired in the database
     *
     * @param timestamp
     * @return true if expired | false if still good
     */
    public static boolean isDBEntryExpired(long timestamp) {
        return isExpired(DATABASE_TTL_MILLIS, timestamp);
    }

    /**
     * Check if given timestamp should be expired in memory cache
     *
     * @param timestamp
     * @return true if expired | false if still good
     */
    public static boolean isMemEntryExpired(long timestamp) {
        return isExpired(MEMCACHE_TTL_MILLIS, timestamp);
    }

    private static boolean isExpired(long ttl, long timestamp) {
        return (timestamp + ttl) < System.currentTimeMillis();
    }

    ;

    //
    public static String encode(String url) {
        return genHMAC(salt, url);
    }

    private static String genHMAC(String key, String data) {

        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] x = sha256_HMAC.doFinal(data.getBytes());

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < x.length; i++) {
                String hex = Integer.toHexString(0xFF & x[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (InvalidKeyException ike) {
            return null;
        } catch (NoSuchAlgorithmException nsae) {
            return null;
        }
    }

    public static String getEndpoint() {
        return protocol + "://" + host + "/" + path + "/" + sitecode;
    }

    /**
     * Returns a request adapter backed by a 200 entry cache.
     *
     * @param context
     * @return
     */
    public static RestAdapter getRestAdapter(Context context) {
        if (sOkHttpClient == null) {
            sOkHttpClient = new OkHttpClient();
            try {
                Cache cache = new Cache(new File(context.getCacheDir(), "mkrHttpCache"), 200);
                sOkHttpClient.setCache(cache);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (sRestAdapter == null) {
            sRestAdapter = new RestAdapter.Builder()
                    .setEndpoint(APIConfiguration.getEndpoint())
                    .setClient(new OkClient(sOkHttpClient))
                    .build();
            sRestAdapter.setLogLevel(RestAdapter.LogLevel.BASIC);
        }

        return sRestAdapter;
    }
}
