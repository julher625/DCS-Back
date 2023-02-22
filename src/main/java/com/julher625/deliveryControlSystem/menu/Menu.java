package com.julher625.deliveryControlSystem.menu;

import com.julher625.deliveryControlSystem.user.Role;
import com.julher625.deliveryControlSystem.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "menus")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)

    private Integer id;
    String name;
    String displayName;
    String path;

    @Enumerated(EnumType.STRING)
    private Role role;
}
