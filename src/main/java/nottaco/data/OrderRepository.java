package nottaco.data;

import org.springframework.data.mongodb.repository.MongoRepository;

import nottaco.ComputerOrder;

public interface OrderRepository extends MongoRepository<ComputerOrder, String> {

}
