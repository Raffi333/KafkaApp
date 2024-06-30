package rh.example.produser.model;

import java.util.Date;

public class UserError {
    private Date dateError;
    private String message;

    public UserError(Date dateError, String message) {
        this.dateError = dateError;
        this.message = message;
    }
}
