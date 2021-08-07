package test.javidesoft.search.price.controller;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RestController;

import test.javidesoft.search.price.api.PriceEndpoint;
import test.javidesoft.search.price.api.dto.PriceDTO;
import test.javidesoft.search.price.persistence.PriceEntity;
import test.javidesoft.search.price.service.PriceService;

@RestController
public class PriceController implements PriceEndpoint {

    private final PriceService service;
    private final ModelMapper modelMapper;

    public PriceController(final PriceService service, final ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @Override
    public PriceDTO getPrice(final String productId, final LocalDateTime date, final Long brandId) {
        final PriceEntity price = this.service.findPrice(productId, brandId, date);
        return this.modelMapper.map(price, PriceDTO.class);
    }
}
