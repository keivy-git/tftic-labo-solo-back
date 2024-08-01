package be.keivy.fleamarketapp.common.exceptions.auth;

import be.keivy.fleamarketapp.common.exceptions.NotAllowedException;

public class InvalidPasswordException extends NotAllowedException {
    public InvalidPasswordException() {
        super("Invalid password!");
    }
}
