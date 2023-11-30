/*
 * @(#)TestUserService.java
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

import com.globallogic.test.dto.PhoneDto;
import com.globallogic.test.dto.SignUpRequestDto;
import com.globallogic.test.exception.GeneralLogicException;
import com.globallogic.test.model.PhoneModel;
import com.globallogic.test.model.UserModel;
import com.globallogic.test.repository.PhoneRepository;
import com.globallogic.test.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

public class TestUserService {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PhoneRepository phoneRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSignUp() {

        SignUpRequestDto request = new SignUpRequestDto();
        PhoneDto phoneDto = new PhoneDto();
        phoneDto.setCitycode(7);
        phoneDto.setCountrycode("25");
        phoneDto.setNumber(87650009);

        List listaPhones = new ArrayList<>();
        listaPhones.add(phoneDto);

        request.setEmail("juan@gmail.cl");
        request.setName("juan");
        request.setPassword("a2asfGfdfdf4");
        request.setPhones(listaPhones);

        Mockito.when(userRepository.findByEmail(request.getEmail())).thenReturn(null);
        UserModel result = userService.signUp(request);

        assertEquals(request.getName(), result.getName());
        assertEquals(request.getEmail(), result.getEmail());

        Mockito.verify(userRepository, times(1)).save(Mockito.any(UserModel.class));
        Mockito.verify(phoneRepository, times(1)).save(Mockito.any(PhoneModel.class));
    }

    @Test
    public void testSignUpExiste()  {

        SignUpRequestDto request = new SignUpRequestDto();

        Mockito.when(userRepository.findByEmail(request.getEmail())).thenReturn(new UserModel());
        assertThrows(GeneralLogicException.class, () -> userService.signUp(request));

        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any(UserModel.class));
        Mockito.verify(phoneRepository, Mockito.never()).save(Mockito.any(PhoneModel.class));
    }


    @Test
    public void testLoginActualizaToken() {

        UserModel user = new UserModel();

        Mockito.when(userRepository.save(user)).thenReturn(user);
        UserModel result = userService.loginActualizaToken(user);

        assertEquals(user.getToken(), result.getToken());
        Mockito.verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testGetUserByToken() {

        String token = "dummyToken";

        UserModel user = new UserModel();
        Mockito.when(userRepository.findByToken(token)).thenReturn(user);

        UserModel result = userService.getUserByToken(token);
        assertEquals(user, result);

        Mockito.verify(userRepository, Mockito.times(1)).findByToken(token);
    }

    @Test
    public void testGetUserByTokenInvalidToken() {

        String invalidToken = "invalidToken";

        Mockito.when(userRepository.findByToken(invalidToken)).thenReturn(null);
        assertThrows(GeneralLogicException.class, () -> userService.getUserByToken(invalidToken), "Token Incorrecto");
        Mockito.verify(userRepository, Mockito.times(1)).findByToken(invalidToken);
    }


}