package es.urjc.code.ecommmerce.infrastructure.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = ProductEntity.TABLE_NAME)
public class ProductEntity {

  public static final String TABLE_NAME = "product";

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  String name;
  String description;

}
