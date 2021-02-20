package es.urjc.code.ecommmerce.infrastructure.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductShoppingCartEntityId implements Serializable {

  private Long productId;
  private Long shoppingCartId;

}