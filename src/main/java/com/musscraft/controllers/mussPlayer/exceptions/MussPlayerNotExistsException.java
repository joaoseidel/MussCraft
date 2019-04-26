package com.musscraft.controllers.mussPlayer.exceptions;

public class MussPlayerNotExistsException extends Exception {

    @Override
    public String getMessage() {
        return "O player não existe.";
    }
}
