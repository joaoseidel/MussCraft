package io.seidel.musscraft.services.exceptions;

public class HomeNotFoundException extends Exception {
    @Override
    public String getMessage() {
        return "Casa não encontrada!";
    }
}
