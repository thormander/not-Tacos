package nottaco;

import java.util.List;

import org.hibernate.validator.constraints.CreditCardNumber;

import jakarta.validation.constraints.Digits; // note: javax changed to jakarta 
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import lombok.Data;

@Data
public class ComputerOrder implements Serializable {
  private static final long serialVersionUID = 1L;
  private Long id;
  private Date placedAt;

  @NotBlank(message="Delivery name is required")
  private String deliveryName;

  @NotBlank(message="Street is required")
  private String deliveryStreet;

  @NotBlank(message="City is required")
  private String deliveryCity;

  @NotBlank(message="State is required")
  private String deliveryState;

  @NotBlank(message="Zip code is required")
  private String deliveryZip;

  @CreditCardNumber(message="Not a valid credit card number")
  private String ccNumber;

  @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
           message="Must be formatted MM/YY")
  private String ccExpiration;

  @Digits(integer=3, fraction=0, message="Invalid CVV")
  private String ccCVV;

  private List<Computer> computers = new ArrayList<>();
  public void addComputer(Computer computer) {
    this.computers.add(computer);
} }