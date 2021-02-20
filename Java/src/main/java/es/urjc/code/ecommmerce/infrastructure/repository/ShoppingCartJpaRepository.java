package es.urjc.code.ecommmerce.infrastructure.repository;

import es.urjc.code.ecommmerce.infrastructure.model.ShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartJpaRepository extends JpaRepository<ShoppingCartEntity, Long> {

}
