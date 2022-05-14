package com.opackisebastian.carrental.domain.order;

import com.opackisebastian.carrental.domain.car.CarDTORequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CarOrderService {
    public ResponseEntity<CarDTORequest> getReservationById(String reservationId) {
        return null;
    }

    public void cancelReservation(String reservationId) {

    }

    public void approveReservation(String reservationId) {

    }

    public ResponseEntity<CarDTORequest> createReservation(CarOrderCreateDTO reservationCreateDTO) {
        return null;
    }
}
