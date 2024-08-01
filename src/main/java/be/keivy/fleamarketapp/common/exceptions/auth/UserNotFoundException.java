package be.keivy.fleamarketapp.common.exceptions.auth;

import be.keivy.fleamarketapp.common.exceptions.NotFoundException;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException() {
        super("User does not exist !");
    }
}
