package com.studio1r.retrofitsandbox.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Tag implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String internal;
    private String heading;
    private String description;
	@SerializedName("image_url")
	private String imageUrl;
	@SerializedName("banner_url")
	private String bannerUrl;


    public Tag() {
    }
}
