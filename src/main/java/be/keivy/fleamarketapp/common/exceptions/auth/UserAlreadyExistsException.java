package be.keivy.fleamarketapp.common.exceptions.auth;

import be.keivy.fleamarketapp.common.exceptions.AlreadyExistsException;

public class UserAlreadyExistsException extends AlreadyExistsException {

    public UserAlreadyExistsException() {
        super("User already exists !");
    }
}
