package nottaco.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import nottaco.Hardware;
import nottaco.Hardware.Type;

@Component
public class HardwareByIdConverter implements Converter<String, Hardware> {

  private Map<String, Hardware> ingredientMap = new HashMap<>();
  
  public HardwareByIdConverter() {
    ingredientMap.put("FLTO", 
        new Hardware("FLTO", "Flour Tortilla", Type.WRAP));
    ingredientMap.put("COTO", 
        new Hardware("COTO", "Corn Tortilla", Type.WRAP));
    ingredientMap.put("GRBF", 
        new Hardware("GRBF", "Ground Beef", Type.PROTEIN));
    ingredientMap.put("CARN", 
        new Hardware("CARN", "Carnitas", Type.PROTEIN));
    ingredientMap.put("TMTO", 
        new Hardware("TMTO", "Diced Tomatoes", Type.VEGGIES));
    ingredientMap.put("LETC", 
        new Hardware("LETC", "Lettuce", Type.VEGGIES));
    ingredientMap.put("CHED", 
        new Hardware("CHED", "Cheddar", Type.CHEESE));
    ingredientMap.put("JACK", 
        new Hardware("JACK", "Monterrey Jack", Type.CHEESE));
    ingredientMap.put("SLSA", 
        new Hardware("SLSA", "Salsa", Type.SAUCE));
    ingredientMap.put("SRCR", 
        new Hardware("SRCR", "Sour Cream", Type.SAUCE));
  }
  
  @Override
  public Hardware convert(String id) {
    return ingredientMap.get(id);
  }

}