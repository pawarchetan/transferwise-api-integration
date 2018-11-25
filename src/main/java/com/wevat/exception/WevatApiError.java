package com.wevat.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WevatApiError {
    private int statusCode = 0;
    private String message = "";
}
