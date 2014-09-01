package com.studio1r.retrofitsandbox.api;

import com.studio1r.retrofitsandbox.Constants;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import retrofit.RequestInterceptor;

public class APIConfiguration {

    private final static String salt = "yeahbaby";
    private final static String protocol = "http";
    //localhost:8081
    // devmobileapi.makerstudios.com
    // stagemobileapi.makerstudios.com
    // mobileapi.makerstudios.com
    //TODO host based on dev setting...
    private final static String host = "stagemobileapi.makerstudios.com";
    private final static String path = Constants.VERSION;
    public final static String sitecode = Constants.SITE_CODE;
    public final static String videoPrefix = "polarisgo.com/video/";

    //
    private static String endpoint;

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

    public static String genUrl(String command) {
        String url = protocol + "://" + host + "/" + path + "/" + sitecode + "/" + command;
        String auth = encode(url);
        if (command.contains("?")) {
            return url + "&authorization=" + auth;
        } else {
            return url + "?authorization=" + auth;
        }
    }

    public static String getEndpoint() {
        return protocol + "://" + host + "/" + path + "/" + sitecode;
    }

    public static RequestInterceptor requestInterceptor = new RequestInterceptor() {
        @Override
        public void intercept(RequestFacade request) {
            request.addEncodedQueryParam("authorization", encode(request.toString()));
        }
    };
}
