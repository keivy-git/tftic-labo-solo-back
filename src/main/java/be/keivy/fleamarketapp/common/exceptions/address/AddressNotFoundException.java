package be.keivy.fleamarketapp.common.exceptions.address;

import be.keivy.fleamarketapp.common.exceptions.NotFoundException;

public class AddressNotFoundException extends NotFoundException {
    public AddressNotFoundException() {
        super("Address not found");
    }
}
