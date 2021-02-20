package es.urjc.code.ecommmerce.service;

import es.urjc.code.ecommmerce.controller.dto.ShoppingCartRequestDTO;
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

  public FullShoppingCartDTO endShoppingCart(long id) {
    return this.shoppingCartUseCase.endShoppingCart(id);
  }

  public FullShoppingCartDTO addProduct(long idShoppingCart, long idProduct, long quantity) {
    return this.shoppingCartUseCase.addProduct(idShoppingCart, idProduct, quantity);
  }

  public Optional<FullShoppingCartDTO> deleteProductById(long idShoppingCart, long idProduct) {
    return this.shoppingCartUseCase.deleteProduct(idShoppingCart, idProduct);
  }

  private ShoppingCartDTO toShoppingCartDTO(ShoppingCartRequestDTO shoppingCartRequestDTO) {
    return this.modelMapper.map(shoppingCartRequestDTO, ShoppingCartDTO.class);
  }
}
