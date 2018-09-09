package br.com.cortes.will.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Character implements Serializable {
    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("modified")
    private Date mModified;

    @SerializedName("resourceURI")
    private String mResourceURI;

    @SerializedName("urls")
    private List<Url> mUrls;

    @SerializedName("thumbnail")
    private Thumbnail mThumbnail;

    @SerializedName("comics")
    private ComicList mComics;

    @SerializedName("stories")
    private ComicList mStories;

    @SerializedName("events")
    private ComicList mEvents;

    @SerializedName("series")
    private ComicList mSeries;

    public String getName() {
        return mName;
    }

    public Thumbnail getThumbnail() {
        return mThumbnail;
    }

    public ComicList getComics() {
        return mComics;
    }
}
