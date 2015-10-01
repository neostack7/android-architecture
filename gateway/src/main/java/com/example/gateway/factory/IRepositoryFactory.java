package com.example.gateway.factory;


import com.example.gateway.login.ILoginRepository;
import com.example.gateway.playaudio.IAudioFileRepository;

import io.realm.Realm;

/**
 *
 */
public interface IRepositoryFactory {

    ILoginRepository getLoginRepository();

    Realm getDefaultRealm();
    Realm getInMemoryRealm();
    void closeAllRealms();

    IAudioFileRepository getFileRepository();
}
