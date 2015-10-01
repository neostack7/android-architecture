package com.example.gateway.framework.repository;

import android.content.Context;
import android.content.SharedPreferences;

/**
 *
 */
public class PreferenceStorage implements IPreferenceStorage {

    private static PreferenceStorage instance;

    private SharedPreferences sharedPreferences;

    private PreferenceStorage(Context context) {
        sharedPreferences = context.getSharedPreferences("PreferenceManager.db", Context.MODE_PRIVATE);
    }

    public static IPreferenceStorage getInstance(Context context) {
        if (instance == null) {
            synchronized (PreferenceStorage.class) {
                if (instance == null) {
                    instance = new PreferenceStorage(context);
                }
            }
        }
        return instance;
    }

    @Override
    public void saveStringValue(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    @Override
    public String getStringValue(String key) {
        return sharedPreferences.getString(key, null);
    }

    @Override
    public void saveBooleanValue(String key, boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    @Override
    public boolean getBooleanValue(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    @Override
    public void saveIntegerValue(String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    @Override
    public int getIntegerValue(String key) {
        return sharedPreferences.getInt(key, -1);
    }

    @Override
    public void saveFloatValue(String key, float value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    @Override
    public float getFloatValue(String key) {
        return sharedPreferences.getFloat(key, -1);
    }

    @Override
    public void clearPreference(String key) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }

    @Override
    public void clearAllPreferences() {
        sharedPreferences.edit().clear();
        sharedPreferences.edit().apply();
    }
}
