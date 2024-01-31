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
@SessionAttributes("computerOrder")
public class DesignComputerController {

  private final HardwareRepository hardwareRepo;

  @Autowired
  public DesignComputerController(
        HardwareRepository hardwareRepo) {
    this.hardwareRepo = hardwareRepo;
  }

  @ModelAttribute
  public void addHardwaresToModel(Model model) {
    List<Hardware> hardwares = new ArrayList<>();
    hardwareRepo.findAll().forEach(i -> hardwares.add(i));

    Type[] types = Hardware.Type.values();
    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(),
          filterByType(hardwares, type));
    }
  }

  @ModelAttribute(name = "computerOrder")
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
      @ModelAttribute ComputerOrder computerOrder) {

    if (errors.hasErrors()) {
      return "design";
    }

    computerOrder.addComputer(computer);

    return "redirect:/orders/current";
  }

  private Iterable<Hardware> filterByType(
      List<Hardware> hardwares, Type type) {
    return hardwares
              .stream()
              .filter(x -> x.getType().equals(type))
              .collect(Collectors.toList());
  }

}