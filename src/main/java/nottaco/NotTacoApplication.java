package nottaco;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import nottaco.Hardware.Type;
import nottaco.data.HardwareRepository;

@SpringBootApplication
public class NotTacoApplication {

  public static void main(String[] args) {
    SpringApplication.run(NotTacoApplication.class, args);
  }

  @Bean
  public CommandLineRunner dataLoader(HardwareRepository repo) {
    return args -> {
      repo.deleteAll(); // TODO: Quick hack to avoid tests from stepping on each other with constraint violations
      repo.save(new Hardware("F", "Flour Tortilla", Type.CASE));
      repo.save(new Hardware("COTO", "Corn Tortilla", Type.CASE));
      repo.save(new Hardware("GRBF", "Ground Beef", Type.COOLING));
      repo.save(new Hardware("CARN", "Carnitas", Type.COOLING));
      repo.save(new Hardware("TMTO", "Diced Tomatoes", Type.CPU));
      repo.save(new Hardware("LETC", "Lettuce", Type.CPU));
      repo.save(new Hardware("CHED", "Cheddar", Type.GPU));
      repo.save(new Hardware("JACK", "Monterrey Jack", Type.GPU));
      repo.save(new Hardware("SLSA", "Salsa", Type.STORAGE));
      repo.save(new Hardware("SRCR", "Sour Cream", Type.STORAGE));
    };
  }

}