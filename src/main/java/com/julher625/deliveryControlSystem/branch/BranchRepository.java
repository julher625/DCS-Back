package com.julher625.deliveryControlSystem.branch;

import com.julher625.deliveryControlSystem.branch.models.Branch;
import com.julher625.deliveryControlSystem.branch.models.BranchResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch,Integer> {
    Branch findFirst1ByNameOrderByIdAsc(String name);
    List<Branch> findAllByUserId(Integer userId);
    Branch findFirst1ByUserIdOrderByIdAsc(Integer userId);
}
