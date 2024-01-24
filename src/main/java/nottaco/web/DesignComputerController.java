package nottaco.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import nottaco.Hardware;
import nottaco.Hardware.Type;
import nottaco.ComputerOrder;
import nottaco.Computer;
import nottaco.data.HardwareRepository;

@Controller
@RequestMapping("/design")
@SessionAttributes("ComputerOrder")
public class DesignComputerController {

  private final HardwareRepository HardwareRepo;

  @Autowired
  public DesignComputerController(
        HardwareRepository HardwareRepo) {
    this.HardwareRepo = HardwareRepo;
  }

  @ModelAttribute
  public void addHardwaresToModel(Model model) {
    List<Hardware> Hardwares = new ArrayList<>();
    HardwareRepo.findAll().forEach(i -> Hardwares.add(i));

    Type[] types = Hardware.Type.values();
    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(),
          filterByType(Hardwares, type));
    }
  }

  @ModelAttribute(name = "ComputerOrder")
  public ComputerOrder order() {
    return new ComputerOrder();
  }

  @ModelAttribute(name = "computer")
  public Computer computer() {
    return new Computer();
  }

  @GetMapping
  public String showDesignForm() {
    return "design";
  }

  @PostMapping
  public String processComputer(
      @Valid Computer computer, Errors errors,
      @ModelAttribute ComputerOrder ComputerOrder) {

    if (errors.hasErrors()) {
      return "design";
    }

    ComputerOrder.addComputer(computer);

    return "redirect:/orders/current";
  }

  private Iterable<Hardware> filterByType(
      List<Hardware> Hardwares, Type type) {
    return Hardwares
              .stream()
              .filter(x -> x.getType().equals(type))
              .collect(Collectors.toList());
  }

}