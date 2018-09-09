package br.com.cortes.will.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ComicList implements Serializable {
    @SerializedName("available")
    private int mAvailable;

    @SerializedName("returned")
    private int mReturned;

    @SerializedName("collectionURI")
    private String mCollectionURI;

    @SerializedName("items")
    private List<ComicSummary> mItems;

    public List<ComicSummary> getItems() {
        return mItems;
    }
}
