package com.jd.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> implements Serializable {

    private int code;
    private String message;
    private T data;

    public CommonResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
