package com.globallogic.test.controller;

import com.globallogic.test.dto.ErrorResponseDto;
import com.globallogic.test.dto.SignUpRequestDto;
import com.globallogic.test.model.UserModel;
import com.globallogic.test.service.HandleErrorService;
import com.globallogic.test.service.UserService;
import com.globallogic.test.service.ValidacionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "User API")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ValidacionService validacionService;

    @Autowired
    private HandleErrorService handleErrorService;


    @ApiOperation("Endpoint para crear un usuario")
    @PostMapping(value = "/sign-up", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        try {
    
            validacionService.validateUser(signUpRequestDto);
    
            UserModel createdUser = userService.signUp(signUpRequestDto);
    
            return ResponseEntity.ok(createdUser);
        } catch (Exception e) {
            ErrorResponseDto errorResponse = handleErrorService.handleError(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }


    @ApiOperation("Endpoint para consultar un usuario por token")
    @GetMapping(value = "/login", produces = "application/json")
    public ResponseEntity<?> login(HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");

            if (token != null && !token.isEmpty()) {

                UserModel user = userService.getUserByToken(token);
                
                if (user != null) {
                    UserModel newUser = userService.loginActualizaToken(user);
                    return ResponseEntity.ok(newUser); 
                } else {
                    ErrorResponseDto errorResponse = new ErrorResponseDto(401, "Usuario no se encontró");
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
                }
            } else {
                    ErrorResponseDto errorResponse = new ErrorResponseDto(401, "Falta Token de Authorization");
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            }
        } catch (Exception e) {
            ErrorResponseDto errorResponse = handleErrorService.handleError(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}
