package com.julher625.deliveryControlSystem.delivery;

import com.julher625.deliveryControlSystem.user.User;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class DeliveryTimeService {

    private final DeliveryTimeRepository deliveryTimeRepository;

    public DeliveryTime start(){
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        DeliveryTime deliveryTime = DeliveryTime.builder()
                .startDate(new Date())
                .user(
                        user
                )
                .status(Status.OPEN)
                .build();

        if (deliveryTimeRepository.countByFinalDate(null) != 0){
            System.out.println(deliveryTimeRepository.countByFinalDate(null));
            return null;
        }

        deliveryTimeRepository.save(deliveryTime);

        return deliveryTime;
    }

    public DeliveryTime stop() {
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        DeliveryTime deliveryTime = deliveryTimeRepository.findFirst1ByUserIdOrderByIdDesc(user.getId())
                .orElseThrow();
        deliveryTime.setFinalDate( new Date());
        deliveryTime.setStatus(Status.CLOSED);
        deliveryTimeRepository.save(deliveryTime);
        return deliveryTime;
    }


    public Page<DeliveryTime> getTimes(PageRequest pageRequest) {
        return  deliveryTimeRepository.findAll(pageRequest);
    }
}
