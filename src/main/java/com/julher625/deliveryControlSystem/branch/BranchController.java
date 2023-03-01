package com.julher625.deliveryControlSystem.branch;

import com.julher625.deliveryControlSystem.branch.models.Branch;
import com.julher625.deliveryControlSystem.branch.models.BranchRequest;
import com.julher625.deliveryControlSystem.branch.models.BranchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/branch")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;

    @PostMapping
    @Secured("SUPER")
    public ResponseEntity<Branch> saveBranch(
            @RequestBody BranchRequest request
    ){
        return ResponseEntity.ok(branchService.save(request));
    }

    @GetMapping
    public  ResponseEntity<List<BranchResponse>> listBranches(){
        List<BranchResponse> branchResponses = branchService.listBranches().stream().map(
                branch -> {
                    BranchResponse response = BranchResponse.builder()
                            .id(branch.getId())
                            .name(branch.getName())
                            .build();
                    return  response;
                }
        ).collect(Collectors.toUnmodifiableList());

        return ResponseEntity.ok(branchResponses);
    }

}
