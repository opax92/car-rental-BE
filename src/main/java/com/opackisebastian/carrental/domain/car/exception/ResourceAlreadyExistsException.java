package com.opackisebastian.carrental.domain.car.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Actor Not Found")
public class ResourceAlreadyExistsException extends RuntimeException{
}
