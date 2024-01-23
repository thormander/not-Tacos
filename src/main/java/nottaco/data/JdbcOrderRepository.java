package nottaco.data;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.asm.Type;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import nottaco.HardwareRef;
import nottaco.Computer;
import nottaco.ComputerOrder;

@Repository
public class JdbcOrderRepository implements OrderRepository {

  private JdbcOperations jdbcOperations;

  public JdbcOrderRepository(JdbcOperations jdbcOperations) {
    this.jdbcOperations = jdbcOperations;
  }

  @Override
  @Transactional
  public ComputerOrder save(ComputerOrder order) {
    PreparedStatementCreatorFactory pscf =
      new PreparedStatementCreatorFactory(
        "insert into Taco_Order "
        + "(delivery_name, delivery_street, delivery_city, "
        + "delivery_state, delivery_zip, cc_number, "
        + "cc_expiration, cc_cvv, placed_at) "
        + "values (?,?,?,?,?,?,?,?,?)",
        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
        Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
    );
    pscf.setReturnGeneratedKeys(true);

    order.setPlacedAt(new Date());
    PreparedStatementCreator psc =
        pscf.newPreparedStatementCreator(
            Arrays.asList(
                order.getDeliveryName(),
                order.getDeliveryStreet(),
                order.getDeliveryCity(),
                order.getDeliveryState(),
                order.getDeliveryZip(),
                order.getCcNumber(),
                order.getCcExpiration(),
                order.getCcCVV(),
                order.getPlacedAt()));

    GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcOperations.update(psc, keyHolder);
    long orderId = keyHolder.getKey().longValue();
    order.setId(orderId);

    List<Computer> computers = order.getComputers();
    int i=0;
    for (Computer computer : computers) {
      saveComputer(orderId, i++, computer);
    }

    return order;
  }

  private long saveComputer(Long orderId, int orderKey, Computer computer) {
    computer.setCreatedAt(new Date());
    PreparedStatementCreatorFactory pscf =
            new PreparedStatementCreatorFactory(
        "insert into Computer "
        + "(name, created_at, computer_order, computer_order_key) "
        + "values (?, ?, ?, ?)",
        Types.VARCHAR, Types.TIMESTAMP, Type.LONG, Type.LONG
    );
    pscf.setReturnGeneratedKeys(true);

    PreparedStatementCreator psc =
        pscf.newPreparedStatementCreator(
            Arrays.asList(
                computer.getName(),
                computer.getCreatedAt(),
                orderId,
                orderKey));

    GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcOperations.update(psc, keyHolder);
    long computerId = keyHolder.getKey().longValue();
    computer.setId(computerId);

    saveHardwareRefs(computerId, computer.getHardwares());

    return computerId;
  }

  private void saveHardwareRefs(
      long computerId, List<HardwareRef> ingredientRefs) {
    int key = 0;
    for (HardwareRef ingredientRef : ingredientRefs) {
      jdbcOperations.update(
          "insert into Ingredient_Ref (ingredient, computer, taco_key) "
          + "values (?, ?, ?)",
          ingredientRef.getHardware(), computerId, key++);
    }
  }

  @Override
  public Optional<ComputerOrder> findById(Long id) {
    try {
      ComputerOrder order = jdbcOperations.queryForObject(
          "select id, delivery_name, delivery_street, delivery_city, "
              + "delivery_state, delivery_zip, cc_number, cc_expiration, "
              + "cc_cvv, placed_at from Taco_Order where id=?",
          (row, rowNum) -> {
            ComputerOrder ComputerOrder = new ComputerOrder();
            ComputerOrder.setId(row.getLong("id"));
            ComputerOrder.setDeliveryName(row.getString("delivery_name"));
            ComputerOrder.setDeliveryStreet(row.getString("delivery_street"));
            ComputerOrder.setDeliveryCity(row.getString("delivery_city"));
            ComputerOrder.setDeliveryState(row.getString("delivery_state"));
            ComputerOrder.setDeliveryZip(row.getString("delivery_zip"));
            ComputerOrder.setCcNumber(row.getString("cc_number"));
            ComputerOrder.setCcExpiration(row.getString("cc_expiration"));
            ComputerOrder.setCcCVV(row.getString("cc_cvv"));
            ComputerOrder.setPlacedAt(new Date(row.getTimestamp("placed_at").getTime()));
            ComputerOrder.setComputers(findComputersByOrderId(row.getLong("id")));
            return ComputerOrder;
          }, id);
      return Optional.of(order);
    } catch (IncorrectResultSizeDataAccessException e) {
      return Optional.empty();
    }
  }

  private List<Computer> findComputersByOrderId(long orderId) {
    return jdbcOperations.query(
        "select id, name, created_at from Computer "
        + "where computer_order=? order by computer_order_key",
        (row, rowNum) -> {
          Computer computer = new Computer();
          computer.setId(row.getLong("id"));
          computer.setName(row.getString("name"));
          computer.setCreatedAt(new Date(row.getTimestamp("created_at").getTime()));
          computer.setHardwares(findHardwaresByComputerId(row.getLong("id")));
          return computer;
        },
        orderId);
  }

  private List<HardwareRef> findHardwaresByComputerId(long computerId) {
    return jdbcOperations.query(
        "select hardware from Hardware_Ref "
        + "where computer = ? order by computer_key",
        (row, rowNum) -> {
          return new HardwareRef(row.getString("hardware"));
        },
        computerId);
  }

}