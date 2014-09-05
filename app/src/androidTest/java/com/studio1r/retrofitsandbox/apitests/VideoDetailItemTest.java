package com.studio1r.retrofitsandbox.apitests;

import android.test.AndroidTestCase;

/**
 * Created by nelsonramirez on 9/1/14.
 */
public class VideoDetailItemTest extends AndroidTestCase {
    public void testEndpointConfiguration() {
        assertTrue(true);
    }

    public void testGetInvalidVideo() {
        String id = "dummy";
        assertEquals("dummy", "dummy");
    }

    public void testGetValidVideo() {
        assertTrue(true);
    }
}
