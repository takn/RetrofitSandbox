package com.studio1r.retrofitsandbox.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class TagCategories implements Serializable, Parcelable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String internal;
	private TagGroup[] groups;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInternal() {
		return internal;
	}
	public void setInternal(String internal) {
		this.internal = internal;
	}
	public TagGroup[] getGroups() {
		return groups;
	}
	public void setGroups(TagGroup[] groups) {
		this.groups = groups;
	}

    public TagCategories() {
    }

    protected TagCategories(Parcel in) {
        name = in.readString();
        internal = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(internal);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<TagCategories> CREATOR = new Parcelable.Creator<TagCategories>() {
        @Override
        public TagCategories createFromParcel(Parcel in) {
            return new TagCategories(in);
        }

        @Override
        public TagCategories[] newArray(int size) {
            return new TagCategories[size];
        }
    };
}
