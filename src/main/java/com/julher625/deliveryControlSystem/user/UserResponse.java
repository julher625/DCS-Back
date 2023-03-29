package com.julher625.deliveryControlSystem.user;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private String firstname;
    private String lastname;
    private Integer documentId;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;


    public UserResponse(User user) {
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.documentId = user.getDocumentId();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}
