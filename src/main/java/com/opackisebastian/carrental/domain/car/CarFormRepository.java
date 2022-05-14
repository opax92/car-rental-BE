package com.opackisebastian.carrental.domain.car;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarFormRepository extends CrudRepository<CarForm, String> {

    List<CarForm> findAllByFormIdIn(List<String> formIds);

}
