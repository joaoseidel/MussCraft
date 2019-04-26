package com.musscraft.exceptions.MussPlayerExceptions;

public class MussPlayerAlreadyRegistered extends Exception {
    @Override
    public String getMessage() {
        return "O pĺayer já está registrado";
    }
}
