package com.julher625.deliveryControlSystem.branch;

import com.julher625.deliveryControlSystem.branch.models.Branch;
import com.julher625.deliveryControlSystem.branch.models.BranchRequest;
import com.julher625.deliveryControlSystem.branch.models.BranchResponse;
import com.julher625.deliveryControlSystem.models.SoloNameRequest;
import com.julher625.deliveryControlSystem.user.Role;
import com.julher625.deliveryControlSystem.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository branchRepository;

    public Branch save(BranchRequest request) {
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Branch branch = Branch.builder()
                .name(request.getName())
                .user(user)
                .build();

        return branchRepository.save(branch);
    }

    public List<Branch> listBranches(){
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.getRole() == Role.DELIVERY){
            return branchRepository.findAll();
        }else if (user.getRole() == Role.CLIENT){
            return branchRepository.findAllByUserId(user.getId());
        }
        return null;
    }

    public Branch findByName(String name){
        return branchRepository.findFirst1ByNameOrderByIdAsc(name);
    }

    public Branch findByUserId(Integer userId){
        return  branchRepository.findFirst1ByUserIdOrderByIdAsc(userId);
    }


}
