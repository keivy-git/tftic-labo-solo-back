package be.keivy.fleamarketapp.bll.services.impls;

import be.keivy.fleamarketapp.bll.services.IZipCityService;
import be.keivy.fleamarketapp.common.dtos.zip_city.responses.ZipCityResponse;
import be.keivy.fleamarketapp.common.mappers.zip_city.ZipCityMapper;
import be.keivy.fleamarketapp.dal.repositories.ZipCityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class ZipCityService implements IZipCityService {

    private final ZipCityRepository zipCityRepository;
    private final ZipCityMapper zipCityMapper;

    @Override
    public List<ZipCityResponse> getAll() {
        return zipCityRepository.findAll()
                .stream()
                .map(zipCityMapper::fromEntity)
                .toList();
    }

    @Override
    public List<ZipCityResponse> getByZipOrCity(String zip, String city) {
        return zipCityRepository.findAll()
                .stream()
                .filter(zipCity ->
                        (city == null || zipCity.getCity().equalsIgnoreCase(city)) &&
                                (zip == null || zipCity.getZip().equalsIgnoreCase(zip))
                ).map(zipCityMapper::fromEntity)
                .collect(Collectors.toList());
    }

}
