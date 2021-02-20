package es.urjc.code.ecommmerce.domain.repository;

import es.urjc.code.ecommmerce.domain.model.dto.FullShoppingCartDTO;
import java.util.Optional;

public interface ShoppingCartRepository {

  FullShoppingCartDTO save(FullShoppingCartDTO fullShoppingCartDTO);

  Optional<FullShoppingCartDTO> findShoppingCartById(long id);

  void deleteShoppingCartById(long id);

  FullShoppingCartDTO addProduct(long idShoppingCart, long idProduct, long quantity);

  Optional<FullShoppingCartDTO> deleteProduct(long idShoppingCart, long idProduct);
}
