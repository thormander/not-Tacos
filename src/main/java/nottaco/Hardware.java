package nottaco;

import lombok.Data;

@Data
public class Hardware {
  
  private final String id;
  private final String name;
  private final Type type;
  
  public enum Type {
    WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
  }

}
