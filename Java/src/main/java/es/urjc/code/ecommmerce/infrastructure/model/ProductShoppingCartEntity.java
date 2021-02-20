package es.urjc.code.ecommmerce.infrastructure.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ProductShoppingCartEntity {

  @EmbeddedId
  private ProductShoppingCartEntityId id;

  @ManyToOne
  @MapsId("shoppingCartId")
  private ShoppingCartEntity shoppingCart;

  @ManyToOne
  @MapsId("productId")
  private ProductEntity product;

  public ProductShoppingCartEntity(ProductEntity product, ShoppingCartEntity shoppingCart) {
    this.product = product;
    this.shoppingCart = shoppingCart;

    this.id = new ProductShoppingCartEntityId(product.getId(), shoppingCart.getId());
  }

}
