package com.opackisebastian.carrental.domain.car;

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
public class Car {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    private String brand;

    private String type;

    private String model;

    private String color;

    private Integer yearModel;

    private String vin;

}
