package com.example.gateway.models;

import com.example.gateway.framework.network.IResponseData;
import com.google.gson.annotations.SerializedName;

/**
 * Created by PraveenKatha on 02/10/15.
 */
public class FileUploadResponse implements IResponseData {
    @SerializedName("ConfidenceScore")
    public int confidenceScore;
}
