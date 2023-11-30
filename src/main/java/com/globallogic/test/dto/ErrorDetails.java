package com.globallogic.test.dto;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDetails {
    private Timestamp timestamp;
    private int code;
    private String detail;

    public ErrorDetails(int code, String detail) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.code = code;
        this.detail = detail;
    }
}