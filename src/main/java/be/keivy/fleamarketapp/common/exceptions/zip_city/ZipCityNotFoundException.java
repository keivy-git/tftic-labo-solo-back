package be.keivy.fleamarketapp.common.exceptions.zip_city;

import be.keivy.fleamarketapp.common.exceptions.NotFoundException;

public class ZipCityNotFoundException extends NotFoundException {
    public ZipCityNotFoundException() {
        super("Zip City not found");
    }
}
