package com.julher625.deliveryControlSystem.menu;

import com.julher625.deliveryControlSystem.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    List<Menu> findAllByRoleOrderByIdDesc(Role role);
}
