package es.urjc.code.ecommmerce.infrastructure.repository;

import es.urjc.code.ecommmerce.infrastructure.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {

}
