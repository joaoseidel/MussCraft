package com.musscraft.exceptions.MussPlayerExceptions;

public class MussPlayerNotExistsException extends Exception {

    @Override
    public String getMessage() {
        return "O player não existe.";
    }
}
