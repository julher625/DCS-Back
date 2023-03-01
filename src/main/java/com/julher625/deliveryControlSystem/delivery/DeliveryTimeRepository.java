package com.julher625.deliveryControlSystem.delivery;


import com.julher625.deliveryControlSystem.delivery.models.DeliveryTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface DeliveryTimeRepository extends JpaRepository<DeliveryTime, Long> {

    Optional<DeliveryTime> findFirst1ByFinalDateAndUserId(Date date,Integer userId);
    Optional<DeliveryTime> findFirst1ByUserIdOrderByIdDesc(Integer userId);


    Page<DeliveryTime> findAllByUserIdOrderByIdDesc(Integer id, Pageable pageable);

    Page<DeliveryTime> findAllByBranchIdOrderByIdDesc(Integer id, Pageable pageable);

}
