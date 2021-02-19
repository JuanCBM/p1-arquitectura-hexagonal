package es.urjc.code.ecommmerce.service;

import es.urjc.code.ecommmerce.controller.dto.ProductRequestDTO;
import es.urjc.code.ecommmerce.domain.FullProductDTO;
import es.urjc.code.ecommmerce.domain.ProductDTO;
import es.urjc.code.ecommmerce.domain.ProductUseCase;
import java.util.Collection;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  private final ProductUseCase productUseCase;
  private final ModelMapper modelMapper;

  public ProductService(ProductUseCase productUseCase) {
    this.productUseCase = productUseCase;
    this.modelMapper = new ModelMapper();
  }

  public FullProductDTO save(ProductRequestDTO productRequestDto) {
    ProductDTO productDTO = this.toProductDTO(productRequestDto);
    return this.productUseCase.createProduct(productDTO);
  }

  public Collection<FullProductDTO> findAll() {
    return this.productUseCase.findAllProducts();
  }

  public Optional<FullProductDTO> findById(long id) {
    return this.productUseCase.findProductById(id);
  }

  private ProductDTO toProductDTO(ProductRequestDTO productRequestDto) {
    return this.modelMapper.map(productRequestDto, ProductDTO.class);
  }

  public void deleteById(long id) {
    this.productUseCase.deleteProductById(id);
  }
}
