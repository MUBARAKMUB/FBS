package CheckIn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
//@EnableMongoRepositories("CheckIn.Repository")
public class CheckInApplication {

	@Bean
	public RestTemplate getRestTemplate()
	{
	return new RestTemplate();
	}
	public static void main(String[] args) {
		SpringApplication.run(CheckInApplication.class, args);
	}

}
