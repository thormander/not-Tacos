package nottaco;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
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
