package nottaco.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import nottaco.Hardware;
import nottaco.Hardware.Type;
import nottaco.data.HardwareRepository;

@Component
public class HardwareByIdConverter implements Converter<String, Hardware> {
    
    private HardwareRepository hardwareRepo;

    @Autowired
    public HardwareByIdConverter(HardwareRepository hardwareRepo) {
        this.hardwareRepo = hardwareRepo;
    }

    /* 
    public HardwareByIdConverter() {
    hardwareMap.put("MATX", 
        new Hardware("MATX", "mATX", Type.CASE));
    hardwareMap.put("IITX", 
        new Hardware("IITX", "ITX", Type.CASE));
    hardwareMap.put("INTL", 
        new Hardware("INTL", "Intel", Type.CPU));
    hardwareMap.put("CAMD", 
        new Hardware("CAMD", "AMD", Type.CPU));
    hardwareMap.put("NVDA", 
        new Hardware("NVDA", "Nvidia", Type.GPU));
    hardwareMap.put("GAMD", 
        new Hardware("GAMD", "AMD", Type.GPU));
    hardwareMap.put("NORM", 
        new Hardware("NORM", "1 TB", Type.STORAGE));
    hardwareMap.put("CRZY", 
        new Hardware("CRZY", "100 TB", Type.STORAGE));
    hardwareMap.put("AAIR", 
        new Hardware("AAIR", "Air", Type.COOLING));
    hardwareMap.put("WATR", 
        new Hardware("WATR", "Water", Type.COOLING));
    }
    */

    @Override
    public Hardware convert(String id) {
    return hardwareRepo.findById(id).orElse(null);
    }

}