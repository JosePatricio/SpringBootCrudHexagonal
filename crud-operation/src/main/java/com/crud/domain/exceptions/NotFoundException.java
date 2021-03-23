package com.crud.domain.exceptions;

public class NotFoundException extends RuntimeException {
    private static final String DESCRIPTION = "Not Found Exception (404)";

    public NotFoundException(Long id) {
        super(DESCRIPTION + ". " + id);
    }

}
