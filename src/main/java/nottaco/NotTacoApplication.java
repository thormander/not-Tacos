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
      repo.save(new Hardware("IITX", "ITX", Type.CASE));
      repo.save(new Hardware("MATX", "mATX", Type.CASE));
      repo.save(new Hardware("WATR", "Water", Type.COOLING));
      repo.save(new Hardware("AAIR", "Air", Type.COOLING));
      repo.save(new Hardware("CAMD", "AMD", Type.CPU));
      repo.save(new Hardware("INTL", "Intel", Type.CPU));
      repo.save(new Hardware("GAMD", "AMD", Type.GPU));
      repo.save(new Hardware("NVDA", "Nvidia", Type.GPU));
      repo.save(new Hardware("NRML", "1 TB", Type.STORAGE));
      repo.save(new Hardware("CRZY", "100 TB", Type.STORAGE));
    };
  }

}