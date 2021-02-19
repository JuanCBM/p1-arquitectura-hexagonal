package es.urjc.code.ecommmerce.domain;

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

  private FullProductDTO toFullProductDTO(ProductDTO productDTO) {
    return this.modelMapper.map(productDTO, FullProductDTO.class);
  }

}
