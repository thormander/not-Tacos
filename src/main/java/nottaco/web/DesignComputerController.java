package nottaco.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import nottaco.Hardware;
import nottaco.Hardware.Type;
import nottaco.Computer;
import nottaco.ComputerOrder;
import nottaco.data.HardwareRepository;

import jakarta.validation.Valid; // note: javax changed to jakarta
import org.springframework.validation.Errors;

@Slf4j
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
  public void addIngredientsToModel(Model model) {
    Iterable<Hardware> hardwares = hardwareRepo.findAll();
    Type[] types = Hardware.Type.values();
    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(), filterByType(hardwares, type));
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
  public String processComputer(@Valid Computer computer, Errors errors, @ModelAttribute ComputerOrder computerOrder) {
    if (errors.hasErrors()) {
      return "design";
    }

    computerOrder.addComputer(computer);
    log.info("Processing computer: {}", computer);

    return "redirect:/orders/current";
  }

  private Iterable<Hardware> filterByType(Iterable<Hardware> hardwares, Type type) {
    return StreamSupport.stream(hardwares.spliterator(), false)
              .filter(i -> i.getType().equals(type))
              .collect(Collectors.toList());
  }

}