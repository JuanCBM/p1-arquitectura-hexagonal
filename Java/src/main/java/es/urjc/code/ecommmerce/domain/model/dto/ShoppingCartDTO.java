package es.urjc.code.ecommmerce.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDTO {

  Long id;
  boolean validated;
  boolean completed;
  String ownerName;

}
