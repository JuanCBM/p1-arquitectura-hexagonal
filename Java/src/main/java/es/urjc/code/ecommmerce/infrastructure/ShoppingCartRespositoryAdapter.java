package es.urjc.code.ecommmerce.infrastructure;

import es.urjc.code.ecommmerce.domain.model.dto.FullShoppingCartDTO;
import es.urjc.code.ecommmerce.domain.repository.ShoppingCartRepository;
import es.urjc.code.ecommmerce.infrastructure.model.ShoppingCartEntity;
import es.urjc.code.ecommmerce.infrastructure.repository.ShoppingCartJpaRepository;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartRespositoryAdapter implements ShoppingCartRepository {

  private static final ModelMapper modelMapper = new ModelMapper();
  private final ShoppingCartJpaRepository productJpaRepository;

  public ShoppingCartRespositoryAdapter(final ShoppingCartJpaRepository productJpaRepository) {
    this.productJpaRepository = productJpaRepository;
  }

  @Override
  public FullShoppingCartDTO save(final FullShoppingCartDTO fullShoppingCartDTO) {
    ShoppingCartEntity productEntity = this.toShoppingCartEntity(fullShoppingCartDTO);
    return toFullShoppingCartDTO(this.productJpaRepository.save(productEntity));
  }

  @Override
  public Optional<FullShoppingCartDTO> findShoppingCartById(long id) {
    Optional<ShoppingCartEntity> productEntityOptional = this.productJpaRepository.findById(id);
    return productEntityOptional.map(ShoppingCartRespositoryAdapter::toFullShoppingCartDTO);
  }

  @Override
  public void deleteShoppingCartById(long id) {
    this.productJpaRepository.deleteById(id);
  }

  private ShoppingCartEntity toShoppingCartEntity(final FullShoppingCartDTO fullShoppingCartDTO) {
    return modelMapper.map(fullShoppingCartDTO, ShoppingCartEntity.class);
  }

  private static FullShoppingCartDTO toFullShoppingCartDTO(final ShoppingCartEntity productEntity) {
    return modelMapper.map(productEntity, FullShoppingCartDTO.class);
  }

}
