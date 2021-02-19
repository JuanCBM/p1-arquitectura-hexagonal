package es.urjc.code.ecommmerce.infrastructure;

import es.urjc.code.ecommmerce.domain.FullProductDTO;
import es.urjc.code.ecommmerce.domain.ProductRepository;
import es.urjc.code.ecommmerce.infrastructure.model.ProductEntity;
import es.urjc.code.ecommmerce.infrastructure.repository.ProductJpaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductRepositoryAdapter implements ProductRepository {

  private final ModelMapper modelMapper;
  private final ProductJpaRepository productJpaRepository;

  public ProductRepositoryAdapter(final ProductJpaRepository productJpaRepository) {
    this.productJpaRepository = productJpaRepository;
    this.modelMapper = new ModelMapper();
  }

  @Override
  public FullProductDTO save(final FullProductDTO fullProductDTO) {
    ProductEntity productEntity = this.toProductEntity(fullProductDTO);
    return this.toFullProductDTO(this.productJpaRepository.save(productEntity));
  }

  private ProductEntity toProductEntity(final FullProductDTO fullProductDTO) {
    return this.modelMapper.map(fullProductDTO, ProductEntity.class);
  }

  private FullProductDTO toFullProductDTO(final ProductEntity productEntity) {
    return this.modelMapper.map(productEntity, FullProductDTO.class);
  }
}
