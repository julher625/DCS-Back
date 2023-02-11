package com.julher625.deliveryControlSystem.delivery;

import com.julher625.deliveryControlSystem.branch.BranchService;
import com.julher625.deliveryControlSystem.branch.models.Branch;
import com.julher625.deliveryControlSystem.branch.models.BranchRequest;
import com.julher625.deliveryControlSystem.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/delivery")
@RequiredArgsConstructor
public class DeliveryTimeController {

    private final DeliveryTimeService deliveryTimeService;
    private final BranchService branchService;

    @PostMapping("/start")
    public ResponseEntity<DeliveryTime> start(
            @RequestBody DeliveryTimeRequest request
    ){
        Branch branch =  branchService.findByName(request.getBranchName());
        DeliveryTime deliveryTime = deliveryTimeService.start(branch);


        if (deliveryTime == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(deliveryTime);
    }


    @PatchMapping("/stop")
    public  ResponseEntity<DeliveryTime> stop(){
        DeliveryTime deliveryTime = deliveryTimeService.stop();
        return ResponseEntity.ok(deliveryTime);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<DeliveryTime>> listDeliveryTimes(@RequestParam int page,
                                                                @RequestParam int size
    ){
        PageRequest pageRequest = PageRequest.of(page,size);
        return ResponseEntity.ok(deliveryTimeService.getTimes(pageRequest));
    }


}
