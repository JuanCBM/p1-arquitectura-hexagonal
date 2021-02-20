package es.urjc.code.ecommmerce.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ShoppingCartUpdateRequestDTO {

  Long id;
  boolean completed;

}
