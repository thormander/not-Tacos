package nottaco.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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

import jakarta.validation.Valid; // note: javax changed to jakarta
import org.springframework.validation.Errors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("computerOrder")
public class DesignComputerController {

@ModelAttribute
public void addHardwaresToModel(Model model) {
	List<Hardware> Hardwares = Arrays.asList(
	  new Hardware("MATX", "mATX", Type.CASE),
	  new Hardware("IITX", "ITX", Type.CASE),
	  new Hardware("INTL", "Intel", Type.CPU),
	  new Hardware("CAMD", "AMD", Type.CPU),
	  new Hardware("NVDA", "Nvidia", Type.GPU),
	  new Hardware("GAMD", "AMD", Type.GPU),
	  new Hardware("NORM", "1 TB", Type.STORAGE),
	  new Hardware("CRZY", "100 TB", Type.STORAGE),
	  new Hardware("AAIR", "Air", Type.COOLING),
	  new Hardware("WATR", "Water", Type.COOLING)
	);

	Type[] types = Hardware.Type.values();
	for (Type type : types) {
	  model.addAttribute(type.toString().toLowerCase(),
	      filterByType(Hardwares, type));
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

/*
  @PostMapping
  public String processComputer(Computer taco,
  			@ModelAttribute ComputerOrder tacoOrder) {
    tacoOrder.addComputer(taco);
    log.info("Processing taco: {}", taco);

    return "redirect:/orders/current";
  }
 */

 @PostMapping
 public String processComputer(
         @Valid Computer computer, Errors errors,
         @ModelAttribute ComputerOrder computerOrder) {

   if (errors.hasErrors()) {
     return "design";
   }

   computerOrder.addComputer(computer);
   log.info("Processing computer: {}", computer);

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