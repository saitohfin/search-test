package test.javidesoft.search.price.controller;

import java.text.DecimalFormat;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Configuration;

import test.javidesoft.search.price.api.dto.PriceDTO;
import test.javidesoft.search.price.persistence.PriceEntity;

@Configuration
public class PriceMappingConfiguration {

    private final ModelMapper modelMapper;
    private final DecimalFormat format;

    public PriceMappingConfiguration(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        format = new DecimalFormat("0.00");

    }

    @PostConstruct
    public ModelMapper configure() {
        this.entityToPriceDTO();
        return this.modelMapper;
    }

    private void entityToPriceDTO() {
        final TypeMap<PriceEntity, PriceDTO> mapping = this.modelMapper
            .createTypeMap(PriceEntity.class, PriceDTO.class);
        mapping.addMapping(PriceEntity::getPriceId, PriceDTO::setPriceList);
    }
}
