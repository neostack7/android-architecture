package com.example.gateway.framework.network;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import retrofit.client.Response;
import retrofit.mime.TypedInput;

/**
 *
 */
public abstract class RestService implements IService {

    protected Set<OnRequestFinishedListener> listeners = new HashSet<>();

    @Override
    public void addServiceListener(OnRequestFinishedListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeServiceListener(OnRequestFinishedListener listener) {
        if (listeners.contains(listener)) {
            listeners.remove(listener);
        }
    }

    protected String getStringFromTypedResponse(Response response) throws IOException {
        TypedInput typedInput = response.getBody();
        InputStream in = null;
        String result = null;
        try {
            in = typedInput.in();
            byte[] bytes = new byte[(int) typedInput.length()];
            if(typedInput.in().read(bytes) == typedInput.length()) {
                result = new String(bytes);
            }
            else {
                throw new IOException("Error getting bytes from response input stream: some bytes not recovered.");
            }
        }
        catch(IOException e) {
            throw e;
        }
        finally {
            try {
                if(in != null) {
                    in.close();
                }
            }
            catch(IOException e) {
                throw e;
            }
        }

        return result;
    }

}
