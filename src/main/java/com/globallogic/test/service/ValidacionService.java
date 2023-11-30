package com.globallogic.test.service;

import com.globallogic.test.dto.SignUpRequestDto;
import org.springframework.stereotype.Service;

@Service
public class ValidacionService {
     public void validateUser(SignUpRequestDto userDto) { 
        if (userDto.getName() == null || userDto.getName().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (userDto.getEmail() == null || !userDto.getEmail().matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
            throw new IllegalArgumentException("El correo electrónico no es válido");
        }
        if (userDto.getPassword() == null || !userDto.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,12}$")) {
            throw new IllegalArgumentException("La contraseña no cumple con los requisitos");
        }
    }
}
