/*
 * @(#)TestHandleErrorService.java
 *
 * Copyright (c) BANCO DE CHILE (Chile). All rights reserved.
 *
 * All rights to this product are owned by BANCO DE CHILE and may only
 * be used under the terms of its associated license document. You may NOT
 * copy, modify, sublicense, or distribute this source file or portions of
 * it unless previously authorized in writing by BANCO DE CHILE.
 * In any event, this notice and the above copyright must always be included
 * verbatim with this file.
 */
package com.globallogic.test.service;

import com.globallogic.test.dto.ErrorDetails;
import com.globallogic.test.dto.ErrorResponseDto;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHandleErrorService {
    @InjectMocks
    private HandleErrorService handleErrorService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testHandleError() {

        String errorMessage = "Error message";
        Exception testException = new Exception(errorMessage);

        ErrorResponseDto result = handleErrorService.handleError(testException);

        List<ErrorDetails> errors = result.getError();
        assertEquals(1, errors.size());

        ErrorDetails errorDetails = errors.get(0);
        assertEquals(HttpStatus.BAD_REQUEST.value(), errorDetails.getCode());
        assertEquals(errorMessage, errorDetails.getDetail());
    }
}