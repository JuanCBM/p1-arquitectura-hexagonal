package es.urjc.code.ecommmerce.domain.usecase;

import es.urjc.code.ecommmerce.domain.model.dto.FullProductDTO;
import es.urjc.code.ecommmerce.domain.model.dto.ProductDTO;
import java.util.Collection;
import java.util.Optional;

public interface ProductUseCase {

  FullProductDTO createProduct(ProductDTO productDTO);

  Optional<FullProductDTO> findProductById(long id);

  Collection<FullProductDTO> findAllProducts();

  void deleteProductById(long id);
}
