package farm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import farm.repository.AnimalRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses= {AnimalRepository.class})
public class FarmApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmApplication.class, args);
	}

}
