package com.pickcle.picklework.http.api;

import com.pickcle.picklework.http.listener.HttpOnNextListener;
import com.pickcle.picklework.http.utils.AesUtils;
import com.pickcle.picklework.util.LogUtil;
import com.google.gson.Gson;
import com.justcan.library.activity.RxAppActivity;
import com.justcan.library.activity.RxFragmentActivity;
import com.justcan.library.utils.common.StringUtils;

import java.io.File;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by justcan on 2017/12/1.
 */

public abstract class PostApi extends BaseApi {
    //参数类型
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("image/png");

    private RequestBody requestBody;
    private MultipartBody multipartBody;

    public PostApi(HttpOnNextListener listener) {
        super(listener);
    }

    public PostApi(HttpOnNextListener listener, RxAppActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
    }

    public PostApi(HttpOnNextListener listener, RxFragmentActivity rxFragmentActivity) {
        super(listener, rxFragmentActivity);
    }

    /**
     * 明文发送
     *
     * @param body
     */
    public void addRequstBody(Object body) {

        if (body != null) {
            String jsonStr = new Gson().toJson(body);
            RequestBody requestBody = RequestBody.create(MEDIA_TYPE_JSON, jsonStr);
            setRequestBody(requestBody);

            LogUtil.e("request------->", jsonStr);
        }

    }

    /**
     * 明文发送
     *
     * @param jsonbody
     */
    public void addRequstBody(String jsonbody) {

        if (!StringUtils.isEmpty(jsonbody)) {

            RequestBody requestBody = RequestBody.create(MEDIA_TYPE_JSON, jsonbody);
            setRequestBody(requestBody);

            LogUtil.e("request", jsonbody);
        }
    }


    /**
     * 加密发送
     */
    public void addEncryptRequestBody(Object body) {

        try {
            String jsonStr = new Gson().toJson(body);
            RequestBody requestBody = RequestBody.create(MEDIA_TYPE_JSON, AesUtils.encryptBase64(jsonStr, AesUtils.KEY));
            setRequestBody(requestBody);

            LogUtil.e("request---->", jsonStr);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 多文件上传
     */
    public void addFilesRequestBody(String userId, String content, List<File> files) {
        if (!StringUtils.isEmpty(userId)) {
            MultipartBody.Builder builder = new MultipartBody.Builder();

            builder.addFormDataPart("userId", userId);
            builder.addFormDataPart("content", content);

            if (files != null && files.size() > 0) {
                for (File file : files) {
                    if (file != null && file.exists()) {
                        builder.addFormDataPart("pictureFile", "pictureFile", RequestBody.create(MEDIA_TYPE_PNG, file));
                    }
                }
            }
            setMultipartBody(builder.build());
        }
    }

    /**
     * 多文件，多字段上传
     */
    public void addFilesRequestBody(Map<String, String> maps, List<File> files) {

        MultipartBody.Builder builder = new MultipartBody.Builder();

        for (Map.Entry<String, String> entry : maps.entrySet()) {
            if (!StringUtils.isEmpty(entry.getValue())) {
                builder.addFormDataPart(entry.getKey(), entry.getValue());
            }
        }

        if (files != null && files.size() > 0) {
            for (File file : files) {
                builder.addFormDataPart("pictures", file.getName(), RequestBody.create(MEDIA_TYPE_PNG, file));
            }
        }

        setMultipartBody(builder.build());
    }

    public void addFilesRequestBodyFeekback(Map<String, String> maps, List<File> files) {

        MultipartBody.Builder builder = new MultipartBody.Builder();

        for (Map.Entry<String, String> entry : maps.entrySet()) {
            if (!StringUtils.isEmpty(entry.getValue())) {
                builder.addFormDataPart(entry.getKey(), entry.getValue());
            }
        }

        if (files != null && files.size() > 0) {
            for (File file : files) {
                builder.addFormDataPart("picture", "picture", RequestBody.create(MEDIA_TYPE_PNG, file));
            }
        }

        setMultipartBody(builder.build());
    }

    /**
     * 多文件，多字段上传
     */
    public void addFilesRequestBody(Map<String, String> maps, Map<String, File> files) {

        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (Map.Entry<String, String> entry : maps.entrySet()) {
            builder.addFormDataPart(entry.getKey(), entry.getValue());
        }

        if (files != null && files.size() > 0) {

            for (Map.Entry<String, File> entry : files.entrySet()) {
                builder.addFormDataPart(entry.getKey(), entry.getKey(), RequestBody.create(MEDIA_TYPE_PNG, entry.getValue()));
            }
        }

        setMultipartBody(builder.build());
    }

    /**
     * 单文件上传
     */
    public void addFileRequestBody(File file) {
        if (file != null && file.exists()) {
            MultipartBody.Builder builder = new MultipartBody.Builder();


            if (file != null && file.exists()) {
                builder.addFormDataPart("pictureFile", "pictureFile", RequestBody.create(MEDIA_TYPE_PNG, file));
            }
            setMultipartBody(builder.build());
        }
    }

    /**
     * 图文混传
     */
    public void addRequstBodyAndPic(Object body, File file) {

        if (body != null) {
            MultipartBody.Builder builder = new MultipartBody.Builder();

            String jsonStr = new Gson().toJson(body);

            builder.addFormDataPart("data", jsonStr);

            if (file != null && file.exists()) {
                builder.addFormDataPart("picture", "picture", RequestBody.create(MEDIA_TYPE_PNG, file));
            }
            setMultipartBody(builder.build());
        }
    }


    public RequestBody getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
    }

    public MultipartBody getMultipartBody() {
        return multipartBody;
    }

    public void setMultipartBody(MultipartBody multipartBody) {
        this.multipartBody = multipartBody;
    }
}
