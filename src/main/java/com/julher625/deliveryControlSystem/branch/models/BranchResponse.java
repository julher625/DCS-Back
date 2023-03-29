package com.julher625.deliveryControlSystem.branch.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BranchResponse {
    private Integer id;
    String name;

    public BranchResponse(Branch branch) {
        this.id = branch.getId();
        this.name = branch.getName();
    }
}
