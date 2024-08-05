package be.keivy.fleamarketapp.common.exceptions.flea_market;

import be.keivy.fleamarketapp.common.exceptions.NotFoundException;

public class FleaMarketNotFoundException extends NotFoundException {
    public FleaMarketNotFoundException() {
        super("Flea Market not found.");
    }
}
