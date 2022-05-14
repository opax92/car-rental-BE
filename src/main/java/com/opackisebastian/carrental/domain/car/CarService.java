package com.opackisebastian.carrental.domain.car;

import com.opackisebastian.carrental.domain.car.exception.ResourceAlreadyExistsException;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    private final CarFormRepository carFormRepository;

    public Either<ResourceAlreadyExistsException, Car> addNewCar(Car car) {
        Optional<Car> carByVin = carRepository.findByVin(car.getVin());

        if (carByVin.isPresent()) {
            return Either.left(new ResourceAlreadyExistsException());
        }

        return Either.right(carRepository.save(car));
    }

    public List<Car> retrieveCarsWithMatchingForms(List<String> formIds) {
        List<CarForm> allByFormId = carFormRepository.findAllByFormIdIn(formIds);

        return null;
    }

    public Optional<Car> findCarById(String carId) {
        return carRepository.findById(carId);
    }

    public void deleteCarById(String carId) {
        carRepository.deleteById(carId);
    }

    public CarView editCarById(String carId) {
        throw new UnsupportedOperationException();
    }

}
