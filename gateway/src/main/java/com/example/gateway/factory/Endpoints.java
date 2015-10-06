package com.example.gateway.factory;


import com.example.gateway.models.Credential;
import com.example.gateway.models.FileUploadResponse;
import com.example.gateway.models.LoginInfo;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.mime.TypedFile;

/**
 * REST api call interface.
 */
public interface Endpoints {

    @DELETE("/userAccount/deleteUserData")
    void executeDeleteUserRequest(Callback<Response> callback);

    @POST("/usersession/ssoLoginWithNameAndPass")
    void executeLoginRequest(@Body Credential credential, Callback<LoginInfo> callback);

    @GET("/sriaudio/audiosamples/{filename}")
    void downloadFile(@Path("filename") String filename, Callback<Response> callback);

    @POST("/files/{filename}")
    void uploadPdf(@Path("filename") String filename, @Body TypedFile file,Callback<FileUploadResponse> callback);

    @Multipart
    @POST("/api/web/SRIAction")
    void uploadAudio(@Part("GrammarKey") String filename, @Part("audio") TypedFile file,Callback<FileUploadResponse> callback);
}
