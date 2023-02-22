package com.julher625.deliveryControlSystem.delivery;

import com.julher625.deliveryControlSystem.branch.BranchService;
import com.julher625.deliveryControlSystem.branch.models.Branch;
import com.julher625.deliveryControlSystem.delivery.models.DeliveryTime;
import com.julher625.deliveryControlSystem.delivery.models.DeliveryTimeRequest;
import com.julher625.deliveryControlSystem.delivery.models.Status;
import com.julher625.deliveryControlSystem.user.Role;
import com.julher625.deliveryControlSystem.user.User;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.zip.Deflater;

@Service
@RequiredArgsConstructor
public class DeliveryTimeService {

    private final DeliveryTimeRepository deliveryTimeRepository;
    private final BranchService branchService;

    public DeliveryTime start(DeliveryTimeRequest request){

        Branch branch =  branchService.findByName(request.getBranchName());
        byte[] initialPhoto = request.getImage().getBytes();
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        DeliveryTime deliveryTime = DeliveryTime.builder()
                .startDate(new Date())
                .user(
                        user
                )
                .status(Status.OPEN)
                .branch(branch)
                .initialPhoto(initialPhoto)
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


    public Page<DeliveryTime> getTimes(PageRequest pageRequest, Integer branchId) {
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user.getRole() == Role.DELIVERY){
            Page<DeliveryTime> deliveryTimes =deliveryTimeRepository.findAllByUserIdOrderByIdDesc(user.getId(),pageRequest);
            deliveryTimes.forEach(deliveryTime -> {
                System.out.println(deliveryTime.getInitialPhoto());
            });
            return deliveryTimes;
        }else {
            return deliveryTimeRepository.findAllByBranchIdOrderByIdDesc(branchId, pageRequest);
        }


    }
}
