package com.globallogic.test.service;

import com.globallogic.test.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class HandleErrorService {
    
        public ErrorResponseDto handleError(Exception e) {
            int code = HttpStatus.BAD_REQUEST.value();
            String detail = e.getMessage();
            return new ErrorResponseDto(code, detail);
        }
}
