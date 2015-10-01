package com.example.gateway.framework.repository;

/**
 *
 */
public interface IPreferenceStorage {

    void saveStringValue(String key, String value);

    String getStringValue(String key);

    void saveBooleanValue(String key, boolean value);

    boolean getBooleanValue(String key);

    void saveIntegerValue(String key, int value);

    int getIntegerValue(String key);

    void saveFloatValue(String key, float value);

    float getFloatValue(String key);

    void clearPreference(String key);

    void clearAllPreferences();
}
