package com.opackisebastian.carrental.domain.order;

import com.opackisebastian.carrental.domain.car.CarDTORequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("reservations/")
@RequiredArgsConstructor
public class CarOrderController {

    private final CarOrderService reservationService;

    private final ModelMapper modelMapper = new ModelMapper();

    @PostMapping
    public ResponseEntity<CarDTORequest> createReservation(@RequestBody CarOrderCreateDTO reservationCreateDTO) {
        return reservationService.createReservation(reservationCreateDTO);
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<Void> approveReservationById(@PathVariable("id") String reservationId) {
        reservationService.approveReservation(reservationId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDTORequest> retrieveReservationById(@PathVariable("id") String reservationId) {
        return reservationService.getReservationById(reservationId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelReservationById(@PathVariable("id") String reservationId) {
        reservationService.cancelReservation(reservationId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
