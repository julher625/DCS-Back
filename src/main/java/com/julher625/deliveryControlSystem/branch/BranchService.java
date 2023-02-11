package com.julher625.deliveryControlSystem.branch;

import com.julher625.deliveryControlSystem.branch.models.Branch;
import com.julher625.deliveryControlSystem.branch.models.BranchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository branchRepository;

    public Branch save(BranchRequest request) {

        Branch branch = Branch.builder()
                .name(request.getName())
                .build();

        return branchRepository.save(branch);
    }

    public Branch findByName(String name){
        return branchRepository.findFirst1ByNameOrderByIdAsc(name);
    }

}
