package com.julher625.deliveryControlSystem.branch;

import com.julher625.deliveryControlSystem.branch.models.Branch;
import com.julher625.deliveryControlSystem.branch.models.BranchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/branch")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;

    @PostMapping("/new")
    public ResponseEntity<Branch> saveBranch(
            @RequestBody BranchRequest request
    ){
        System.out.println(request);
        return ResponseEntity.ok(branchService.save(request));
    }
}
