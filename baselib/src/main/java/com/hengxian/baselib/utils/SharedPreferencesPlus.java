package com.hengxian.baselib.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import java.lang.reflect.Type;


/**
 * 帮助存取SharedPreferences,支持obj存取，obj是使用gson序列化的 <br/>
 */
public class SharedPreferencesPlus {

    private SharedPreferences sp;

    private Gson gson;

    public static SharedPreferencesPlus createDefault(Context context) {
        return createDefault(context, null);
    }

    public static SharedPreferencesPlus createDefault(Context context, Gson gson) {
        return new SharedPreferencesPlus(context, "global_sharedPreferences", gson);
    }

    public SharedPreferencesPlus(Context context, String name, Gson gson) {
        sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        this.gson = gson;
    }

    public String getString(String key) {
        return getString(key, "");
    }

    public String getString(String key, String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    public boolean putString(String key, String content) {
        return sp.edit().putString(key, content).commit();
    }

    public int getInt(String key, int defValue) {
        return sp.getInt(key, defValue);
    }

    public long getLong(String key, long defValue) {
        return sp.getLong(key, defValue);
    }

    public boolean putInt(String key, int value) {
        return sp.edit().putInt(key, value).commit();
    }

    public boolean putLong(String key, long value) {
        return sp.edit().putLong(key, value).commit();
    }

    public boolean remove(String key) {
        return sp.edit().remove(key).commit();
    }

    public boolean contains(String key) {
        return sp.contains(key);
    }

    public boolean putBoolean(String key, boolean value) {
        return sp.edit().putBoolean(key, value).commit();
    }

    public boolean getBoolean(String key, boolean defValue) {
        return sp.getBoolean(key, defValue);
    }

    /**
     * 保存对象,对象是以json字符串形式保存，如果对象是null，则保存为null <br/>
     * 保存对象，暂时不处理对象的class类型
     *
     * @param key
     * @param object
     * @return
     */
    public boolean putObject(String key, Object object) {
        if (gson == null) {
            Log.e(getClass().getSimpleName(), String.format("本实例不支持保存对象，保存 %s 失败。", key));
            return false;
        }
        String json = null;
        if (object != null) {
            try {
                json = gson.toJson(object);
            } catch (Exception e) {
                return false;
            }
        }

        return putString(key, json);
    }

    /**
     * 获取保存的对象，由于保存的时候是以json字符串保存，没有保存class类型，所以如果type与原先保存的对象的类型有冲突，结果返回是null
     *
     * @param key
     * @param type 对象的type，一般情况下是 object.class
     * @param <T>
     * @return
     */
    public <T> T getObject(String key, Type type) {

        T t = null;

        String string = getString(key);
        if (!TextUtils.isEmpty(string) && type != null) {
            try {
                t = gson.fromJson(string, type);
            } catch (Exception e) { // json解析出错
                String msg = String.format("get %s of %s fail", key, type);
            }
        }
        return t;
    }

    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        sp.registerOnSharedPreferenceChangeListener(listener);
    }

    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        sp.unregisterOnSharedPreferenceChangeListener(listener);
    }
}