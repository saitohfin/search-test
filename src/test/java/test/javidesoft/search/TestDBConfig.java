package test.javidesoft.search;

import javax.annotation.PostConstruct;

import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration
public class TestDBConfig {

    @PostConstruct
    public void setUp(){
        System.out.println("");
    }
}
