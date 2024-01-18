package nottaco.data;

import java.util.Optional;
//import org.springframework.data.repository.CrudRepository;
import nottaco.Hardware;

public interface HardwareRepository {
    Iterable<Hardware> findAll();
    Optional<Hardware> findById(String id);
    Hardware save(Hardware ingredient);   
}
