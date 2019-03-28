package utcn.labs.sd.bankingservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    /**
     * Default constructor
     */
    public Application() {
        // needed by Spring
    }

    /**
     * Main method for initialization the application
     *
     * @param args can not be null
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
