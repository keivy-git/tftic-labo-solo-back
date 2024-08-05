package be.keivy.fleamarketapp.common.dtos.flea_market.requests;

import be.keivy.fleamarketapp.domain.entities.Address;
import be.keivy.fleamarketapp.domain.entities.Organizer;
import jakarta.validation.constraints.NotBlank;
import org.springframework.cglib.core.Local;

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
        @NotBlank(message = "There must be a address")
        Address address,
        Organizer organizerId
        ) {
}
