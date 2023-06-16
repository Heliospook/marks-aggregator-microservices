package com.example.authenticationservice.services;

import com.example.authenticationservice.models.AuthenticationRequest;
import com.example.authenticationservice.models.AuthenticationResponse;
import com.example.authenticationservice.models.RegisterRequest;
import com.example.authenticationservice.models.User;
import com.example.authenticationservice.proxies.DataProviderProxy;
import com.example.authenticationservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private DataProviderProxy proxy;
    public AuthenticationResponse register(RegisterRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
         User user = proxy.createUser(request);
         var jwtToken = jwtService.generateToken(user);
         return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getRollNum(),
                        request.getPassword()
                )
        );
        var user = repository.findById(request.getRollNum()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
}
