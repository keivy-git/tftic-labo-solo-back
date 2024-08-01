package be.keivy.fleamarketapp.common.exceptions.auth;

public class AccountReactivatedException extends RuntimeException {

    public AccountReactivatedException() {
        super("Your account has been reactivated! Please login again.");
    }
}
