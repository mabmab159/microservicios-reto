package com.miguel.userservicequery.Services;

import com.miguel.userservicequery.Config.JwtService;
import com.miguel.userservicequery.Model.AuthenticationRequest;
import com.miguel.userservicequery.Model.User;
import com.miguel.userservicequery.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        User user = userRepository.findByUsername(request.username());
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no registrado en BD");
        }
        return jwtService.generateToken(user);
    }
}
