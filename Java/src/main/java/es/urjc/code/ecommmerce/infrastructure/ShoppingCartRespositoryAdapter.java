package es.urjc.code.ecommmerce.infrastructure;

import es.urjc.code.ecommmerce.domain.model.dto.FullShoppingCartDTO;
import es.urjc.code.ecommmerce.domain.repository.ShoppingCartRepository;
import es.urjc.code.ecommmerce.infrastructure.model.ProductEntity;
import es.urjc.code.ecommmerce.infrastructure.model.ShoppingCartEntity;
import es.urjc.code.ecommmerce.infrastructure.repository.ProductJpaRepository;
import es.urjc.code.ecommmerce.infrastructure.repository.ShoppingCartJpaRepository;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartRespositoryAdapter implements ShoppingCartRepository {

  private static final ModelMapper modelMapper = new ModelMapper();
  private final ShoppingCartJpaRepository shoppingCartJpaRepository;
  private final ProductJpaRepository productJpaRepository;

  public ShoppingCartRespositoryAdapter(final ShoppingCartJpaRepository shoppingCartJpaRepository,
      ProductJpaRepository productJpaRepository) {
    this.shoppingCartJpaRepository = shoppingCartJpaRepository;
    this.productJpaRepository = productJpaRepository;
  }

  @Override
  public FullShoppingCartDTO save(final FullShoppingCartDTO fullShoppingCartDTO) {
    ShoppingCartEntity productEntity = this.toShoppingCartEntity(fullShoppingCartDTO);
    return toFullShoppingCartDTO(this.shoppingCartJpaRepository.save(productEntity));
  }

  @Override
  public Optional<FullShoppingCartDTO> findShoppingCartById(long id) {
    return this.shoppingCartJpaRepository.findById(id)
        .map(ShoppingCartRespositoryAdapter::toFullShoppingCartDTO);
  }

  @Override
  public void deleteShoppingCartById(long id) {
    this.shoppingCartJpaRepository.deleteById(id);
  }

  @Override
  public FullShoppingCartDTO addProduct(long idShoppingCart, long idProduct) {
    ShoppingCartEntity shoppingCart = this.shoppingCartJpaRepository.findById(idShoppingCart).get();
    ProductEntity product = this.productJpaRepository.findById(idProduct).get();
    shoppingCart.getProducts().add(product);

    return toFullShoppingCartDTO(this.shoppingCartJpaRepository.save(shoppingCart));

  }

  private ShoppingCartEntity toShoppingCartEntity(final FullShoppingCartDTO fullShoppingCartDTO) {
    return modelMapper.map(fullShoppingCartDTO, ShoppingCartEntity.class);
  }

  private static FullShoppingCartDTO toFullShoppingCartDTO(final ShoppingCartEntity productEntity) {
    return modelMapper.map(productEntity, FullShoppingCartDTO.class);
  }

}
