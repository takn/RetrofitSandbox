package com.studio1r.retrofitsandbox.api.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Group implements Serializable {

    public static final long serialVersionUID = 1L;

    public String name;
    public String internal;
    @SerializedName("group_type")
    public String groupType;
    @SerializedName("fixed_position")
    public String fixedPosition;
    public Section[] sections;
    @SerializedName("section_type")
    public String sectionType;
    @SerializedName("display_count")
    public String displayCount;
}
