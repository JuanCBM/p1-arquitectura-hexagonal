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
public class ShoppingCartProductId implements Serializable {

  private Long shoppingCartId;
  private Long productId;

}
