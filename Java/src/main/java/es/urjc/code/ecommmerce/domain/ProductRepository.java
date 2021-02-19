package es.urjc.code.ecommmerce.domain;

import java.util.Collection;
import java.util.Optional;

public interface ProductRepository {

  FullProductDTO save(FullProductDTO fullProductDTO);

  Optional<FullProductDTO> findProductById(long id);

  Collection<FullProductDTO> findAllProducts();

  void deleteProductById(long id);
}
