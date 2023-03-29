package com.julher625.deliveryControlSystem.delivery.models;

import com.julher625.deliveryControlSystem.branch.models.Branch;
import com.julher625.deliveryControlSystem.branch.models.BranchResponse;
import com.julher625.deliveryControlSystem.user.User;
import com.julher625.deliveryControlSystem.user.UserResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryTimeResponse {
    private Date startDate;
    private Date finalDate;
    private String initialPhoto;
    private String finalPhoto;
    private UserResponse user;
    private Status status;
    private BranchResponse branch;
}
