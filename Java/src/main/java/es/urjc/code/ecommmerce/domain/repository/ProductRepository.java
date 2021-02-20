package es.urjc.code.ecommmerce.domain.repository;

import es.urjc.code.ecommmerce.domain.model.dto.FullProductDTO;
import java.util.Collection;
import java.util.Optional;

public interface ProductRepository {

  FullProductDTO save(FullProductDTO fullProductDTO);

  Optional<FullProductDTO> findProductById(long id);

  Collection<FullProductDTO> findAllProducts();

  void deleteProductById(long id);
}
