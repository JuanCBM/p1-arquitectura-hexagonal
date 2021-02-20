package es.urjc.code.ecommmerce.controller;

import static es.urjc.code.ecommmerce.controller.dto.ShoppingCartResponseDTO.fromFullShoppingCartDTO;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import es.urjc.code.ecommmerce.controller.dto.ShoppingCartRequestDTO;
import es.urjc.code.ecommmerce.controller.dto.ShoppingCartResponseDTO;
import es.urjc.code.ecommmerce.service.ShoppingCartService;
import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shoppingcarts")
public class ShoppingCartController {

  private final ShoppingCartService shoppingCartService;

  public ShoppingCartController(ShoppingCartService shoppingCartService) {
    this.shoppingCartService = shoppingCartService;
  }

  @PostMapping({"/", ""})
  public ResponseEntity<ShoppingCartResponseDTO> createShoppingCart(
      @RequestBody ShoppingCartRequestDTO shoppingCartRequestDTO) {

    ShoppingCartResponseDTO shoppingCartResponseDTO = fromFullShoppingCartDTO(
        this.shoppingCartService.save(shoppingCartRequestDTO));

    URI location = fromCurrentRequest().path("/{id}")
        .buildAndExpand(shoppingCartResponseDTO.getId())
        .toUri();

    return ResponseEntity.created(location).body(shoppingCartResponseDTO);

  }

  @GetMapping("/{id}")
  public ResponseEntity<ShoppingCartResponseDTO> getShoppingCart(@PathVariable long id) {
    return ResponseEntity.ok().body(this.shoppingCartService.findById(id)
        .map(ShoppingCartResponseDTO::fromFullShoppingCartDTO)
        .orElseThrow());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteShoppingCart(@PathVariable long id) {
    this.shoppingCartService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ShoppingCartResponseDTO> updateShoppingCart(@PathVariable long id) {
    return ResponseEntity.ok()
        .body(fromFullShoppingCartDTO(this.shoppingCartService.endShoppingCart(id)));
  }

  @PostMapping("/{idShoppingCart}/product/{idProduct}/quantity/{quantity}")
  public ResponseEntity<ShoppingCartResponseDTO> addProductToShoppingCart(
      @PathVariable long idShoppingCart,
      @PathVariable long idProduct,
      @PathVariable long quantity) {
    return ResponseEntity.ok().body(
        fromFullShoppingCartDTO(
            this.shoppingCartService.addProduct(idShoppingCart, idProduct, quantity)));
  }

  @DeleteMapping("/{idShoppingCart}/product/{idProduct}")
  public ResponseEntity<ShoppingCartResponseDTO> deleteProductFromShoppingCart(
      @PathVariable long idShoppingCart,
      @PathVariable long idProduct) {
    return ResponseEntity.ok().body(
        fromFullShoppingCartDTO(
            this.shoppingCartService.deleteProduct(idShoppingCart, idProduct).get()));
  }

}
