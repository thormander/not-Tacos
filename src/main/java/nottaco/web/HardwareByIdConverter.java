package nottaco.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import nottaco.Hardware;
import nottaco.data.HardwareRepository;


@Component
public class HardwareByIdConverter implements Converter<String, Hardware> {

  private HardwareRepository hardwareRepo;

  @Autowired
  public HardwareByIdConverter(HardwareRepository hardwareRepo) {
    this.hardwareRepo = hardwareRepo;
  }

  @Override
  public Hardware convert(String id) {
    return hardwareRepo.findById(id).orElse(null);
  }

}