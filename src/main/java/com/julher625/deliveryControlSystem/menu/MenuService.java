package com.julher625.deliveryControlSystem.menu;

import com.julher625.deliveryControlSystem.user.Role;
import com.julher625.deliveryControlSystem.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;

    public Menu saveMenu(MenuRequest menuRequest){

        Menu menu = Menu.builder()
                .displayName(menuRequest.getDisplayName())
                .path(menuRequest.getPath())
                .role(Role.valueOf(menuRequest.getRole()))
                .name(menuRequest.getName())
                .build();
        return menuRepository.save(menu);
    }

    public List<Menu> findByRole(){
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return menuRepository.findAllByRoleOrderByIdDesc(user.getRole());
    }


}
