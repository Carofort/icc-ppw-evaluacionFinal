package ec.edu.ups.icc.labevaluation.core.exceptions.domain;

import org.springframework.http.HttpStatus;

import ec.edu.ups.icc.labevaluation.core.exceptions.base.ApplicationException;

public class SupplyConflictException extends ApplicationException {

    public SupplyConflictException(String code, String message) {
        super(HttpStatus.CONFLICT, code, message);
    }
    public SupplyConflictException(String message) {
        this("SUPPLY_CONFLICT", message);
    }
}
