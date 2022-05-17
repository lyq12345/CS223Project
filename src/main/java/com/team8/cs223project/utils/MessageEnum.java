package com.team8.cs223project.utils;

import lombok.Getter;

@Getter
public enum MessageEnum {

    ABORT(-1),
    READ(0),
    WRITE(1),
    COMMIT(2);

    private Integer code;
    private MessageEnum(Integer code) {
        this.code = code;
    }
}
