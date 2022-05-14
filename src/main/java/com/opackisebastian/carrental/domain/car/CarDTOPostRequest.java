package com.opackisebastian.carrental.domain.car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CarDTOPostRequest {
    String brand;
    String type;
    String model;
    String color;
    Integer yearModel;
    String vin;
}
