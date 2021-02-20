package es.urjc.code.ecommmerce.service;

import es.urjc.code.ecommmerce.controller.dto.ShoppingCartRequestDTO;
import es.urjc.code.ecommmerce.controller.dto.ShoppingCartUpdateRequestDTO;
import es.urjc.code.ecommmerce.domain.model.dto.FullShoppingCartDTO;
import es.urjc.code.ecommmerce.domain.model.dto.ShoppingCartDTO;
import es.urjc.code.ecommmerce.domain.usecase.ShoppingCartUseCase;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {

  private final ShoppingCartUseCase shoppingCartUseCase;
  private final ModelMapper modelMapper;

  public ShoppingCartService(ShoppingCartUseCase shoppingCartUseCase) {
    this.shoppingCartUseCase = shoppingCartUseCase;
    this.modelMapper = new ModelMapper();
  }

  public FullShoppingCartDTO save(ShoppingCartRequestDTO shoppingCartRequestDTO) {
    return this.shoppingCartUseCase
        .createShoppingCart(this.toShoppingCartDTO(shoppingCartRequestDTO));
  }

  public Optional<FullShoppingCartDTO> findById(long id) {
    return this.shoppingCartUseCase.findShoppingCartById(id);
  }

  public void deleteById(long id) {
    this.shoppingCartUseCase.deleteShoppingCartById(id);
  }

  public FullShoppingCartDTO update(
      ShoppingCartUpdateRequestDTO shoppingCartCreateRequestDTO) {
    return this.shoppingCartUseCase
        .updateShoppingCart(this.toShoppingCartDTO(shoppingCartCreateRequestDTO));
  }

  private ShoppingCartDTO toShoppingCartDTO(Object shoppingCartRequestDTO) {
    return this.modelMapper.map(shoppingCartRequestDTO, ShoppingCartDTO.class);
  }
}
