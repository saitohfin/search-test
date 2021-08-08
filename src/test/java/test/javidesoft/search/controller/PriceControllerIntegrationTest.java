package test.javidesoft.search.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import test.javidesoft.search.TestDBConfig;
import test.javidesoft.search.price.api.PriceEndpoint;
import test.javidesoft.search.price.api.dto.PriceDTO;

import java.text.SimpleDateFormat;

@SpringBootTest
@Import({TestDBConfig.class})
@AutoConfigureMockMvc
public class PriceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
    }

    /**
     * Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
     */
    @Test
    public void test1() throws Exception {
        final String productId = "35455";
        final String date = "2020-06-14T10:00:00";
        final String brandId = "1";

        final ResultActions result = this.findPriceRequest(productId, date, brandId)
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        final PriceDTO dtoResult = new Gson().fromJson(result.andReturn().getResponse().getContentAsString(),
            PriceDTO.class);
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final PriceDTO expected = PriceDTO.builder()
            .brandId(brandId)
            .productId(productId)
            .priceList("1")
            .price(35.5d)
            .startDate(simpleDateFormat.parse("2020-06-14 00:00:00"))
            .endDate(simpleDateFormat.parse("2020-12-31 23:59:59"))
            .build();
        Assertions.assertEquals(expected, dtoResult);
    }

    /**
     * Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
     */
    @Test
    public void test2() throws Exception {
        final String productId = "35455";
        final String date = "2020-06-14T16:00:00";
        final String brandId = "1";

        final ResultActions result = this.findPriceRequest(productId, date, brandId)
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        final PriceDTO dtoResult = new Gson().fromJson(result.andReturn().getResponse().getContentAsString(),
            PriceDTO.class);
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final PriceDTO expected = PriceDTO.builder()
            .brandId(brandId)
            .productId(productId)
            .priceList("2")
            .price(25.45d)
            .startDate(simpleDateFormat.parse("2020-06-14 15:00:00"))
            .endDate(simpleDateFormat.parse("2020-06-14 18:30:00"))
            .build();
        Assertions.assertEquals(expected, dtoResult);
    }

    /**
     * Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
     */
    @Test
    public void test3() throws Exception {
        final String productId = "35455";
        final String date = "2020-06-14T21:00:00";
        final String brandId = "1";

        final ResultActions result = this.findPriceRequest(productId, date, brandId)
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        final PriceDTO dtoResult = new Gson().fromJson(result.andReturn().getResponse().getContentAsString(),
            PriceDTO.class);
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final PriceDTO expected = PriceDTO.builder()
            .brandId(brandId)
            .productId(productId)
            .priceList("1")
            .price(35.5d)
            .startDate(simpleDateFormat.parse("2020-06-14 00:00:00"))
            .endDate(simpleDateFormat.parse("2020-12-31 23:59:59"))
            .build();
        Assertions.assertEquals(expected, dtoResult);
    }

    /**
     * Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
     */
    @Test
    public void test4() throws Exception {
        final String productId = "35455";
        final String date = "2020-06-15T10:00:00";
        final String brandId = "1";

        final ResultActions result = this.findPriceRequest(productId, date, brandId)
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        final PriceDTO dtoResult = new Gson().fromJson(result.andReturn().getResponse().getContentAsString(),
            PriceDTO.class);
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final PriceDTO expected = PriceDTO.builder()
            .brandId(brandId)
            .productId(productId)
            .priceList("3")
            .price(30.5d)
            .startDate(simpleDateFormat.parse("2020-06-15 00:00:00"))
            .endDate(simpleDateFormat.parse("2020-06-15 11:00:00"))
            .build();
        Assertions.assertEquals(expected, dtoResult);
    }

    /**
     * Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
     */
    @Test
    public void test5() throws Exception {
        final String productId = "35455";
        final String date = "2020-06-16T21:00:00";
        final String brandId = "1";

        final ResultActions result = this.findPriceRequest(productId, date, brandId)
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        final PriceDTO dtoResult = new Gson().fromJson(result.andReturn().getResponse().getContentAsString(),
            PriceDTO.class);
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final PriceDTO expected = PriceDTO.builder()
            .brandId(brandId)
            .productId(productId)
            .priceList("4")
            .price(38.95d)
            .startDate(simpleDateFormat.parse("2020-06-15 16:00:00"))
            .endDate(simpleDateFormat.parse("2020-12-31 23:59:59"))
            .build();
        Assertions.assertEquals(expected, dtoResult);
    }

    /**
     * Given: Two valid prices with the same priority
     * When: Find the price to use
     * Then: Is returned the price with lowest price
     */
    @Test
    public void twoPricesWithSamePriorityIsTakenTheLowestPrice() throws Exception {
        final String productId = "35455";
        final String date = "2021-06-16T21:00:00";
        final String brandId = "1";

        final ResultActions result = this.findPriceRequest(productId, date, brandId)
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        final PriceDTO dtoResult = new Gson().fromJson(result.andReturn().getResponse().getContentAsString(),
                PriceDTO.class);
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final PriceDTO expected = PriceDTO.builder()
                .brandId(brandId)
                .productId(productId)
                .priceList("6")
                .price(10.0)
                .startDate(simpleDateFormat.parse("2021-06-15 16:00:00"))
                .endDate(simpleDateFormat.parse("2021-06-30 23:59:59"))
                .build();
        Assertions.assertEquals(expected, dtoResult);
    }

    /**
     * Given: A date without price
     * When: Find the price to use
     * Then: Is returned a 404
     */
    @Test
    public void requestAPriceWithAnInvalidDateReturns404() throws Exception {
        final String productId = "35455";
        final String date = "1998-06-16T21:00:00";
        final String brandId = "1";

        final ResultActions result = this.findPriceRequest(productId, date, brandId)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        final String body = result.andReturn().getResolvedException().getMessage();
        Assertions.assertEquals("Does not exist a price for this request", body);
    }

    /**
     * Given: A brand which doesnt exists
     * When: Find the price to use
     * Then: Is returned a 404
     */
    @Test
    public void requestAPriceWithAnBrandReturns404() throws Exception {
        final String productId = "35455";
        final String date = "2021-06-16T21:00:00";
        final String brandId = "99900";

        final ResultActions result = this.findPriceRequest(productId, date, brandId)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        final String body = result.andReturn().getResolvedException().getMessage();
        Assertions.assertEquals("Does not exist a price for this request", body);
    }

    /**
     * Given: A product which doesnt exists
     * When: Find the price to use
     * Then: Is returned a 404
     */
    @Test
    public void requestAPriceWithAnProductReturns404() throws Exception {
        final String productId = "999000";
        final String date = "2021-06-16T21:00:00";
        final String brandId = "1";

        final ResultActions result = this.findPriceRequest(productId, date, brandId)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        final String body = result.andReturn().getResolvedException().getMessage();
        Assertions.assertEquals("Does not exist a price for this request", body);
    }

    private ResultActions findPriceRequest(final String productId, final String date, final String brand)
        throws Exception {

        final String url = this.findPriceUrl(productId);
        return this.mockMvc.perform(MockMvcRequestBuilders.get(url)
            .param("date", date)
            .param("brandId", brand)
        ).andDo(MockMvcResultHandlers.print());
    }

    private String findPriceUrl(final String productId) {
        return PriceEndpoint.BASE_URL.concat("/").concat(productId);
    }
}
