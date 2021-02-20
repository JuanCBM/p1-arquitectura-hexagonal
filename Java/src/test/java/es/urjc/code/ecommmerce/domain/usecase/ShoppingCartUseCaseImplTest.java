package es.urjc.code.ecommmerce.domain.usecase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import es.urjc.code.ecommmerce.domain.exceptions.ProductNotAvailableException;
import es.urjc.code.ecommmerce.domain.model.dto.FullShoppingCartDTO;
import es.urjc.code.ecommmerce.domain.model.dto.ShoppingCartDTO;
import es.urjc.code.ecommmerce.domain.model.dto.ShoppingCartProductDTO;
import es.urjc.code.ecommmerce.domain.repository.ShoppingCartRepository;
import es.urjc.code.ecommmerce.service.ShoppingCartValidationService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ShoppingCartUseCaseImplTest {

  @Mock
  ShoppingCartRepository shoppingCartRepository;

  @Mock
  ShoppingCartValidationService shoppingCartValidationService;

  @InjectMocks
  ShoppingCartUseCaseImpl shoppingCartUseCase;

  private static final String ownerName = "Juan Carlos";
  private static final long id = 1;

  @Test
  void givenShoppingCartWhenCreateShoppingCartThenOK() {
    ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
    shoppingCartDTO.setOwnerName(ownerName);

    when(this.shoppingCartRepository.save(Mockito.any()))
        .thenReturn(this.createFullShoppingCartDTO(0, ownerName));

    FullShoppingCartDTO fullShoppingCartDTO = this.shoppingCartUseCase
        .createShoppingCart(shoppingCartDTO);

    assertNotNull(fullShoppingCartDTO.getId());
    assertEquals(fullShoppingCartDTO.getOwnerName(), shoppingCartDTO.getOwnerName());
    assertThat(fullShoppingCartDTO.getProducts(), is(empty()));

  }

  @Test
  void givenIdWhenFindShoppingCartByIdThenOK() {
    long elements = 2;
    when(this.shoppingCartRepository.findShoppingCartById(Mockito.anyLong()))
        .thenReturn(Optional.of(this.createFullShoppingCartDTO(elements, ownerName)));

    Optional<FullShoppingCartDTO> fullShoppingCartDTO = this.shoppingCartRepository
        .findShoppingCartById(id);

    assertTrue(fullShoppingCartDTO.isPresent());
    assertEquals(fullShoppingCartDTO.get().getOwnerName(), ownerName);
    assertEquals(fullShoppingCartDTO.get().getProducts().size(), elements);
    assertEquals(fullShoppingCartDTO.get().getId(), id);
  }

  @Test
  void givenIdWhenDeleteShoppingCartByIdThenOK() {
    doNothing().when(this.shoppingCartRepository).deleteShoppingCartById(Mockito.anyLong());
    this.shoppingCartUseCase.deleteShoppingCartById(id);
    verify(this.shoppingCartRepository).deleteShoppingCartById(id);
  }

  @Test
  void givenProductShoppingCartAndQuantityWhenAddProductThenOK() {
    long quantity = 1;
    long idProduct = id;
    long idShoppingCart = id;

    when(this.shoppingCartRepository
        .addProduct(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyLong()))
        .thenReturn(this.createFullShoppingCartDTO(3, ownerName));

    FullShoppingCartDTO fullShoppingCartDTO = this.shoppingCartUseCase
        .addProduct(idShoppingCart, idProduct, quantity);

    assertTrue(fullShoppingCartDTO.getProducts().stream()
        .anyMatch(productDTO -> idProduct == productDTO.getId()));
  }

  @Test
  void givenProductAndShoppingCartWhenDeleteProductThenOK() {
    long idProduct = 3;
    long idShoppingCart = 3;

    when(this.shoppingCartRepository
        .deleteProduct(Mockito.anyLong(), Mockito.anyLong()))
        .thenReturn(Optional.of(this.createFullShoppingCartDTO(2, ownerName)));

    Optional<FullShoppingCartDTO> fullShoppingCartDTO = this.shoppingCartUseCase
        .deleteProduct(idShoppingCart, idProduct);

    assertFalse(fullShoppingCartDTO.get().getProducts().stream()
        .anyMatch(productDTO -> idProduct == productDTO.getId()));
  }

  @Test
  void givenNonExistingProductAndShoppingCartWhenDeleteProductThenOK() {
    long idProduct = 3;
    long idShoppingCart = 3;

    when(this.shoppingCartRepository
        .deleteProduct(Mockito.anyLong(), Mockito.anyLong()))
        .thenReturn(Optional.empty());

    Optional<FullShoppingCartDTO> fullShoppingCartDTO = this.shoppingCartUseCase
        .deleteProduct(idShoppingCart, idProduct);

    assertFalse(fullShoppingCartDTO.isPresent());

  }


  @Test
  void givenShoppingCartIdWhenEndShoppingCartThenOK() {
    long elements = 2;
    Optional<FullShoppingCartDTO> fullShoppingCartDTO = Optional
        .of(this.createFullShoppingCartDTO(elements, ownerName));
    fullShoppingCartDTO.get().setCompleted(Boolean.TRUE);

    when(this.shoppingCartValidationService.validateShoppingCart(Mockito.anyList()))
        .thenReturn(Boolean.TRUE);
    doNothing().when(this.shoppingCartRepository).endShoppingCart(Mockito.anyLong());
    when(this.shoppingCartRepository.findShoppingCartById(Mockito.anyLong()))
        .thenReturn(fullShoppingCartDTO);

    FullShoppingCartDTO fullShoppingCartDTOResult = this.shoppingCartUseCase.endShoppingCart(id);

    assertTrue(fullShoppingCartDTOResult.isCompleted());

  }

  @Test
  void givenShoppingCartIdWhenEndShoppingCartThenNotAvailableException() {
    long elements = 2;
    Optional<FullShoppingCartDTO> fullShoppingCartDTO = Optional
        .of(this.createFullShoppingCartDTO(elements, ownerName));
    fullShoppingCartDTO.get().setCompleted(Boolean.TRUE);

    when(this.shoppingCartRepository.findShoppingCartById(Mockito.anyLong()))
        .thenReturn(fullShoppingCartDTO);

    when(this.shoppingCartValidationService.validateShoppingCart(Mockito.anyList()))
        .thenReturn(Boolean.FALSE);

    Assertions.assertThrows(ProductNotAvailableException.class, () -> {
      this.shoppingCartUseCase.endShoppingCart(id);
    });
  }

  private FullShoppingCartDTO createFullShoppingCartDTO(long elements, String ownerName) {
    FullShoppingCartDTO fullShoppingCartDTO = new FullShoppingCartDTO();
    fullShoppingCartDTO.setOwnerName(ownerName);
    fullShoppingCartDTO.setCompleted(false);
    fullShoppingCartDTO.setId(id);
    fullShoppingCartDTO.setProducts(this.createShoppingCartProductsDTO(elements));

    return fullShoppingCartDTO;
  }

  private List<ShoppingCartProductDTO> createShoppingCartProductsDTO(long elements) {
    List<ShoppingCartProductDTO> shoppingCartProductDTOs = new ArrayList<>();
    for (int i = 0; i < elements; i++) {
      shoppingCartProductDTOs.add(this.createShoppingCartProductDTO(i));
    }
    return shoppingCartProductDTOs;
  }

  private ShoppingCartProductDTO createShoppingCartProductDTO(long id) {
    ShoppingCartProductDTO shoppingCartProductDTO = new ShoppingCartProductDTO();
    shoppingCartProductDTO.setId(id);
    shoppingCartProductDTO.setQuantity(id);

    return shoppingCartProductDTO;
  }

}