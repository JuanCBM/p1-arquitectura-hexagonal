package es.urjc.code.ecommmerce.infrastructure.repository;

import es.urjc.code.ecommmerce.infrastructure.model.ShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartJpaRepository extends JpaRepository<ShoppingCartEntity, Long> {

  @Modifying
  @Query(value = "UPDATE shopping_cart SET completed = true WHERE id = :id", nativeQuery = true)
  void endShoppingCart(@Param("id") long id);
}
