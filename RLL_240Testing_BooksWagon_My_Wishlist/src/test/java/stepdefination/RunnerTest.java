//package stepdefination;
//
//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
//import org.junit.runner.RunWith;
//
//@RunWith(Cucumber.class)
//@CucumberOptions(
//        features = "src\\test\\resources\\features",
//        glue = "stepdefination", // Adjust to your package
//        plugin = {"pretty", "html:target/cucumber-reports.html"}
//)
//public class RunnerTest {
//	
//}
//

package stepdefination;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = { "src\\test\\resources\\features\\WishlistFeature.feature", "src\\test\\resources\\features\\WishListFeature2.feature"},
        glue = "stepdefination", // Adjust to your package
        plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class RunnerTest extends AbstractTestNGCucumberTests {
}

