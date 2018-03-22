package com.hao.lib.rx.gson;

import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.hao.lib.bean.HttpResult;
import com.hao.lib.rx.HttpCode;
import com.hao.lib.rx.exception.ApiException;
import com.hao.lib.rx.exception.TokenInvalidException;
import com.socks.library.KLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, Object> {

    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override
    public Object convert(ResponseBody value) {
        try {
            String json = new String(value.bytes());
            KLog.json("json----------", json);
            HttpResult result = (HttpResult) adapter.fromJson(json);
            if (result == null) {
                throw new ApiException(HttpCode.CODE_10031.getCode());

            } else if (result.isOk()) {
                return result.getResult().getData();
            } else {
                throw new ApiException(result.getError_code() + "");
            }
        } catch (IOException | JsonSyntaxException e) {
            throw new ApiException(HttpCode.CODE_10031.getCode());
        } finally {
            if (value != null) {
                value.close();
            }
        }
    }
}
