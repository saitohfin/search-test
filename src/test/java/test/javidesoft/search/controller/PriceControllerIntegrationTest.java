package test.javidesoft.search.controller;

import java.text.SimpleDateFormat;

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

import com.google.gson.Gson;

import test.javidesoft.search.TestDBConfig;
import test.javidesoft.search.price.api.dto.PriceDTO;

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
        final ResultActions result = this.mockMvc
            .perform(MockMvcRequestBuilders.get("/price/35455")
                .param("date", "2020-06-14T10:00:00")
                .param("brandId", "1")
            )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        final PriceDTO dtoResult = new Gson().fromJson(result.andReturn().getResponse().getContentAsString(),
            PriceDTO.class);
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final PriceDTO expected = PriceDTO.builder()
            .brandId("1")
            .productId("35455")
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
    public void test2() {
        Assertions.fail();
    }

    /**
     * Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
     */
    @Test
    public void test3() {
        Assertions.fail();
    }

    /**
     * Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
     */
    @Test
    public void test4() {
        Assertions.fail();
    }

    /**
     * Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
     */
    @Test
    public void test5() {
        Assertions.fail();
    }

}
