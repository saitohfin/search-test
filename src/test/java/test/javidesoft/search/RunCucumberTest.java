package test.javidesoft.search;

import org.junit.runner.RunWith;
import org.springframework.test.context.TestPropertySource;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {"pretty"},
    features = "src/test/resources/features",
    glue = {"com.finsoft.petdirectory.step"}
)
@TestPropertySource(locations = "/application-test.properties")
public class RunCucumberTest {
}
