package es.urjc.code.ecommmerce.domain.usecase;

import es.urjc.code.ecommmerce.domain.model.dto.FullShoppingCartDTO;
import es.urjc.code.ecommmerce.domain.model.dto.ShoppingCartDTO;
import java.util.Optional;

public interface ShoppingCartUseCase {

  FullShoppingCartDTO createShoppingCart(ShoppingCartDTO toShoppingCartDTO);

  Optional<FullShoppingCartDTO> findShoppingCartById(long id);

  void deleteShoppingCartById(long id);

  FullShoppingCartDTO updateShoppingCart(ShoppingCartDTO toShoppingCartDTO);
}
