package proyecto.API_Inicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApiInicioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiInicioApplication.class, args);
	}

}
