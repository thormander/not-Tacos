package nottaco;

import java.util.List;
import lombok.Data;

@Data
public class Computer {
  private String name;
  private List<Hardware> hardware;
}
