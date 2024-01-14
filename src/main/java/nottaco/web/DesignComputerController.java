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

import javax.validation.Valid;
import org.springframework.validation.Errors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignComputerController {

@ModelAttribute
public void addHardwaresToModel(Model model) {
	List<Hardware> Hardwares = Arrays.asList(
	  new Hardware("FLTO", "Flour Tortilla", Type.WRAP),
	  new Hardware("COTO", "Corn Tortilla", Type.WRAP),
	  new Hardware("GRBF", "Ground Beef", Type.PROTEIN),
	  new Hardware("CARN", "Carnitas", Type.PROTEIN),
	  new Hardware("TMTO", "Diced Tomatoes", Type.VEGGIES),
	  new Hardware("LETC", "Lettuce", Type.VEGGIES),
	  new Hardware("CHED", "Cheddar", Type.CHEESE),
	  new Hardware("JACK", "Monterrey Jack", Type.CHEESE),
	  new Hardware("SLSA", "Salsa", Type.SAUCE),
	  new Hardware("SRCR", "Sour Cream", Type.SAUCE)
	);

	Type[] types = Hardware.Type.values();
	for (Type type : types) {
	  model.addAttribute(type.toString().toLowerCase(),
	      filterByType(Hardwares, type));
	}
  }

  @ModelAttribute(name = "tacoOrder")
  public ComputerOrder order() {
    return new ComputerOrder();
  }

  @ModelAttribute(name = "taco")
  public Computer taco() {
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
		  @Valid Computer taco, Errors errors,
		  @ModelAttribute ComputerOrder tacoOrder) {

    if (errors.hasErrors()) {
      return "design";
    }

