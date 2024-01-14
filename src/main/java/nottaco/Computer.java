package nottaco;

import java.util.List;
import jakarta.validation.constraints.NotNull; // note: javax changed to jakarta
import jakarta.validation.constraints.Size; 
import lombok.Data;



@Data
public class Computer {
    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;
  
    @NotNull
    @Size(min=1, message="You must choose at least 1 component")
    private List<Hardware> hardware;
}
