package lotte.newdevps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class NewdevpsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewdevpsApplication.class, args);
	}

}
