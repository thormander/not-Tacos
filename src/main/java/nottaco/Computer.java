package nottaco;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class Computer {

  @NotNull
  @Size(min=5, message="Name must be at least 5 characters long")
  private String name;

  private Date createdAt = new Date();

  @Size(min=1, message="You must choose at least 1 hardware")
  private List<Hardware> hardwares = new ArrayList<>();
  
  public void addHardware(Hardware hardware) {
    this.hardwares.add(hardware);
  }

}