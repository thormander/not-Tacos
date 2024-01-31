package nottaco.data;

import org.springframework.data.mongodb.repository.MongoRepository;

import nottaco.Hardware;

public interface HardwareRepository extends MongoRepository<Hardware, String> {
  
}