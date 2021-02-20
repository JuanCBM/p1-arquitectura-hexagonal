package es.urjc.code.ecommmerce.domain.usecase;

import es.urjc.code.ecommmerce.domain.exceptions.ProductNotAvailableException;
import es.urjc.code.ecommmerce.domain.model.dto.FullShoppingCartDTO;
import es.urjc.code.ecommmerce.domain.model.dto.ShoppingCartDTO;
import es.urjc.code.ecommmerce.domain.repository.ShoppingCartRepository;
import es.urjc.code.ecommmerce.service.ShoppingCartValidationService;
import java.util.Optional;
import org.modelmapper.ModelMapper;

public class ShoppingCartUseCaseImpl implements ShoppingCartUseCase {

  private final ShoppingCartRepository shoppingCartRepository;
  private final ModelMapper modelMapper;
  private final ShoppingCartValidationService shoppingCartValidationService;

  public ShoppingCartUseCaseImpl(ShoppingCartRepository shoppingCartRepository,
      ShoppingCartValidationService shoppingCartValidationService) {
    this.shoppingCartRepository = shoppingCartRepository;
    this.modelMapper = new ModelMapper();
    this.shoppingCartValidationService = shoppingCartValidationService;
  }

  @Override
  public FullShoppingCartDTO createShoppingCart(ShoppingCartDTO shoppingCartDTO) {
    FullShoppingCartDTO fullShoppingCartDTO = this.toFullShoppingCartDTO(shoppingCartDTO);
    return this.shoppingCartRepository.save(fullShoppingCartDTO);
  }

  @Override
  public Optional<FullShoppingCartDTO> findShoppingCartById(long id) {
    return this.shoppingCartRepository.findShoppingCartById(id);
  }

  @Override
  public void deleteShoppingCartById(long id) {
    this.shoppingCartRepository.deleteShoppingCartById(id);
  }

  @Override
  public FullShoppingCartDTO endShoppingCart(long id) throws ProductNotAvailableException {
    Optional<FullShoppingCartDTO> fullShoppingCartDTO = this.shoppingCartRepository
        .findShoppingCartById(id);

    if (this.shoppingCartValidationService
        .validateShoppingCart(fullShoppingCartDTO.get().getProducts())) {
      fullShoppingCartDTO.get().setCompleted(Boolean.TRUE);
      this.shoppingCartRepository.endShoppingCart(id);
    } else {
      throw new ProductNotAvailableException();
    }

    return fullShoppingCartDTO.get();
  }

  @Override
  public FullShoppingCartDTO addProduct(long idShoppingCart, long idProduct, long quantity) {
    return this.shoppingCartRepository.addProduct(idShoppingCart, idProduct, quantity);
  }

  @Override
  public Optional<FullShoppingCartDTO> deleteProduct(long idShoppingCart, long idProduct) {
    return this.shoppingCartRepository.deleteProduct(idShoppingCart, idProduct);
  }

  private FullShoppingCartDTO toFullShoppingCartDTO(ShoppingCartDTO shoppingCartDTO) {
    return this.modelMapper.map(shoppingCartDTO, FullShoppingCartDTO.class);
  }

}
