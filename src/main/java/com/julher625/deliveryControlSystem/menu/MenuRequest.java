package com.julher625.deliveryControlSystem.menu;

import com.julher625.deliveryControlSystem.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuRequest {
    private String name;
    private String displayName;
    private String path;
    private String role;
}
