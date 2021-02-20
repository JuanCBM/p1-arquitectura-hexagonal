package es.urjc.code.ecommmerce.service;

import es.urjc.code.ecommmerce.controller.dto.ShoppingCartRequestDTO;
import es.urjc.code.ecommmerce.controller.exception.ProductNotFoundException;
import es.urjc.code.ecommmerce.domain.exceptions.ProductNotAvailableException;
import es.urjc.code.ecommmerce.domain.model.dto.FullShoppingCartDTO;
import es.urjc.code.ecommmerce.domain.model.dto.ShoppingCartDTO;
import es.urjc.code.ecommmerce.domain.usecase.ShoppingCartUseCase;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {

  private final ShoppingCartUseCase shoppingCartUseCase;
  private final ProductService productService;
  private final ModelMapper modelMapper;

  public ShoppingCartService(ShoppingCartUseCase shoppingCartUseCase,
      ProductService productService) {
    this.shoppingCartUseCase = shoppingCartUseCase;
    this.productService = productService;
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
    this.findById(id).orElseThrow(NoSuchElementException::new);
    FullShoppingCartDTO fullShoppingCartDTO;
    try {
      fullShoppingCartDTO = this.shoppingCartUseCase.endShoppingCart(id);
    } catch (ProductNotAvailableException e) {
      throw new ProductNotFoundException();
    }
    return fullShoppingCartDTO;
  }

  private ShoppingCartDTO toShoppingCartDTO(Object shoppingCartRequestDTO) {
    return this.modelMapper.map(shoppingCartRequestDTO, ShoppingCartDTO.class);
  }

  public FullShoppingCartDTO addProduct(long idShoppingCart, long idProduct, long quantity) {
    this.findById(idShoppingCart).orElseThrow(NoSuchElementException::new);
    this.productService.findById(idProduct).orElseThrow(NoSuchElementException::new);
    return this.shoppingCartUseCase.addProduct(idShoppingCart, idProduct, quantity);

  }

  public Optional<FullShoppingCartDTO> deleteProduct(long idShoppingCart, long idProduct) {
    this.findById(idShoppingCart).orElseThrow(NoSuchElementException::new);
    this.productService.findById(idProduct).orElseThrow(NoSuchElementException::new);
    return this.shoppingCartUseCase.deleteProduct(idShoppingCart, idProduct);
  }
}
