package com.manhpd9.sensormonitor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BuildingNotExistException extends RuntimeException {
    public BuildingNotExistException(String message) {
        super(message);
    }
}
