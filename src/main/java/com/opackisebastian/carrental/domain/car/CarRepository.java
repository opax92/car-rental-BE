package com.opackisebastian.carrental.domain.car;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CarRepository extends CrudRepository<Car, String> {

    Optional<Car> findByVin(String vin);

}
