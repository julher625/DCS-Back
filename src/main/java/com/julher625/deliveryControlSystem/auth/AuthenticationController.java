package com.julher625.deliveryControlSystem.auth;

import com.julher625.deliveryControlSystem.auth.models.AuthenticationRequest;
import com.julher625.deliveryControlSystem.auth.models.AuthenticationResponse;
import com.julher625.deliveryControlSystem.auth.models.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return  ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request,
            @RequestHeader Map<String, String> headers
    ){
        headers.forEach((key, value) -> {
            System.out.println(String.format("Header '%s' = %s", key, value));
        });
        return  ResponseEntity.ok(authenticationService.authenticate(request));
    }

}
