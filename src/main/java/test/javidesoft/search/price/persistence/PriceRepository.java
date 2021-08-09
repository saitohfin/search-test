package test.javidesoft.search.price.persistence;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PriceRepository extends JpaRepository<PriceEntity, Long> {
    @Query("select p from PriceEntity p where  productId= :productId and " +
               "p.brand.brandId = :brandId and p.startDate <= :date and p.endDate >= :date")
    Collection<PriceEntity> findValidPrices(@Param("productId") final String productId,
                                            @Param("brandId") final Long brandId,
                                            @Param("date") final Date date);
}
