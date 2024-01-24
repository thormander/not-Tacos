package nottaco;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
public class Hardware implements Persistable<String> {

  @Id
  private String id;

  private String name;
  private Type type;

  @Override
	public boolean isNew() {
		return true;
	}

  public enum Type {
    CASE, CPU, GPU, STORAGE, COOLING
  }

}
