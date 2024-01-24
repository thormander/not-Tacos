package nottaco.data;

import org.springframework.data.repository.CrudRepository;

import nottaco.ComputerOrder;

public interface OrderRepository extends CrudRepository<ComputerOrder, Long> {

}
