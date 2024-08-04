package be.keivy.fleamarketapp.common.exceptions.role;

import be.keivy.fleamarketapp.common.exceptions.NotFoundException;

public class RoleNotFoundException extends NotFoundException {
    public RoleNotFoundException() {
        super("Role not found ");
    }
}
