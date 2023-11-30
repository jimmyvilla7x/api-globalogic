package com.globallogic.test.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDto {
    private UUID id;

    private String name;
    private String email;
    
    private String password;
    private List<PhoneDto> phones;

    private LocalDateTime created;
    private LocalDateTime lastLogin;
    private String token;
    private boolean isActive;
    

}

