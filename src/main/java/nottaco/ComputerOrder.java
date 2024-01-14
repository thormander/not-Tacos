package nottaco;

import java.util.List;
import java.util.ArrayList;
import lombok.Data;

@Data
public class ComputerOrder {
  private String deliveryName;
  private String deliveryStreet;
  private String deliveryCity;
  private String deliveryState;
  private String deliveryZip;
  private String ccNumber;
  private String ccExpiration;
  private String ccCVV;
  private List<Computer> computers = new ArrayList<>();
  public void addTaco(Computer computer) {
    this.computers.add(computer);
} }