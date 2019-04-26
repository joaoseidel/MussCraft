package com.musscraft.exceptions.MussPlayerExceptions;

public class MussPlayerNotFoundException extends Exception {

    @Override
    public String getMessage() {
        return "Player não encontrado";
    }
}
