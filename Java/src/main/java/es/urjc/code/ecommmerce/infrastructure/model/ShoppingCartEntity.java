package es.urjc.code.ecommmerce.infrastructure.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = ShoppingCartEntity.TABLE_NAME)
@Builder
public class ShoppingCartEntity {

  public static final String TABLE_NAME = "shopping_cart";

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  boolean completed;
  String ownerName;

  @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.MERGE, orphanRemoval = true)
  private List<ShoppingCartProductEntity> products = new ArrayList<>();
}
