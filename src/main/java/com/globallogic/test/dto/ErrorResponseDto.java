package com.globallogic.test.dto;

import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseDto {
    private List<ErrorDetails> error;

    public ErrorResponseDto(int code, String detail) {
         this.error = Collections.singletonList(new ErrorDetails(code, detail));
    }
}