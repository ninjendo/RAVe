package com.ninjendo.rave.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.UNPROCESSABLE_ENTITY)
public class MissingDataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3543157183551872526L;

    public MissingDataException(String message) {
        super(message);
    }
}
