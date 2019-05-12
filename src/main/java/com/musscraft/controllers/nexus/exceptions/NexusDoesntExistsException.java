package com.musscraft.controllers.nexus.exceptions;

public class NexusDoesntExistsException extends Exception {

    @Override
    public String getMessage() {
        return "O nexus procurado não existe!";
    }
}
