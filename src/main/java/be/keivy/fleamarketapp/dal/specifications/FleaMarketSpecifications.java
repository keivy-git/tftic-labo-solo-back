package be.keivy.fleamarketapp.dal.specifications;

import be.keivy.fleamarketapp.domain.entities.Address;
import be.keivy.fleamarketapp.domain.entities.FleaMarket;
import be.keivy.fleamarketapp.domain.entities.ZipCity;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

public interface FleaMarketSpecifications {

    static Specification<FleaMarket> filterByParams(Map<String, String> params) {
        Specification<FleaMarket> specification = Specification.where(null);

        for (Map.Entry<String, String> entry : params.entrySet()) {
            specification = specification.and(filterBy(entry.getKey(), entry.getValue()));
        }
        return specification;
    }
    static Specification<FleaMarket> filterBy(String key, String value) {
        return (root, query, criteriaBuilder) ->
                switch (key) {
                    case "title" -> criteriaBuilder.equal(root.get("title"), String.valueOf(value));
                    case "dateBegin" -> criteriaBuilder.lessThanOrEqualTo(root.get("dateBegin"), Integer.parseInt(value));
                    case "zip" -> {
                        Join<FleaMarket, Address> addressJoin = root.join("address");
                        Join<Address, ZipCity> zip = addressJoin.join("zipCity");
                        yield criteriaBuilder.like(zip.get("zip"), "%" + value + "%");
                    }
                    case "city" -> {
                        Join<FleaMarket, Address> addressJoin = root.join("address");
                        Join<Address, ZipCity> city = addressJoin.join("zipCity");
                        yield criteriaBuilder.like(city.get("city"), "%" + value + "%");
                    }
                    default -> null;
                };
    }
}
