package com.pickcle.picklework.http.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pickcle.picklework.http.ResponseCode;
import com.pickcle.picklework.http.api.BaseResultEntity;
import com.pickcle.picklework.http.exception.CodeException;
import com.pickcle.picklework.http.exception.CodeFailException;
import com.pickcle.picklework.http.exception.EmptyException;
import com.pickcle.picklework.http.exception.HttpTimeException;
import com.pickcle.picklework.http.exception.ServerException;
import com.pickcle.picklework.http.exception.SpecialException;
import com.pickcle.picklework.http.exception.TokenException;
import com.pickcle.picklework.util.LogUtil;
import com.justcan.library.utils.common.StringUtils;


import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;
import retrofit2.Converter;

/**
 * Created by justcan on 2018/1/10.
 */

public class FastJsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Type type;


    public FastJsonResponseBodyConverter(Type type) {
        this.type = type;
    }

    /*
    * 转换方法
    */
    @Override
    public T convert(ResponseBody value) throws IOException {
        BufferedSource bufferedSource = Okio.buffer(value.source());
        String tempStr = bufferedSource.readUtf8();
        bufferedSource.close();

        LogUtil.e("请求返回数据\n", tempStr);

        if (StringUtils.isEmpty(tempStr)) {
            throw new ServerException("");
        } else {

            BaseResultEntity baseResultEntity = JSONObject.parseObject(tempStr, BaseResultEntity.class);
            if (baseResultEntity != null) {
                switch (baseResultEntity.getReturnCode()) {
                    case ResponseCode.OPERATE_FAILURE:
                        throw new CodeFailException(baseResultEntity.getReturnMsg());
                    case ResponseCode.OPERATE_SUCCESS:
                        if (StringUtils.isEmpty(baseResultEntity.getContent()) || StringUtils.isEmpty(baseResultEntity.getContent().replace("{}", ""))) {
                            throw new EmptyException(baseResultEntity.getReturnMsg());
                        } else {
                            return JSON.parseObject(baseResultEntity.getContent(), type);
                        }

                    case ResponseCode.TOKEN_EMPTY:
                    case ResponseCode.TOKEN_INVALID:
                        throw new TokenException();

                    case ResponseCode.TIMESTAMP_NOT_MATCH:
                        //时间戳不匹配


                    case ResponseCode.REQUEST_EMPTY:
                    case ResponseCode.REQUEST_MORE:
                    case ResponseCode.PARAM_NOT_MATCH:
                        throw new HttpTimeException(baseResultEntity.getReturnMsg());
                    case ResponseCode.SERIAL_NUMBER_REPEAT:
                    case ResponseCode.MOTION_PLAN_EXIST:
                    case ResponseCode.MOTION_PLAN_OUT_OF_DATE:
                    case ResponseCode.USER_LOCK:
                        throw new SpecialException(baseResultEntity.getReturnCode());
                    case ResponseCode.MOBILE_NO_BIND:

                        return JSON.parseObject(tempStr, type);
                    default:
                        throw new CodeException(baseResultEntity.getReturnMsg());
                }
            }
        }
        return null;
    }
}