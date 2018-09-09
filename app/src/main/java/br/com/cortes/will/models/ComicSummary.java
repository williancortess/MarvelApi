package br.com.cortes.will.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ComicSummary implements Serializable {
    @SerializedName("resourceURI")
    private String mResourceURI;

    @SerializedName("name")
    private String mName;

    @SerializedName("type")
    private String mType;

    public String getName() {
        return mName;
    }
}
