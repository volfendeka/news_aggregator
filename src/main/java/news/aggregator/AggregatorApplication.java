package news.aggregator;

import java.util.Arrays;
import java.util.Timer;

import news.aggregator.Service.FeedRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"news.aggregator.Entity"} )
@EnableJpaRepositories(basePackages = {"news.aggregator.Repository"})
public class AggregatorApplication {

	@Autowired
	public FeedRunner feedRunner;

	public static void main(String[] args) {
		SpringApplication.run(AggregatorApplication.class, args);
		System.out.println("start");
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			//Timer timer = new Timer();
			//timer.schedule(feedRunner, 0, 1800000);//10 Min

		};
	}

}
