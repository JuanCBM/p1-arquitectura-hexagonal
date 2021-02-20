package es.urjc.code.ecommmerce.infrastructure.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  boolean validated;
  boolean completed;
  String ownerName;

  @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ProductShoppingCartEntity> products = new ArrayList<>();
  
}
