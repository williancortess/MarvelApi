package br.com.cortes.will.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Url implements Serializable {
    @SerializedName("type")
    public String mType;

    @SerializedName("url")
    public String mUrl;
}
