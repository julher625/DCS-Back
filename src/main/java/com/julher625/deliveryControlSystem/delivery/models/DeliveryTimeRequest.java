package com.julher625.deliveryControlSystem.delivery.models;

import com.julher625.deliveryControlSystem.models.SoloNameRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryTimeRequest implements SoloNameRequest {

    String branchName;
    String image;

    @Override
    public String getName() {
        return this.getBranchName();
    }
}
