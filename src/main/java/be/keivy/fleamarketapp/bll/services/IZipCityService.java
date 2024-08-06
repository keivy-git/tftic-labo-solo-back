package be.keivy.fleamarketapp.bll.services;

import be.keivy.fleamarketapp.common.dtos.zip_city.responses.ZipCityResponse;

import java.util.List;

public interface IZipCityService {

    /**
     * Affiche toute les villes et code postal
     * @return une liste de ville et code postal
     */
    List<ZipCityResponse> getAll();

    List<ZipCityResponse> getByZipOrCity(String zip, String city);
}
