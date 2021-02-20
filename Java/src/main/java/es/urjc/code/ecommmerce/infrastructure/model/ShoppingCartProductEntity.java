package es.urjc.code.ecommmerce.infrastructure.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = ShoppingCartProductEntity.TABLE_NAME)
public class ShoppingCartProductEntity {

  public static final String TABLE_NAME = "shopping_cart_product";

  @EmbeddedId
  private ShoppingCartProductId id;

  @ManyToOne
  @MapsId("shoppingCartId")
  private ShoppingCartEntity shoppingCart;

  @ManyToOne
  @MapsId("productId")
  private ProductEntity product;

  @Column(nullable = false)
  private long quantity;

  public ShoppingCartProductEntity(ShoppingCartEntity shoppingCart, ProductEntity product,
      long quantity) {
    this.product = product;
    this.shoppingCart = shoppingCart;
    this.quantity = quantity;

    this.id = new ShoppingCartProductId(shoppingCart.getId(), product.getId());
  }

}
