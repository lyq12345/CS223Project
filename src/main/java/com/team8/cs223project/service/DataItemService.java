package com.team8.cs223project.service;

public interface DataItemService {
    Integer readItemValue(String key);

    void writeItemValue(String key, Integer value);
}
