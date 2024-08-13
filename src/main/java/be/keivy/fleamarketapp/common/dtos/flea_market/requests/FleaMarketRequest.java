package be.keivy.fleamarketapp.common.dtos.flea_market.requests;

import be.keivy.fleamarketapp.domain.entities.Address;
import be.keivy.fleamarketapp.domain.entities.Organizer;

import java.time.LocalDate;

public record FleaMarketRequest(

        String title,
        String description,
        String shortDescription,
        String urlPicture,
        LocalDate dateBegin,
        LocalDate dateEnd,
        int pricePerMeter,
        int locationPrice,
        Address address,
        Organizer organizer
        ) {
}
