package be.keivy.fleamarketapp.infrastructure.data_init;

import be.keivy.fleamarketapp.dal.repositories.FleaMarketRepository;
import be.keivy.fleamarketapp.dal.repositories.OrganizerRepository;
import be.keivy.fleamarketapp.dal.repositories.ZipCityRepository;
import be.keivy.fleamarketapp.domain.entities.Address;
import be.keivy.fleamarketapp.domain.entities.FleaMarket;
import be.keivy.fleamarketapp.domain.entities.Organizer;
import be.keivy.fleamarketapp.domain.entities.ZipCity;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(2)
public class FleaMarketInit implements CommandLineRunner {

    private final FleaMarketRepository fleaMarketRepository;
    private  final ZipCityRepository zipCityRepository;
    private  final OrganizerRepository organizerRepository;

    @Override
    public void run(String... args) throws Exception {
        if (fleaMarketRepository.count() == 0) {

            List<ZipCity> zipCities = zipCityRepository.findAll();
            List<Organizer> organizerList = organizerRepository.findAll();

            FleaMarket fleaMarket1 = new FleaMarket();

            fleaMarket1.setTitle("Brocante samedi Sombreffe");
            fleaMarket1.setDescription("Brocante Sombreffe samedi 10 août\n" +
                    "placement dès 05h\n" +
                    "emplacement 6 mètres façade 5 mètres de profondeur avec véhicule à l arrière prix 15 euros\n" +
                    "Fête foraine, concert, animation divers\n" +
                    "Bar, restauration sur place\n" +
                    "\n" +
                    "Réservation Daniel");
            fleaMarket1.setAddress(new Address("Place du Stain", zipCities.get(1205)));
            fleaMarket1.setPhoneNumberEvent("0745 25 36 74");
            fleaMarket1.setPricePerMeter(15);
            fleaMarket1.setLocationPrice(6);
            fleaMarket1.setOrganizer(organizerList.get(0));
            fleaMarket1.setUrlPicture("https://www.quefaire.be/imgok/8578069_1.jpeg");
            fleaMarket1.setNumberOfPlaces(200);
            fleaMarket1.setDateBegin(LocalDate.of(2024, 8, 6));
            fleaMarket1.setDateEnd(LocalDate.of(2024,8,10));
            fleaMarket1.setShortDescription("Brocante Sombreffe samedi 10 août");
            fleaMarket1.setActiveDay(fleaMarket1.getActiveDay());
            fleaMarket1.setActive(true);

            FleaMarket fleaMarket2 = new FleaMarket();

            fleaMarket2.setTitle("Brocante dimanche Namur");
            fleaMarket2.setDescription("Brocante Namur dimanche 11 août\n" +
                    "placement dès 06h\n" +
                    "emplacement 8 mètres façade 5 mètres de profondeur avec véhicule à l arrière prix 20 euros\n" +
                    "Jeux pour enfants, animations musicales\n" +
                    "Buvette, food trucks\n" +
                    "\n" +
                    "Réservation Marie");
            fleaMarket2.setAddress(new Address("Place d'Armes", zipCities.get(1145)));
            fleaMarket2.setPhoneNumberEvent("0745 36 47 85");
            fleaMarket2.setPricePerMeter(20);
            fleaMarket2.setLocationPrice(8);
            fleaMarket2.setOrganizer(organizerList.get(1));
            fleaMarket2.setUrlPicture("https://www.quefaire.be/imgok/8578069_1.jpeg");
            fleaMarket2.setNumberOfPlaces(250);
            fleaMarket2.setDateBegin(LocalDate.of(2024, 8, 11));
            fleaMarket2.setDateEnd(LocalDate.of(2024, 8, 15));
            fleaMarket2.setShortDescription("Brocante Namur dimanche 11 août");
            fleaMarket2.setActiveDay(fleaMarket2.getActiveDay());
            fleaMarket2.setActive(true);

            FleaMarket fleaMarket3 = new FleaMarket();
            fleaMarket3.setTitle("Brocante jeudi Liège");
            fleaMarket3.setDescription("Brocante Liège jeudi 15 août\n" +
                    "placement dès 07h\n" +
                    "emplacement 5 mètres façade 4 mètres de profondeur avec véhicule à l arrière prix 12 euros\n" +
                    "Marché alimentaire, musique live\n" +
                    "Bar et snacks disponibles\n" +
                    "\n" +
                    "Réservation Jean");
            fleaMarket3.setAddress(new Address("Boulevard de la Sauvenière", zipCities.get(776)));
            fleaMarket3.setPhoneNumberEvent("0745 58 69 74");
            fleaMarket3.setPricePerMeter(12);
            fleaMarket3.setLocationPrice(5);
            fleaMarket3.setOrganizer(organizerList.get(2));
            fleaMarket3.setUrlPicture("https://www.quefaire.be/imgok/8578069_1.jpeg");
            fleaMarket3.setNumberOfPlaces(150);
            fleaMarket3.setDateBegin(LocalDate.of(2024, 8, 15));
            fleaMarket3.setDateEnd(LocalDate.of(2024, 8, 15));
            fleaMarket3.setShortDescription("Brocante Liège jeudi 15 août");
            fleaMarket3.setActiveDay(fleaMarket3.getActiveDay());
            fleaMarket3.setActive(true);

            FleaMarket fleaMarket4 = new FleaMarket();
            fleaMarket4.setTitle("Brocante vendredi Charleroi");
            fleaMarket4.setDescription("Brocante Charleroi vendredi 16 août\n" +
                    "placement dès 08h\n" +
                    "emplacement 7 mètres façade 6 mètres de profondeur avec véhicule à l arrière prix 18 euros\n" +
                    "Animations pour enfants, stands artisanaux\n" +
                    "Restauration sur place\n" +
                    "\n" +
                    "Réservation Laura");
            fleaMarket4.setAddress(new Address("Place Charles II", zipCities.get(1517)));
            fleaMarket4.setPhoneNumberEvent("0745 78 89 65");
            fleaMarket4.setPricePerMeter(18);
            fleaMarket4.setLocationPrice(7);
            fleaMarket4.setOrganizer(organizerList.get(0));
            fleaMarket4.setUrlPicture("https://www.quefaire.be/imgok/8578069_1.jpeg");
            fleaMarket4.setNumberOfPlaces(180);
            fleaMarket4.setDateBegin(LocalDate.of(2024, 8, 16));
            fleaMarket4.setDateEnd(LocalDate.of(2024, 8, 16));
            fleaMarket4.setShortDescription("Brocante Charleroi vendredi 16 août");
            fleaMarket4.setActiveDay(fleaMarket4.getActiveDay());
            fleaMarket4.setActive(true);

            FleaMarket fleaMarket5 = new FleaMarket();
            fleaMarket5.setTitle("Brocante samedi Bruxelles");
            fleaMarket5.setDescription("Brocante Bruxelles samedi 17 août\n" +
                    "placement dès 09h\n" +
                    "emplacement 9 mètres façade 7 mètres de profondeur avec véhicule à l arrière prix 22 euros\n" +
                    "Animations culturelles, stands divers\n" +
                    "Buffet et boissons sur place\n" +
                    "\n" +
                    "Réservation Vincent");
            fleaMarket5.setAddress(new Address("Grand Place", zipCities.get(1)));
            fleaMarket5.setPhoneNumberEvent("0745 98 10 36");
            fleaMarket5.setPricePerMeter(22);
            fleaMarket5.setLocationPrice(9);
            fleaMarket5.setOrganizer(organizerList.get(1));
            fleaMarket5.setUrlPicture("https://www.quefaire.be/imgok/8578069_1.jpeg");
            fleaMarket5.setNumberOfPlaces(220);
            fleaMarket5.setDateBegin(LocalDate.of(2024, 8, 17));
            fleaMarket5.setDateEnd(LocalDate.of(2024, 8, 17));
            fleaMarket5.setShortDescription("Brocante Bruxelles samedi 17 août");
            fleaMarket5.setActiveDay(fleaMarket5.getActiveDay());
            fleaMarket5.setActive(true);

            FleaMarket fleaMarket6 = new FleaMarket();
            fleaMarket6.setTitle("Brocante dimanche Mons");
            fleaMarket6.setDescription("Brocante Mons dimanche 18 août\n" +
                    "placement dès 10h\n" +
                    "emplacement 10 mètres façade 8 mètres de profondeur avec véhicule à l arrière prix 25 euros\n" +
                    "Spectacles de rue, food court\n" +
                    "Boissons disponibles\n" +
                    "\n" +
                    "Réservation Sophie");
            fleaMarket6.setAddress(new Address("Place du Parc", zipCities.get(115)));
            fleaMarket6.setPhoneNumberEvent("0745 12 34 56");
            fleaMarket6.setPricePerMeter(25);
            fleaMarket6.setLocationPrice(10);
            fleaMarket6.setOrganizer(organizerList.get(2));
            fleaMarket6.setUrlPicture("https://www.quefaire.be/imgok/8578069_1.jpeg");
            fleaMarket6.setNumberOfPlaces(250);
            fleaMarket6.setDateBegin(LocalDate.of(2024, 8, 18));
            fleaMarket6.setDateEnd(LocalDate.of(2024, 8, 18));
            fleaMarket6.setShortDescription("Brocante Mons dimanche 18 août");
            fleaMarket6.setActiveDay(fleaMarket6.getActiveDay());
            fleaMarket6.setActive(true);

            FleaMarket fleaMarket7 = new FleaMarket();
            fleaMarket7.setTitle("Brocante lundi Tournai");
            fleaMarket7.setDescription("Brocante Tournai lundi 19 août\n" +
                    "placement dès 11h\n" +
                    "emplacement 6 mètres façade 5 mètres de profondeur avec véhicule à l arrière prix 14 euros\n" +
                    "Stands d'antiquités, animations variées\n" +
                    "Restauration sur place\n" +
                    "\n" +
                    "Réservation Emma");
            fleaMarket7.setAddress(new Address("Grand Place", zipCities.get(2029)));
            fleaMarket7.setPhoneNumberEvent("0745 23 45 67");
            fleaMarket7.setPricePerMeter(14);
            fleaMarket7.setLocationPrice(6);
            fleaMarket7.setOrganizer(organizerList.get(2));
            fleaMarket7.setUrlPicture("https://www.quefaire.be/imgok/8578069_1.jpeg");
            fleaMarket7.setNumberOfPlaces(180);
            fleaMarket7.setDateBegin(LocalDate.of(2024, 8, 19));
            fleaMarket7.setDateEnd(LocalDate.of(2024, 8, 19));
            fleaMarket7.setShortDescription("Brocante Tournai lundi 19 août");
            fleaMarket7.setActiveDay(fleaMarket7.getActiveDay());
            fleaMarket7.setActive(true);

            FleaMarket fleaMarket8 = new FleaMarket();
            fleaMarket8.setTitle("Brocante mardi Bruges");
            fleaMarket8.setDescription("Brocante Bruges mardi 20 août\n" +
                    "placement dès 12h\n" +
                    "emplacement 7 mètres façade 6 mètres de profondeur avec véhicule à l arrière prix 17 euros\n" +
                    "Marché artisanal, musique locale\n" +
                    "Bars et restaurants à proximité\n" +
                    "\n" +
                    "Réservation Luc");
            fleaMarket8.setAddress(new Address("Grote Markt", zipCities.get(2216)));
            fleaMarket8.setPhoneNumberEvent("0745 34 56 78");
            fleaMarket8.setPricePerMeter(17);
            fleaMarket8.setLocationPrice(7);
            fleaMarket8.setOrganizer(organizerList.get(0));
            fleaMarket8.setUrlPicture("https://www.quefaire.be/imgok/8578069_1.jpeg");
            fleaMarket8.setNumberOfPlaces(200);
            fleaMarket8.setDateBegin(LocalDate.of(2024, 8, 20));
            fleaMarket8.setDateEnd(LocalDate.of(2024, 8, 20));
            fleaMarket8.setShortDescription("Brocante Bruges mardi 20 août");
            fleaMarket8.setActiveDay(fleaMarket8.getActiveDay());
            fleaMarket8.setActive(true);

            FleaMarket fleaMarket9 = new FleaMarket();
            fleaMarket9.setTitle("Brocante mercredi Anvers");
            fleaMarket9.setDescription("Brocante Anvers mercredi 21 août\n" +
                    "placement dès 13h\n" +
                    "emplacement 8 mètres façade 7 mètres de profondeur avec véhicule à l arrière prix 19 euros\n" +
                    "Stands internationaux, animations diverses\n" +
                    "Restauration sur place\n" +
                    "\n" +
                    "Réservation Paul");
            fleaMarket9.setAddress(new Address("Grote Markt", zipCities.get(268)));
            fleaMarket9.setPhoneNumberEvent("0745 45 67 89");
            fleaMarket9.setPricePerMeter(19);
            fleaMarket9.setLocationPrice(8);
            fleaMarket9.setOrganizer(organizerList.get(1));
            fleaMarket9.setUrlPicture("https://www.quefaire.be/imgok/8578069_1.jpeg");
            fleaMarket9.setNumberOfPlaces(230);
            fleaMarket9.setDateBegin(LocalDate.of(2024, 8, 21));
            fleaMarket9.setDateEnd(LocalDate.of(2024, 8, 21));
            fleaMarket9.setShortDescription("Brocante Anvers mercredi 21 août");
            fleaMarket9.setActiveDay(fleaMarket9.getActiveDay());
            fleaMarket9.setActive(true);

            FleaMarket fleaMarket10 = new FleaMarket();
            fleaMarket10.setTitle("Brocante jeudi Gand");
            fleaMarket10.setDescription("Brocante Gand jeudi 22 août\n" +
                    "placement dès 14h\n" +
                    "emplacement 9 mètres façade 8 mètres de profondeur avec véhicule à l arrière prix 21 euros\n" +
                    "Marché fermier, animations musicales\n" +
                    "Bars et restaurants à proximité\n" +
                    "\n" +
                    "Réservation Pierre");
            fleaMarket10.setAddress(new Address("Korenmarkt", zipCities.get(2463)));
            fleaMarket10.setPhoneNumberEvent("0745 56 78 90");
            fleaMarket10.setPricePerMeter(21);
            fleaMarket10.setLocationPrice(9);
            fleaMarket10.setOrganizer(organizerList.get(0));
            fleaMarket10.setUrlPicture("https://www.quefaire.be/imgok/8578069_1.jpeg");
            fleaMarket10.setNumberOfPlaces(240);
            fleaMarket10.setDateBegin(LocalDate.of(2024, 8, 22));
            fleaMarket10.setDateEnd(LocalDate.of(2024, 8, 22));
            fleaMarket10.setShortDescription("Brocante Gand jeudi 22 août");
            fleaMarket10.setActiveDay(fleaMarket10.getActiveDay());
            fleaMarket10.setActive(true);

            List<FleaMarket> fleaMarketList = List.of(fleaMarket1, fleaMarket2, fleaMarket3, fleaMarket4, fleaMarket5, fleaMarket6, fleaMarket7, fleaMarket8, fleaMarket9, fleaMarket10);
            fleaMarketRepository.saveAll(fleaMarketList);

        }
    }
}
