package be.keivy.fleamarketapp.common.exceptions;

public class InvalidUserTypeException extends NotAllowedException {
    public InvalidUserTypeException() {
        super("Invalid user type!");
    }
}
