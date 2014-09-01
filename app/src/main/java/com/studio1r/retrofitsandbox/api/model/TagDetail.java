package com.studio1r.retrofitsandbox.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TagDetail implements Serializable, Parcelable {

	private static final long serialVersionUID = 1L;
	
	@SerializedName("item_groups")
	private ItemGroup[] itemGroups;

	public ItemGroup[] getItemGroups() {
		return itemGroups;
	}
	public void setItemGroups(ItemGroup[] itemGroups) {
		this.itemGroups = itemGroups;
	}

    public TagDetail() {
    }

    protected TagDetail(Parcel in) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<TagDetail> CREATOR = new Parcelable.Creator<TagDetail>() {
        @Override
        public TagDetail createFromParcel(Parcel in) {
            return new TagDetail(in);
        }

        @Override
        public TagDetail[] newArray(int size) {
            return new TagDetail[size];
        }
    };
}
