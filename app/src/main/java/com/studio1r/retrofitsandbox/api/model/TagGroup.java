package com.studio1r.retrofitsandbox.api.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TagGroup extends MKRBaseModel implements Serializable {

    public static final long serialVersionUID = 1L;

    public String heading;
    public String description;
    @SerializedName("image_url")
    public String imageUrl;
    public Tag[] tags;
    @SerializedName("category_style")
    public String categoryStyle;


}
