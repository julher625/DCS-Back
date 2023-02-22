package com.julher625.deliveryControlSystem.auth;

import com.julher625.deliveryControlSystem.auth.models.AuthenticationRequest;
import com.julher625.deliveryControlSystem.auth.models.AuthenticationResponse;
import com.julher625.deliveryControlSystem.auth.models.RegisterRequest;
import com.julher625.deliveryControlSystem.config.JwtService;
import com.julher625.deliveryControlSystem.user.Role;
import com.julher625.deliveryControlSystem.user.User;
import com.julher625.deliveryControlSystem.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        Role role = Role.valueOf(request.getRole());
        System.out.println(role);
        if (role == null) {
            role = Role.DELIVERY;
        }

        User user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .documentId(request.getDocumentId())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();
        userRepository.save(user);

        String jwtToken =jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getDocumentId(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByDocumentId(request.getDocumentId())
                .orElseThrow();
        String jwtToken =jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
