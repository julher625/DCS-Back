package com.julher625.deliveryControlSystem.menu;


import com.julher625.deliveryControlSystem.delivery.models.DeliveryTimeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/menu")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @PostMapping
    @Secured("SUPER")
    public ResponseEntity<Menu> saveMenu(
            @RequestBody MenuRequest request
    ){
        Menu menu = menuService.saveMenu(request);
        if (menu == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        System.out.println(menu);
        return ResponseEntity.ok(menu);
    }

    @GetMapping
    public ResponseEntity<List<Menu>> listMenus(){

        List<Menu> menus = menuService.findByRole();

        if (menus == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(menus);
    }

}
