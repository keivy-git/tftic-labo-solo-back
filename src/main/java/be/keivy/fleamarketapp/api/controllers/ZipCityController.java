package be.keivy.fleamarketapp.api.controllers;

import be.keivy.fleamarketapp.bll.services.impls.ZipCityService;
import be.keivy.fleamarketapp.common.dtos.zip_city.responses.ZipCityResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/zipcity")
public class ZipCityController {

    private final ZipCityService zipCityService;

    @GetMapping("/all")
    public ResponseEntity<List<ZipCityResponse>> getAllZipCities() {
        return ResponseEntity.ok(zipCityService.getAll());
    }

    @GetMapping
    public ResponseEntity<List<ZipCityResponse>> getZipCities(
            @RequestParam(required = false) String zip,
            @RequestParam(required = false) String city) {
        if (city == null && zip == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(zipCityService.getByZipOrCity(zip, city));
        }
    }
}
