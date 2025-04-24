package com.manhpd9.sensormonitor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RoomNotExistException extends RuntimeException {
    public RoomNotExistException(String message) {
        super(message);
    }
}
