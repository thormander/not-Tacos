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
    ingredientMap.put("MATX", 
        new Hardware("MATX", "mATX", Type.CASE));
    ingredientMap.put("IITX", 
        new Hardware("IITX", "ITX", Type.CASE));
    ingredientMap.put("INTL", 
        new Hardware("INTL", "Intel", Type.CPU));
    ingredientMap.put("CAMD", 
        new Hardware("CAMD", "AMD", Type.CPU));
    ingredientMap.put("NVDA", 
        new Hardware("NVDA", "Nvidia", Type.GPU));
    ingredientMap.put("GAMD", 
        new Hardware("GAMD", "AMD", Type.GPU));
    ingredientMap.put("NORM", 
        new Hardware("NORM", "1 TB", Type.STORAGE));
    ingredientMap.put("CRZY", 
        new Hardware("CRZY", "100 TB", Type.STORAGE));
    ingredientMap.put("AAIR", 
        new Hardware("AAIR", "Air", Type.COOLING));
    ingredientMap.put("WATR", 
        new Hardware("WATR", "Water", Type.COOLING));
  }
  
  @Override
  public Hardware convert(String id) {
    return ingredientMap.get(id);
  }

}