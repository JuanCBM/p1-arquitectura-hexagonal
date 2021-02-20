package es.urjc.code.ecommmerce.domain.usecase;

import es.urjc.code.ecommmerce.domain.model.dto.FullShoppingCartDTO;
import es.urjc.code.ecommmerce.domain.model.dto.ShoppingCartDTO;
import es.urjc.code.ecommmerce.domain.repository.ShoppingCartRepository;
import java.util.Optional;
import org.modelmapper.ModelMapper;

public class ShoppingCartUseCaseImpl implements ShoppingCartUseCase {

  private final ShoppingCartRepository shoppingCartRepository;
  private final ModelMapper modelMapper;

  public ShoppingCartUseCaseImpl(ShoppingCartRepository shoppingCartRepository) {
    this.shoppingCartRepository = shoppingCartRepository;
    this.modelMapper = new ModelMapper();
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
  public FullShoppingCartDTO endShoppingCart(long id) {
    Optional<FullShoppingCartDTO> fullShoppingCartDTO = this.shoppingCartRepository
        .findShoppingCartById(id);

    fullShoppingCartDTO.get().setCompleted(Boolean.TRUE);

    return this.shoppingCartRepository.save(fullShoppingCartDTO.get());
  }

  @Override
  public FullShoppingCartDTO addProduct(long idShoppingCart, long idProduct) {
    return this.shoppingCartRepository.addProduct(idShoppingCart, idProduct);
  }

  private FullShoppingCartDTO toFullShoppingCartDTO(ShoppingCartDTO shoppingCartDTO) {
    return this.modelMapper.map(shoppingCartDTO, FullShoppingCartDTO.class);
  }

}
