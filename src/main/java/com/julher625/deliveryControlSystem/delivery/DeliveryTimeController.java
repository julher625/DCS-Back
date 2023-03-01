package com.julher625.deliveryControlSystem.delivery;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.julher625.deliveryControlSystem.branch.BranchService;
import com.julher625.deliveryControlSystem.branch.models.Branch;
import com.julher625.deliveryControlSystem.delivery.models.DeliveryTime;
import com.julher625.deliveryControlSystem.delivery.models.DeliveryTimeRequest;
import com.julher625.deliveryControlSystem.delivery.models.DeliveryTimeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URI;


@RestController
@RequestMapping("/api/v1/delivery")
@RequiredArgsConstructor
public class DeliveryTimeController {

    private final DeliveryTimeService deliveryTimeService;
    private final BranchService branchService;

    @PostMapping("/start")
    @Secured("DELIVERY")
    public ResponseEntity<DeliveryTime> start(
            @RequestBody DeliveryTimeRequest request
    ) throws IOException {

        DeliveryTime deliveryTime = deliveryTimeService.start(request);
        if (deliveryTime == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(deliveryTime);
    }


    @PatchMapping("/stop")
    @Secured("DELIVERY")
    public  ResponseEntity<DeliveryTime> stop(){
        DeliveryTime deliveryTime = deliveryTimeService.stop();
        return ResponseEntity.ok(deliveryTime);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<DeliveryTimeResponse>> listDeliveryTimes(@RequestParam int page,
                                                                @RequestParam int size,
                                                                @RequestParam(required = false) Integer branchId
    ){
        PageRequest pageRequest = PageRequest.of(page,size);

        Page<DeliveryTimeResponse> mapped = deliveryTimeService.getTimes(pageRequest, branchId)
                .map(deliveryTime -> {
                    DeliveryTimeResponse deliveryTimeResponse = DeliveryTimeResponse.builder()
                            .user(deliveryTime.getUser())
                            //.finalPhoto(new String(deliveryTime.getFinalPhoto()))
                            .initialPhoto(new String(deliveryTime.getInitialPhoto()))
                            .startDate(deliveryTime.getStartDate())
                            .finalDate(deliveryTime.getFinalDate())
                            .branch(deliveryTime.getBranch())
                            .status(deliveryTime.getStatus())
                            .build();
                    return deliveryTimeResponse;
                });

        return ResponseEntity.ok(mapped);
    }

    @GetMapping("/started")
    public ResponseEntity<Boolean> started(){
        return  ResponseEntity.ok(deliveryTimeService.deliveryTimeIsStarted());
    }

}
