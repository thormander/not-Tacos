package nottaco.data;

import java.util.Optional;

import nottaco.ComputerOrder;

public interface OrderRepository {

    ComputerOrder save(ComputerOrder order);
  
    Optional<ComputerOrder> findById(Long id);
  
  }
