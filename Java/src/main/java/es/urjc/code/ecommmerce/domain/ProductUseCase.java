package es.urjc.code.ecommmerce.domain;

import java.util.Collection;
import java.util.Optional;

public interface ProductUseCase {

  FullProductDTO createProduct(ProductDTO productDTO);

  Optional<FullProductDTO> findProductById(long id);

  Collection<FullProductDTO> findAllProducts();

  void deleteProductById(long id);
}
