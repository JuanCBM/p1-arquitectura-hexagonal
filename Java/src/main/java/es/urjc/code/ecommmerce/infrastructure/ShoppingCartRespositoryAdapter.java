package es.urjc.code.ecommmerce.infrastructure;

import es.urjc.code.ecommmerce.domain.model.dto.FullShoppingCartDTO;
import es.urjc.code.ecommmerce.domain.repository.ShoppingCartRepository;
import es.urjc.code.ecommmerce.infrastructure.model.ProductEntity;
import es.urjc.code.ecommmerce.infrastructure.model.ShoppingCartEntity;
import es.urjc.code.ecommmerce.infrastructure.model.ShoppingCartProductEntity;
import es.urjc.code.ecommmerce.infrastructure.repository.ProductJpaRepository;
import es.urjc.code.ecommmerce.infrastructure.repository.ShoppingCartJpaRepository;
import es.urjc.code.ecommmerce.infrastructure.repository.ShoppingCartProductJpaRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class ShoppingCartRespositoryAdapter implements ShoppingCartRepository {

  private static final ModelMapper modelMapper = new ModelMapper();
  private final ShoppingCartJpaRepository shoppingCartJpaRepository;
  private final ProductJpaRepository productJpaRepository;
  private final ShoppingCartProductJpaRepository shoppingCartProductJpaRepository;

  public ShoppingCartRespositoryAdapter(final ShoppingCartJpaRepository shoppingCartJpaRepository,
      ProductJpaRepository productJpaRepository,
      ShoppingCartProductJpaRepository shoppingCartProductJpaRepository) {
    this.shoppingCartJpaRepository = shoppingCartJpaRepository;
    this.productJpaRepository = productJpaRepository;
    this.shoppingCartProductJpaRepository = shoppingCartProductJpaRepository;
  }

  @Override
  public FullShoppingCartDTO save(final FullShoppingCartDTO shoppingCartDTO) {
    return toFullShoppingCartDTO(
        this.shoppingCartJpaRepository.save(this.toShoppingCartEntity(shoppingCartDTO)));
  }

  @Override
  public Optional<FullShoppingCartDTO> findShoppingCartById(long id) {
    return this.shoppingCartJpaRepository.findById(id)
        .map(ShoppingCartRespositoryAdapter::toFullShoppingCartDTO);
  }

  @Override
  public void deleteShoppingCartById(long id) {
    if (this.shoppingCartJpaRepository.findById(id).isPresent()) {
      this.shoppingCartJpaRepository.deleteById(id);
    }
  }

  @Override
  public FullShoppingCartDTO addProduct(long idShoppingCart, long idProduct, long quantity) {
    ShoppingCartEntity shoppingCart = this.shoppingCartJpaRepository.findById(idShoppingCart).get();
    ProductEntity productEntity = this.productJpaRepository.findById(idProduct).get();

    ShoppingCartProductEntity shoppingCartProductEntity = shoppingCart.getProducts().stream()
        .filter(shoppingCartProduct -> shoppingCartProduct.getProduct().getId().equals(idProduct))
        .findFirst().orElse(null);

    if (shoppingCartProductEntity != null) {
      shoppingCartProductEntity.setQuantity(shoppingCartProductEntity.getQuantity() + quantity);
    } else {
      shoppingCartProductEntity = new ShoppingCartProductEntity(shoppingCart, productEntity,
          quantity);
      shoppingCart.getProducts().add(shoppingCartProductEntity);
    }

    this.shoppingCartProductJpaRepository.save(shoppingCartProductEntity);

    return toFullShoppingCartDTO(shoppingCart);

  }

  @Override
  public Optional<FullShoppingCartDTO> deleteProduct(long idShoppingCart, long idProduct) {
    Optional<ShoppingCartEntity> shoppingCart = this.shoppingCartJpaRepository
        .findById(idShoppingCart);

    if (shoppingCart.isPresent()) {
      Optional<ShoppingCartProductEntity> shoppingCartProductEntity = shoppingCart.get()
          .getProducts()
          .stream()
          .filter(shoppingCartProduct -> shoppingCartProduct.getProduct().getId().equals(idProduct))
          .findFirst();

      if (shoppingCartProductEntity.isPresent()) {
        shoppingCart.get().getProducts().remove(shoppingCartProductEntity.get());
        this.shoppingCartProductJpaRepository.delete(shoppingCartProductEntity.get());
      }

    }

    return shoppingCart.map(ShoppingCartRespositoryAdapter::toFullShoppingCartDTO);
  }

  @Override
  public void endShoppingCart(long idShoppingCart) {
    this.shoppingCartJpaRepository.endShoppingCart(idShoppingCart);
  }

  private ShoppingCartEntity toShoppingCartEntity(final Object fullShoppingCartDTO) {
    return modelMapper.map(fullShoppingCartDTO, ShoppingCartEntity.class);
  }

  private static FullShoppingCartDTO toFullShoppingCartDTO(final ShoppingCartEntity productEntity) {
    return modelMapper.map(productEntity, FullShoppingCartDTO.class);
  }


}
