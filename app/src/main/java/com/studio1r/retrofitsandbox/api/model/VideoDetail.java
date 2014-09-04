package com.studio1r.retrofitsandbox.api.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class VideoDetail extends Item implements Serializable {

    public static final long serialVersionUID = 1L;

    @SerializedName("auditude_video_id")
    public String auditudeVideoId;
    public String title;
    @SerializedName("show_name")
    public String showName;
    @SerializedName("show_name_internal")
    public String showNameInternal;
    public String episode;
    public String duration;
    @SerializedName("view_count")
    public String viewCount;
    @SerializedName("original_published_at")
    public String originalPublishedAt;
    public String description;
    @SerializedName("uploaded_at")
    public long uploadedAt;
    public int exclusive;
    public ArrayList<Tag> tags;
    public Image images;
    @SerializedName("play_urls")
    public PlayUrl playUrls;

    public VideoDetail() {
    }

}
