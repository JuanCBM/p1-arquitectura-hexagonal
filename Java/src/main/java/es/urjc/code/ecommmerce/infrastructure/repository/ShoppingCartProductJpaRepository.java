package es.urjc.code.ecommmerce.infrastructure.repository;

import es.urjc.code.ecommmerce.infrastructure.model.ShoppingCartProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartProductJpaRepository extends
    JpaRepository<ShoppingCartProductEntity, Long> {
  
}
