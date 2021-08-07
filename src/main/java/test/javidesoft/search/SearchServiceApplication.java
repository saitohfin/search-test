package test.javidesoft.search;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SearchServiceApplication {

    public static void main(final String[] args) {
        SpringApplication.run(SearchServiceApplication.class, args);
    }

    @Bean
    public ModelMapper initModelMapper() {
        return new ModelMapper();
    }

}
