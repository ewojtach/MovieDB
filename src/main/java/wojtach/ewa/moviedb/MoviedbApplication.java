package wojtach.ewa.moviedb;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MoviedbApplication {

	public static void main(String[] args) {

//		ApplicationContext appContext = new AnnotationConfigApplictionContext(UserAccountConfig.class);
		SpringApplication.run(MoviedbApplication.class, args);
	}
}
