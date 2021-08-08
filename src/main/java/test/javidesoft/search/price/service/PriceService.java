package test.javidesoft.search.price.service;

import org.springframework.stereotype.Service;
import test.javidesoft.search.exception.NotFoundException;
import test.javidesoft.search.price.persistence.PriceEntity;
import test.javidesoft.search.price.persistence.PricePersistence;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;

@Service
public class PriceService {

    private final PricePersistence persistence;

    public PriceService(final PricePersistence persistence) {
        this.persistence = persistence;
    }

    public PriceEntity findPrice(final String productId, final Long brandId, final LocalDateTime date) {

        final Collection<PriceEntity> compatiblePrices = this.persistence.findPrice(productId, brandId, date);
        return compatiblePrices.stream()
            .max(Comparator.comparingInt(PriceEntity::getPriority)
                .thenComparing(PriceEntity::getPrice, Comparator.reverseOrder()))
                .orElseThrow(() -> new NotFoundException("Does not exist a price for this request"));
    }
}
