package com.example.gateway.login;

import com.example.gateway.framework.network.IService;
import com.example.gateway.models.Credential;

public interface ILoginService extends IService {

    void doLoginRequest(Credential credential);
}
