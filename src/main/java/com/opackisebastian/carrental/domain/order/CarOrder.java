package com.opackisebastian.carrental.domain.order;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarOrder {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    private String carId;

    private CarOrderStatus status;

}
