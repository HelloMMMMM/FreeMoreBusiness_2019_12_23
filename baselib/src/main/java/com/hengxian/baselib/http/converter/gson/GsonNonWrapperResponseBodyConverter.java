package com.hengxian.baselib.http.converter.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.hengxian.baselib.bean.ResponseBean;
import com.hengxian.baselib.http.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * 非包装返回类型解析
 *
 * @param <T>
 */
public class GsonNonWrapperResponseBodyConverter<T> implements Converter<ResponseBody, T>
{
    private static final TypeToken<ResponseBean> WRAPPER_TYPE = TypeToken.get(ResponseBean.class);

    private final Gson gson;
    private final TypeAdapter<T> adapter;
    private final TypeAdapter<ResponseBean> errorAdapter;

    GsonNonWrapperResponseBodyConverter(Gson gson, TypeAdapter<T> adapter)
    {
        this.gson = gson;
        this.adapter = adapter;
        errorAdapter = gson.getAdapter(WRAPPER_TYPE);
    }

    @Override
    public T convert(ResponseBody value) throws IOException
    {
        String response = value.string();
        if ("".equals(response))
        {
            response = "{}";
        } else
        {
            try
            {
                JSONObject jsonObject = new JSONObject(response);
                if (jsonObject.has("msg") && jsonObject.has("code"))
                {
                    if (ResponseBean.SUCCESS == jsonObject.getInt("code"))
                    {
                        return adapter.fromJson(response);
                    }
                    throw new ApiException()
                            .setDescription(jsonObject.getString("msg"));
                }
            } catch (JSONException e)
            {
                throw new ApiException(e);
            }
        }
        T t ;
        try
        {
            t = adapter.fromJson(response);
        } catch (Exception e)
        {
            throw new ApiException(e);
        }
        return t;
    }
}
