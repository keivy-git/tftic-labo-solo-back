package be.keivy.fleamarketapp.infrastructure.data_init;

import be.keivy.fleamarketapp.dal.repositories.OrganizerRepository;
import be.keivy.fleamarketapp.dal.repositories.RoleRepository;
import be.keivy.fleamarketapp.dal.repositories.SecondHandDealerRepository;
import be.keivy.fleamarketapp.dal.repositories.ZipCityRepository;
import be.keivy.fleamarketapp.domain.entities.*;
import be.keivy.fleamarketapp.infrastructure.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Order(1)
public class UserInit implements CommandLineRunner {

    private final SecondHandDealerRepository secondHandDealerRepository;
    private final OrganizerRepository organizerRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ZipCityRepository zipCityRepository;

    @Override
    public void run(String... args) throws Exception {

        if (secondHandDealerRepository.count() == 0 && organizerRepository.count() == 0) {
            List<Role> userRoles = roleRepository.findAll();

            Role adminRole = userRoles.stream()
                    .filter(role -> role.getName().equals(Constants.ADMIN_ROLE))
                    .findFirst()
                    .orElseThrow();

            Role secondHandDealerRole = userRoles.stream()
                    .filter(role -> role.getName().equals(Constants.SECOND_HAND_DEALER_ROLE))
                    .findFirst()
                    .orElseThrow();

            Role organizerRole = userRoles.stream()
                    .filter(role -> role.getName().equals(Constants.ORGANIZER_ROLE))
                    .findFirst()
                    .orElseThrow();

            /**
             * Brocanteur 1
             */
            SecondHandDealer secondHandDealer1 = new SecondHandDealer();
            secondHandDealer1.setEmail("adminsecondhanddealer1@mail.com");
            secondHandDealer1.setPassword(passwordEncoder.encode("password123"));
            secondHandDealer1.setFirstName("Goku");
            secondHandDealer1.setLastName("Son");
            secondHandDealer1.setPhoneNumber("0488 56 32 12");
            secondHandDealer1.setRoles(Set.of(adminRole, secondHandDealerRole));
            secondHandDealer1.setBirthDate(LocalDate.of(1990, 10, 8));

            /**
             * Brocanteur 2
             */
            SecondHandDealer secondHandDealer2 = new SecondHandDealer();
            secondHandDealer2.setEmail("secondhanddealer2@mail.com");
            secondHandDealer2.setPassword(passwordEncoder.encode("password123"));
            secondHandDealer2.setFirstName("Vegata");
            secondHandDealer2.setLastName("Prince");
            secondHandDealer2.setPhoneNumber("0475 23 64 25 ");
            secondHandDealer2.setRoles(Set.of(secondHandDealerRole));
            secondHandDealer2.setBirthDate(LocalDate.of(1990, 4, 25));

            /**
             * Brocanteur 3
             */
            SecondHandDealer secondHandDealer3 = new SecondHandDealer();
            secondHandDealer3.setEmail("secondhanddealer3@mail.com");
            secondHandDealer3.setPassword(passwordEncoder.encode("password123"));
            secondHandDealer3.setFirstName("Gohan");
            secondHandDealer3.setLastName("Son");
            secondHandDealer3.setPhoneNumber("0474 74 74 74");
            secondHandDealer3.setRoles(Set.of(secondHandDealerRole));
            secondHandDealer3.setBirthDate(LocalDate.of(1999, 4, 25));

            List<ZipCity> zipCities = zipCityRepository.findAll();

            /**
             * generator register national belge
             * https://rsolution.be/rijksregister-nummer-generator.RSolution?year=1985&month=02&day=15&teller=&man=1
             */
            /**
             * Organisateur 1
             */
            Organizer organizer1 = new Organizer();
            organizer1.setEmail("adminorganizer1@mail.com");
            organizer1.setPassword(passwordEncoder.encode("password123"));
            organizer1.setFirstName("Jim");
            organizer1.setLastName("Nastik");
            organizer1.setPhoneNumber("0462 25 36 35");
            organizer1.setRoles(Set.of(adminRole, organizerRole));
            organizer1.setAddress(new Address("2, Rue des branches", zipCities.get(1)));
            organizer1.setOrganizationName("Les petits lutins à la plage");
            organizer1.setOrganizationPhone("02 52 63 54");
            organizer1.setNationalRegister("85.02.15-001.87");

            /**
             * Organisateur 2
             */
            Organizer organizer2 = new Organizer();
            organizer2.setEmail("organizer2@mail.com");
            organizer2.setPassword(passwordEncoder.encode("password123"));
            organizer2.setFirstName("Jean");
            organizer2.setLastName("Boon");
            organizer2.setPhoneNumber("0475 36 52 36");
            organizer2.setRoles(Set.of(organizerRole));
            organizer2.setAddress(new Address("26, rue de la boucherie", zipCities.get(1517)));
            organizer2.setOrganizationName("Association Dominique");
            organizer2.setOrganizationPhone("071 25 36 45");
            organizer2.setNationalRegister("90.10.08-001.70");

            /**
             * Organisateur 3
             */
            Organizer organizer3 = new Organizer();
            organizer3.setEmail("organizer3@mail.com");
            organizer3.setPassword(passwordEncoder.encode("password123"));
            organizer3.setFirstName("Julia");
            organizer3.setLastName("Nastik");
            organizer3.setPhoneNumber("0488 36 36 36");
            organizer3.setRoles(Set.of(adminRole, organizerRole));
            organizer3.setAddress(new Address("2, Rue des branches", zipCities.get(1144)));
            organizer3.setOrganizationName("");
            organizer3.setOrganizationPhone("");
            organizer3.setNationalRegister("93.05.25-002.72");

            List<Organizer> organizerList = List.of(organizer1, organizer2, organizer3);
            List<SecondHandDealer> secondHandDealersList = List.of(secondHandDealer1, secondHandDealer2, secondHandDealer3);

            organizerRepository.saveAll(organizerList);
            secondHandDealerRepository.saveAll(secondHandDealersList);
        }
    }
}