/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

final class GsonWrapperResponseBodyConverter<T> implements Converter<ResponseBody, T>
{
    private static final TypeToken<ResponseBean> WRAPPER_TYPE = TypeToken.get(ResponseBean.class);
    private static final TypeToken<String> STRING_TYPE = TypeToken.get(String.class);

    private final Gson gson;
    private final TypeAdapter<T> adapter;
    private final TypeAdapter<ResponseBean> wrapperAdapter;
    private final TypeAdapter<String> stringAdapter;

    GsonWrapperResponseBodyConverter(Gson gson, TypeAdapter<T> adapter)
    {
        this.gson = gson;
        this.adapter = adapter;
        wrapperAdapter = gson.getAdapter(WRAPPER_TYPE);
        stringAdapter = gson.getAdapter(STRING_TYPE);
    }

    @Override
    public T convert(ResponseBody value) throws IOException
    {
        String response = value.string();
        ResponseBean responseBean = wrapperAdapter.fromJson(response);
        try
        {
            if (ResponseBean.isSuccess(responseBean))
            {
                if (adapter.equals(wrapperAdapter))
                {
                    return (T) responseBean;
                }
                JSONObject jsonObject = new JSONObject(response);
                if (adapter.equals(stringAdapter))
                {
                    return (T) jsonObject.getString("data");
                }
                return adapter.fromJson(jsonObject.getString("data"));
            } else
            {
                throw new ApiException(response)
                        .setCode(responseBean.getCode())
                        .setDescription(responseBean.getMsg());
            }
        } catch (JSONException e)
        {
            throw new ApiException(e);
        } finally
        {
            value.close();
        }
    }
}
