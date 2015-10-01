package com.example.gateway.factory;

import android.content.Context;

import com.example.gateway.login.ILoginRepository;
import com.example.gateway.login.LoginRepository;
import com.example.gateway.playaudio.AudioFileRepository;
import com.example.gateway.playaudio.IAudioFileRepository;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 *
 */
public class RepositoryFactory implements IRepositoryFactory {
    private Context context;
    private ILoginRepository loginRepository;
    private IAudioFileRepository audioRepository;

    private RealmConfiguration defaultRealmConfiguration;
    private RealmConfiguration inMemoryRealmConfiguration;

    public RepositoryFactory(Context context) {
        this.context = context;

        defaultRealmConfiguration= new RealmConfiguration.Builder(context).name("default.realm")
                .schemaVersion(1).build();

        inMemoryRealmConfiguration = new RealmConfiguration.Builder(context).inMemory().name
                ("memory")
                .build();
    }

    @Override
    public ILoginRepository getLoginRepository() {
        if (loginRepository == null) {
            loginRepository = new LoginRepository(context);
        }
        return loginRepository;
    }

    @Override
    public Realm getDefaultRealm() {
        return Realm.getInstance(defaultRealmConfiguration);
    }

    @Override
    public Realm getInMemoryRealm() {
        return Realm.getInstance(inMemoryRealmConfiguration);
    }

    @Override
    public void closeAllRealms() {
        getDefaultRealm().close();
        getInMemoryRealm().close();
    }

    @Override
    public IAudioFileRepository getFileRepository() {
        if(audioRepository == null) {
            audioRepository = new AudioFileRepository(context);
        }
        return audioRepository;
    }
}
