package com.musscraft.controllers.mussPlayer.exceptions;

public class MussPlayerAlreadyRegistered extends Exception {
    @Override
    public String getMessage() {
        return "O pĺayer já está registrado";
    }
}
