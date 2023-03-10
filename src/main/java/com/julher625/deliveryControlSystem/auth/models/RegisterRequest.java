package com.julher625.deliveryControlSystem.auth.models;

import com.julher625.deliveryControlSystem.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstname;
    private String lastname;
    private Integer documentId;
    private String email;
    private String password;
    private String role;
}
