
package com.ragentek.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.ragentek.application.TranslateApplication;

/**
 * Created by Jun.wang on 2018/9/17.
 */
public class SPManager {

    private static SharedPreferences sp = null;
    private static SPManager spManager = null;
    private static Editor editor = null;
    /**
     * Preference文件名
     */
    private static final String SHARE_PREFREENCE_NAME = "translate.pre";

    /**
     * 上次基金更新时间
     */
    public static final String CURRENT_COUNTRY = "current_country";

    private SPManager() {
        sp = TranslateApplication.getInstance().getSharedPreferences(SHARE_PREFREENCE_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public static SPManager getInstance() {
        if (spManager == null || sp == null || editor == null) {
            spManager = new SPManager();
        }
        return spManager;
    }

    public void putInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public int getInt(String key, int defaultValue) {
        return sp.getInt(key, defaultValue);
    }

    public void putLong(String key, Long value) {
        editor.putLong(key, value);
        editor.commit();
    }

    public long getLong(String key, int defaultValue) {
        return sp.getLong(key, defaultValue);
    }

    public void putString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key, String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    public void putFloat(String key, float value) {
        editor.putFloat(key, value);
        editor.commit();
    }

    public boolean isKeyExist(String key) {
        return sp.contains(key);
    }

    public float getFloat(String key, float defaultValue) {
        return sp.getFloat(key, defaultValue);
    }

    public void putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    public void remove(String key) {
        editor.remove(key);
        editor.commit();
    }
}
