package com.buyukli.ivan.productserviceweb2.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class AppError {
    @Getter
    @Setter
    private int statusCode;
    @Getter
    @Setter
    private String message;
}
