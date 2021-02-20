package es.urjc.code.ecommmerce.infrastructure;

import es.urjc.code.ecommmerce.domain.model.dto.FullProductDTO;
import es.urjc.code.ecommmerce.domain.repository.ProductRepository;
import es.urjc.code.ecommmerce.infrastructure.model.ProductEntity;
import es.urjc.code.ecommmerce.infrastructure.repository.ProductJpaRepository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class ProductRepositoryAdapter implements ProductRepository {

  private static final ModelMapper modelMapper = new ModelMapper();
  private final ProductJpaRepository productJpaRepository;

  public ProductRepositoryAdapter(final ProductJpaRepository productJpaRepository) {
    this.productJpaRepository = productJpaRepository;
  }

  @Override
  public FullProductDTO save(final FullProductDTO fullProductDTO) {
    return toFullProductDTO(this.productJpaRepository.save(this.toProductEntity(fullProductDTO)));
  }

  @Override
  public Optional<FullProductDTO> findProductById(long id) {
    return this.productJpaRepository.findById(id).map(ProductRepositoryAdapter::toFullProductDTO);
  }

  @Override
  public Collection<FullProductDTO> findAllProducts() {
    List<ProductEntity> productEntities = this.productJpaRepository.findAll();

    return productEntities
        .stream()
        .map(ProductRepositoryAdapter::toFullProductDTO)
        .collect(Collectors.toList());
  }

  @Override
  public void deleteProductById(long id) {
    if (this.productJpaRepository.findById(id).isPresent()) {
      this.productJpaRepository.deleteById(id);
    }
  }

  private ProductEntity toProductEntity(final FullProductDTO fullProductDTO) {
    return modelMapper.map(fullProductDTO, ProductEntity.class);
  }

  private static FullProductDTO toFullProductDTO(final ProductEntity productEntity) {
    return modelMapper.map(productEntity, FullProductDTO.class);
  }

}
