package com.pickcle.picklework.http.api;

import com.pickcle.picklework.http.listener.HttpOnNextListener;
import com.google.gson.Gson;
import com.justcan.library.activity.RxAppActivity;

import java.io.File;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by justcan on 2017/12/1.
 */

public abstract class UploadApi extends BaseApi {
    private MultipartBody.Builder builder;

    public UploadApi(HttpOnNextListener listener, RxAppActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
    }

    /**
     * 单文件上传
     */
    public void addFileRequestBody(File file) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.addFormDataPart("pictureFile", file.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file));

        setBuilder(builder);

    }

    /**
     * 多文件上传
     */
    public void addFilesRequestBody(String content, List<File> files) {

        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.addFormDataPart("content", content);

        if (files != null && files.size() > 0) {
            for (File file : files) {
                if (file != null && file.exists()) {
                    builder.addFormDataPart("pictureFile", file.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file));
                }
            }
        }

        setBuilder(builder);

    }

    /**
     * 多文件，多字段上传
     */
    public void addFilesRequestBody(Map<String, Object> maps, List<File> files) {

        MultipartBody.Builder builder = new MultipartBody.Builder();

        for (Map.Entry<String, Object> entry : maps.entrySet()) {
            builder.addFormDataPart(entry.getKey(), String.valueOf(entry.getValue()));
        }

        if (files != null && files.size() > 0) {
            for (File file : files) {
                if (file != null && file.exists()) {
                    builder.addFormDataPart("pictureFile", file.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file));
                }
            }
        }

        setBuilder(builder);

    }

    /**
     * 多文件，多字段上传
     */
    public void addFilesRequestBody(Map<String, Object> maps, Map<String, File> files) {

        MultipartBody.Builder builder = new MultipartBody.Builder();

        for (Map.Entry<String, Object> entry : maps.entrySet()) {
            builder.addFormDataPart(entry.getKey(), String.valueOf(entry.getValue()));
        }

        if (files != null && files.size() > 0) {
            for (Map.Entry<String, File> entry : files.entrySet()) {
                builder.addFormDataPart(entry.getKey(), (entry.getValue()).getName(), RequestBody.create(MediaType.parse("image/jpeg"), entry.getValue()));
            }
        }

        setBuilder(builder);

    }

    /**
     * 图文混传
     */
    public void addRequstBodyAndPic(Object body, File file) {

        if (body != null) {
            String jsonStr = new Gson().toJson(body);

            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.addFormDataPart("data", jsonStr);
            builder.addFormDataPart("picture", file.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file));

            setBuilder(builder);

        }
    }

    public MultipartBody.Builder getBuilder() {
        return builder;
    }

    public void setBuilder(MultipartBody.Builder builder) {
        this.builder = builder;
    }

}
