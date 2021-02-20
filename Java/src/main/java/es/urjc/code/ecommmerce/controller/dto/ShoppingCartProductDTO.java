package es.urjc.code.ecommmerce.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartProductDTO {

  Long id;
  Long quantity;
}
