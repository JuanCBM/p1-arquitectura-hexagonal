package es.urjc.code.ecommmerce.domain.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import es.urjc.code.ecommmerce.domain.model.dto.FullProductDTO;
import es.urjc.code.ecommmerce.domain.model.dto.ProductDTO;
import es.urjc.code.ecommmerce.domain.repository.ProductRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductUseCaseImplTest {

  @Mock
  ProductRepository productRepository;

  @InjectMocks
  ProductUseCaseImpl productUseCase;

  private static final String description = "Monitor 27 pulgadas LG";
  private static final String name = "Monitor ";
  private static final long id = 1;

  @Test
  void givenNoProductsWhenFindAllProductsThenOK() {
    when(this.productRepository.findAllProducts()).thenReturn(Collections.EMPTY_LIST);
    Collection<FullProductDTO> productsDTO = this.productUseCase.findAllProducts();
    assertEquals(productsDTO.size(), 0);
  }

  @Test
  void givenProductsWhenFindAllProductsThenOK() {
    int elements = 4;

    when(this.productRepository.findAllProducts()).thenReturn(this.createFullProductsDTO(elements));
    Collection<FullProductDTO> productsDTO = this.productUseCase.findAllProducts();
    assertEquals(productsDTO.size(), elements);
  }

  @Test
  void givenProductDTOWhenCreateProductThenOK() {
    ProductDTO productDTO = new ProductDTO();
    productDTO.setDescription(description);
    productDTO.setName(name);

    when(this.productRepository.save(Mockito.any())).thenReturn(this.createFullProductDTO(1));
    FullProductDTO createdProduct = this.productUseCase.createProduct(productDTO);

    assertNotNull(createdProduct.getId());
    assertEquals(createdProduct.getDescription(), productDTO.getDescription());
    assertEquals(createdProduct.getName(), productDTO.getName());

  }

  @Test
  void givenIdOfExistingProductWhenFindByIdThenOK() {
    when(this.productRepository.findProductById(Mockito.anyLong()))
        .thenReturn(Optional.of(this.createFullProductDTO(1)));

    Optional<FullProductDTO> fullProductDTO = this.productUseCase.findProductById(id);

    assertTrue(fullProductDTO.isPresent());
    assertEquals(fullProductDTO.get().getDescription(), fullProductDTO.get().getDescription());
    assertEquals(fullProductDTO.get().getName(), fullProductDTO.get().getName());
    assertEquals(fullProductDTO.get().getId(), id);
  }

  @Test
  void givenIdOfNonExistingProductWhenFindByIdThenOK() {
    when(this.productRepository.findProductById(Mockito.anyLong()))
        .thenReturn(Optional.empty());

    Optional<FullProductDTO> fullProductDTO = this.productUseCase.findProductById(id);

    assertFalse(fullProductDTO.isPresent());
  }

  @Test
  void deleteProductById() {
    doNothing().when(this.productRepository).deleteProductById(Mockito.anyLong());
    this.productUseCase.deleteProductById(id);
    verify(this.productRepository).deleteProductById(id);
  }

  private FullProductDTO createFullProductDTO(long id) {
    FullProductDTO fullProductDTO = new FullProductDTO();
    fullProductDTO.setName(name);
    fullProductDTO.setDescription(description);
    fullProductDTO.setId(id);

    return fullProductDTO;
  }

  private List<FullProductDTO> createFullProductsDTO(long elements) {
    List<FullProductDTO> fullProductDTOs = new ArrayList<>();
    for (int i = 0; i < elements; i++) {
      fullProductDTOs.add(this.createFullProductDTO(i));
    }
    return fullProductDTOs;
  }

}