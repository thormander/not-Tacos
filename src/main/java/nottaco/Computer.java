package nottaco;

import java.util.ArrayList;
import java.util.Date;
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
    private List<HardwareRef> hardwares = new ArrayList<>();

    private Long id;
    private Date createdAt = new Date();
    public void addHardware(Hardware computer) {
        this.hardwares.add(new HardwareRef(computer.getId()));
    }
}
