package com.studio1r.retrofitsandbox.api.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TagDetail extends MKRBaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@SerializedName("item_groups")
	private ItemGroup[] itemGroups;
}
