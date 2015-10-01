package com.example.gateway.framework.repository;

import java.io.InputStream;

/**
 *
 */
public interface IFileStorage {
    void saveFile(String filename, InputStream inputStream);

    void delete(String filename);
}
