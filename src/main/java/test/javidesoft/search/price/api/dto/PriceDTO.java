package test.javidesoft.search.price.api.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor()
@NoArgsConstructor()
public class PriceDTO {

    private String productId;
    private String brandId;
    private String priceList;
    private Date startDate;
    private Date endDate;
    private Double price;
}
