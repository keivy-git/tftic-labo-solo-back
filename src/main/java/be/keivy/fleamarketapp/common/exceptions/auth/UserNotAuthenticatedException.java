package be.keivy.fleamarketapp.common.exceptions.auth;

import be.keivy.fleamarketapp.common.exceptions.NotAllowedException;

public class UserNotAuthenticatedException extends NotAllowedException {

        public UserNotAuthenticatedException() {
            super("You are not authenticated !");
        }
}
