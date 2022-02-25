package com.sagar.azurepr.exception;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ErrorMessage {
    private Date timestamp;
    private String message;
    private String description;
}
