package es.urjc.code.ecommmerce.domain;

import java.util.Collection;
import java.util.Optional;
import org.modelmapper.ModelMapper;

public class ProductUseCaseImpl implements ProductUseCase {

  private final ProductRepository productRepository;
  private final ModelMapper modelMapper;

  public ProductUseCaseImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
    this.modelMapper = new ModelMapper();
  }

  @Override
  public FullProductDTO createProduct(ProductDTO productDTO) {
    FullProductDTO fullProductDTO = this.toFullProductDTO(productDTO);
    return this.productRepository.save(fullProductDTO);
  }

  @Override
  public Optional<FullProductDTO> findProductById(long id) {
    return this.productRepository.findProductById(id);
  }

  @Override
  public Collection<FullProductDTO> findAllProducts() {
    return this.productRepository.findAllProducts();
  }

  @Override
  public void deleteProductById(long id) {
    this.productRepository.deleteProductById(id);
  }

  private FullProductDTO toFullProductDTO(ProductDTO productDTO) {
    return this.modelMapper.map(productDTO, FullProductDTO.class);
  }

}
