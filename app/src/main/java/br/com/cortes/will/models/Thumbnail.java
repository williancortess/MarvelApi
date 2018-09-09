package br.com.cortes.will.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Thumbnail implements Serializable {
    @SerializedName("path")
    private String mPath;

    @SerializedName("extension")
    private String mExtension;

    public String getmPath() {
        return mPath;
    }

    public String getmExtension() {
        return mExtension;
    }

    public String getUrl() {
        return getmPath() + "." + getmExtension();
    }
}
