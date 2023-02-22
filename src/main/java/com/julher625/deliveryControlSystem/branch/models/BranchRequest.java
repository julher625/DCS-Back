package com.julher625.deliveryControlSystem.branch.models;

import com.julher625.deliveryControlSystem.models.SoloNameRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BranchRequest implements SoloNameRequest{
    String name;
}
