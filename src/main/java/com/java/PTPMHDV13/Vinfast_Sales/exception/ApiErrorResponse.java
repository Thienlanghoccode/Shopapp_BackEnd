package com.java.PTPMHDV13.Vinfast_Sales.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ApiErrorResponse {
    private Date timestamp;
    private String path;
    private String message;
}
