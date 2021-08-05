package test.javidesoft.search;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductDTO {

    private String productId;
    private String brandId;
    private String priceList;
    private Date startDate;
    private Date endDate;
    private String price;
}
