package com.miguel.userservicequery.Services;

import com.miguel.userservicequery.Config.JwtService;
import com.miguel.userservicequery.Model.Audit;
import com.miguel.userservicequery.Model.AuthenticationRequest;
import com.miguel.userservicequery.Model.UserEntity;
import com.miguel.userservicequery.Repositories.UserRepository;
import com.miguel.userservicequery.Utils.KafkaUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final KafkaUtil kafkaUtil;

    public String authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        UserEntity userEntity = userRepository.findByUsername(request.username());
        if (userEntity == null) {
            throw new UsernameNotFoundException("Usuario no registrado en BD");
        }
        kafkaUtil.sendMessage(new Audit(null, "User | auth: " + userEntity.getUsername(), new Date()));
        return jwtService.generateToken(userEntity);
    }
}
