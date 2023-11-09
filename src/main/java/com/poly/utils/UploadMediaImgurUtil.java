package com.poly.utils;

import okhttp3.*;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public class UploadMediaImgurUtil {
  private final OkHttpClient client;

  public UploadMediaImgurUtil() {
    this.client = new OkHttpClient();
  }

  public void upload(File file) {
    try {  
    String imgurClientID = "e7d5e6ad5392098";
      String fieldName = "image";
      String fileName = file.getName();
      MediaType mediaType = MediaType.parse("image/*");

    RequestBody requestBody = new MultipartBody.Builder()
        .setType(MultipartBody.FORM)
        .addFormDataPart(fieldName, fileName, RequestBody.create(file, mediaType))
        .addFormDataPart("photo", fileName, RequestBody.create(file, mediaType))
        .addFormDataPart("ablum", "https://imgur.com/gallery/Ovpq9pC")
        .build();

    Request request = new Request.Builder()
        .header("Authorization", "Client-ID " + imgurClientID)
        .url("https://api.imgur.com/3/image")
        .post(requestBody)
        .build();

     Response response = client.newCall(request).execute();
      if (!response.isSuccessful()) {
        throw new IOException("Unexpected code " + response);
      }

      System.out.println(response.body().string());
    } catch (Exception e) {
        e.printStackTrace();
    }
  }
}
