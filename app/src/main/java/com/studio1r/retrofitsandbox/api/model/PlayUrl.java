package com.studio1r.retrofitsandbox.api.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PlayUrl implements Serializable {

	public static final long serialVersionUID = 1L;

	@SerializedName("play_url_lo")
	public String playUrlLo;
	@SerializedName("play_url_hi")
	public String playUrlHi;
	@SerializedName("play_url_hls")
	public String playUrlHls;

}
