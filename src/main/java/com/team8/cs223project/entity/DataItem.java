package com.team8.cs223project.entity;

import lombok.Data;

@Data
public class DataItem {
    private String key;
    private Integer value;

    public DataItem(String key, Integer value) {
        this.key = key;
        this.value = value;
    }
}
