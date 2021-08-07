package test.javidesoft.search.brand.persistence;

import org.springframework.stereotype.Repository;

@Repository
public class BrandPersistence {

    private BrandRepository repository;

    public BrandPersistence(final BrandRepository repository) {
        this.repository = repository;
    }

    public BrandEntity save(final BrandEntity brand) {
        return this.repository.save(brand);
    }
}
