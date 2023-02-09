package com.julher625.deliveryControlSystem.delivery;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/delivery")
public class DeliveryTimeController {

    @PostMapping("/start")
    public ResponseEntity<String> sayHi(){

        return ResponseEntity.ok("Hi secured");
    }


}
