package test.javidesoft.search.price.api;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import test.javidesoft.search.price.api.dto.PriceDTO;

public interface PriceEndpoint {

    String BASE_URL = "/price";

    @GetMapping(BASE_URL + "/{productId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    PriceDTO getPrice(
        @PathVariable("productId") final String productId,
        @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime date,
        @RequestParam("brandId") final Long brandId
    );
}
