package nottaco;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
public class Hardware {

  @Id
  private String id;
  private String name;
  private Type type;

  public enum Type {
    CASE, CPU, GPU, STORAGE, COOLING
  }

}
