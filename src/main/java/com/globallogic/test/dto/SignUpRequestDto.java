package com.globallogic.test.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignUpRequestDto {
    private String name;
    private String email;
    private String password;
    private List<PhoneDto> phones;

}