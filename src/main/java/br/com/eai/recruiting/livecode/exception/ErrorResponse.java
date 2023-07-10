package br.com.eai.recruiting.livecode.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private String time;
    private String message;
}
