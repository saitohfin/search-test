package test.javidesoft.search;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.boot.test.context.TestConfiguration;

import test.javidesoft.search.brand.persistence.BrandEntity;
import test.javidesoft.search.brand.persistence.BrandPersistence;
import test.javidesoft.search.price.persistence.PriceEntity;
import test.javidesoft.search.price.persistence.PricePersistence;

@TestConfiguration
public class TestDBConfig {

    private BrandPersistence brandPersistence;
    private PricePersistence pricePersistence;

    public TestDBConfig(final BrandPersistence brandPersistence,
                        final PricePersistence pricePersistence) {
        this.brandPersistence = brandPersistence;
        this.pricePersistence = pricePersistence;
    }

    @PostConstruct
    public void setUp() throws ParseException {
        final BrandEntity defaultBrand = this.saveBrand("ZARA");
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        final String defaultCurrency = "EUR";
        final String defaultProduct = "35455";
        this.saveAPriceEntity(defaultBrand, dateFormat.parse("2020-06-14 00:00:00"),
            dateFormat.parse("2020-12-31 23:59:59"),
            defaultProduct, 0, 35.50, defaultCurrency);

        this.saveAPriceEntity(defaultBrand, dateFormat.parse("2020-06-14 15:00:00"),
            dateFormat.parse("2020-06-14 18:30:00"),
            defaultProduct, 1, 25.45, defaultCurrency);

        this.saveAPriceEntity(defaultBrand, dateFormat.parse("2020-06-15 00:00:00"),
            dateFormat.parse("2020-06-15 11:00:00"),
            defaultProduct, 1, 35.50, defaultCurrency);

        this.saveAPriceEntity(defaultBrand, dateFormat.parse("2020-06-15 16:00:00"),
            dateFormat.parse("2020-12-31 23:59:59"),
            defaultProduct, 1, 35.50, defaultCurrency);
    }

    private BrandEntity saveBrand(final String name) {
        final BrandEntity brand = new BrandEntity();
        brand.setName(name);
        return this.brandPersistence.save(brand);
    }

    private void saveAPriceEntity(final BrandEntity brand, final Date startDate, final Date endDate,
                                  final String productId, final Integer priority, final Double price,
                                  final String currency) {
        final PriceEntity entity = new PriceEntity();
        entity.setBrand(brand);
        entity.setProductId(productId);
        entity.setStartDate(startDate);
        entity.setEndDate(endDate);
        entity.setPriority(priority);
        entity.setPrice(price);
        entity.setCurrency(currency);
        this.pricePersistence.save(entity);
    }
}
