package be.keivy.fleamarketapp.common.exceptions.flea_market;

import be.keivy.fleamarketapp.common.exceptions.NotAllowedException;

public class FleaMarketIsActiveException extends NotAllowedException {
    public FleaMarketIsActiveException() {
        super("Flea Market is active.");
    }
}
