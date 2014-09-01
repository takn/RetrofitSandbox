package com.studio1r.retrofitsandbox.api.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Arrays;

public class VideoDetail extends FeedItem implements Serializable {

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
    public Tag[] tags;
    public Image images;
    @SerializedName("play_urls")
    public PlayUrl playUrls;

    public VideoDetail() {
    }

    @Override
    public String toString() {
        return "VideoDetail{" +
                "auditudeVideoId='" + auditudeVideoId + '\'' +
                ", title='" + title + '\'' +
                ", showName='" + showName + '\'' +
                ", showNameInternal='" + showNameInternal + '\'' +
                ", episode='" + episode + '\'' +
                ", duration='" + duration + '\'' +
                ", viewCount='" + viewCount + '\'' +
                ", originalPublishedAt='" + originalPublishedAt + '\'' +
                ", description='" + description + '\'' +
                ", uploadedAt=" + uploadedAt +
                ", exclusive=" + exclusive +
                ", tags=" + Arrays.toString(tags) +
                ", images=" + images +
                ", playUrls=" + playUrls +
                '}';
    }
}
