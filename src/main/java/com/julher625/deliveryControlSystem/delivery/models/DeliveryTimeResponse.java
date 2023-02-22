package com.julher625.deliveryControlSystem.delivery.models;

import com.julher625.deliveryControlSystem.branch.models.Branch;
import com.julher625.deliveryControlSystem.user.User;
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
    private User user;
    private Status status;
    private Branch branch;
}
