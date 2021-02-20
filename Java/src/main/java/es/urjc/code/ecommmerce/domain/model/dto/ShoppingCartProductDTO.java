package es.urjc.code.ecommmerce.domain.model.dto;

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
