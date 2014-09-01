package com.studio1r.retrofitsandbox.api.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FeedItem implements Serializable {

    public static final long serialVersionUID = 1L;

    public String code;
    @SerializedName("item_type")
    public String itemType;
    public String internal;

}
