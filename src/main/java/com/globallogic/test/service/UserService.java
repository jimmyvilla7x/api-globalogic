package com.globallogic.test.service;

import com.globallogic.test.dto.PhoneDto;
import com.globallogic.test.dto.SignUpRequestDto;
import com.globallogic.test.exception.GeneralLogicException;
import com.globallogic.test.model.PhoneModel;
import com.globallogic.test.model.UserModel;
import com.globallogic.test.repository.PhoneRepository;
import com.globallogic.test.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    public UserModel signUp(SignUpRequestDto request) {
    
        if (userRepository.findByEmail(request.getEmail()) != null) {
            throw new GeneralLogicException("El usuario ya existe");
        }

        UserModel user= new UserModel();
        user.setName(request.getName());
        user.setEmail(request.getEmail());

        // user.setPassword(request.getPassword());
        String hashedPassword = BCrypt.hashpw(request.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);

        user.setCreated(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setActive(true);
        user.setToken(this.generateJwtToken(user));

        userRepository.save(user);

        if (request.getPhones().size() > 0) {
            List<PhoneModel> phoneModels = request.getPhones().stream()
                    .map(PhoneDto::createEntity)
                    .collect(Collectors.toList());
        
            phoneModels.forEach(phone -> {
                phone.setUser(user);
                phoneRepository.save(phone);
            });
        
            user.setPhones(phoneModels);
        }

        return user;
    }


     public UserModel loginActualizaToken(UserModel user) {
        String newToken = this.generateJwtToken(user);
        user.setToken(newToken);
        this.userRepository.save(user);
        return user;
    }

    public String generateJwtToken(UserModel user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 864000000)) // 10 dias
                // .signWith(SignatureAlgorithm.HS512, "yourSecretKey")
                .compact();
    }

    public UserModel getUserByToken(String token) {
        UserModel user = this.userRepository.findByToken(token);
        if (user == null) {
            throw new GeneralLogicException("Token Incorrecto");
        }
        return user;
    }

}

