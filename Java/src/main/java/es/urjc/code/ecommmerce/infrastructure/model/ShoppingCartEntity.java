package es.urjc.code.ecommmerce.infrastructure.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = ShoppingCartEntity.TABLE_NAME)
public class ShoppingCartEntity {

  public static final String TABLE_NAME = "shopping_cart";

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  boolean validated;
  boolean completed;
  String ownerName;

  @ManyToMany(cascade = {CascadeType.ALL})
  @JoinTable(name = "shopping_cart_product",
      joinColumns = @JoinColumn(name = "shopping_cart_id"),
      inverseJoinColumns = @JoinColumn(name = "product_id"))
  private List<ProductEntity> products = new ArrayList<>();

}
