/*
 * @(#)TestValidateUser.java
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

import com.globallogic.test.dto.SignUpRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestValidacionService {

    @InjectMocks
    private ValidacionService validacionService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testValidaNombre() {

        SignUpRequestDto userDto = new SignUpRequestDto();
        userDto.setName("Juan");
        userDto.setEmail("juan@gmail.com");
        userDto.setPassword("a2asfGfdfdf4");

        validacionService.validateUser(userDto);
    }

    @Test
    public void testValidaNombreNull() {

        SignUpRequestDto userDto = new SignUpRequestDto();
        userDto.setEmail("juan@gmail.com");
        userDto.setPassword("a2asfGfdfdf4");

        assertThrows(IllegalArgumentException.class, () ->
            validacionService.validateUser(userDto), "El nombre es obligatorio");
    }

    @Test
    public void testValidaMailInvalido() {

        SignUpRequestDto userDto = new SignUpRequestDto();
        userDto.setName("Juan");
        userDto.setEmail("juan@gmail");

        assertThrows(IllegalArgumentException.class, () ->
            validacionService.validateUser(userDto), "El correo electrónico no es válido");
    }

    @Test
    public void testValidaClaveInvalida() {

        SignUpRequestDto userDto = new SignUpRequestDto();
        userDto.setName("Juan");
        userDto.setEmail("juan@gmail.com");
        userDto.setPassword("123");

        assertThrows(IllegalArgumentException.class, () ->
            validacionService.validateUser(userDto), "La contraseña no cumple con los requisitos");
    }

}