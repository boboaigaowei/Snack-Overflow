package group.boboaigaowei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SnackOverflowApplication {

    public static void main(String[] args) {
        SpringApplication.run(SnackOverflowApplication.class, args);
    }

}
