package com.health.onboardrest.exception.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EmployeeErrorResponse {

    private int status;

    private String message;

    private String path;

    private LocalDateTime timestamp;

}
