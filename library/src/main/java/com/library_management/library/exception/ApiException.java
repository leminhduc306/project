package com.library_management.library.exception;

import com.library_management.library.constant.ErrorCode;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    ErrorCode errorCode;

    public ApiException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
