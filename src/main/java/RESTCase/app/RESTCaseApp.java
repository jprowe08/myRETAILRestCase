package RESTCase.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main entry point for the spring boot application.
 * @author James
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = { "RESTCase.app" })
public class RESTCaseApp {

    public static void main(String[] args) {
        SpringApplication.run(RESTCaseApp.class, args);
    }

}