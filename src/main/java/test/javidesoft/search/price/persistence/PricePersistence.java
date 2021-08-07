package test.javidesoft.search.price.persistence;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
public class PricePersistence {

    private final PriceRepository repository;

    public PricePersistence(final PriceRepository repository) {
        this.repository = repository;
    }

    public Collection<PriceEntity> findPrice(final String productId, final Long brandId, final LocalDateTime date) {
        return repository
            .findPriceMostPriority(productId, brandId, Date.from(date.atZone(ZoneId.systemDefault()).toInstant()));
    }

    @Transactional
    public PriceEntity save(final PriceEntity entity) {
        return this.repository.save(entity);
    }
}
