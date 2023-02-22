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
@Entity
@Table(name = "delivery_times")
public class DeliveryTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date startDate;
    private Date finalDate;

    @Column(length = 1000000)
    private byte[] initialPhoto;

    @Column(length = 1000000)
    private byte[] finalPhoto;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private User user;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Branch branch;


}
