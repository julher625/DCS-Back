package com.julher625.deliveryControlSystem.delivery;

import com.julher625.deliveryControlSystem.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class DeliveryTimeService {

    private final UserRepository userRepository;

    public DeliveryTime start(Integer documentId){
        DeliveryTime deliveryTime = DeliveryTime.builder()
                .startDate(new Date())
                .user(
                        userRepository.findByDocumentId(documentId)
                                .orElseThrow()
                )
                .build();
        return deliveryTime;
    }

}
