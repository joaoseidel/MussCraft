package com.musscraft.controllers.mussPlayer.exceptions;

public class MussPlayerNotFoundException extends Exception {

    @Override
    public String getMessage() {
        return "Player não encontrado";
    }
}
