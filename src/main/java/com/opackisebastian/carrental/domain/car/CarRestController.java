package com.opackisebastian.carrental.domain.car;

import com.opackisebastian.carrental.domain.car.exception.ResourceAlreadyExistsException;
import com.opackisebastian.carrental.domain.form.FormResponseDTO;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cars/")
@RequiredArgsConstructor
public class CarRestController {

    private final CarService carService;

    private final ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/{id}")
    public ResponseEntity<CarDTORequest> retrieveCarById(@PathVariable("id") String carId) {
        return carService.findCarById(carId)
                .map(car -> ResponseEntity.ok().body(convertFromEntity(car)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarById(@PathVariable("id") String carId) {
        carService.deleteCarById(carId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/matching-forms")
    public ResponseEntity<List<FormResponseDTO>> findAllCarsWithMatchingForms(@Param("formIds") List<String> formIds) {

        return null;
    }

    @PutMapping
    public ResponseEntity<CarView> modifyExistingCar(@RequestParam(name = "id") String carId, @RequestBody CarDTORequest carPutRequest) {
        return new ResponseEntity<>(carService.editCarById(carId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CarDTORequest> addNewCar(@RequestBody CarDTOPostRequest carDTOPostRequest) {
        Either<ResourceAlreadyExistsException, Car> car = carService.addNewCar(convertToEntity(carDTOPostRequest));

        return new ResponseEntity<>(convertFromEntity(car.getOrElseThrow(ResourceAlreadyExistsException::new)), HttpStatus.CREATED);
    }

    private CarDTORequest convertFromEntity(Car carEntity) {
        return modelMapper.map(carEntity, CarDTORequest.class);
    }

    private Car convertToEntity(CarDTOPostRequest carDTOPostRequest) {
        return modelMapper.map(carDTOPostRequest, Car.class);
    }
}
