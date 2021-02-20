package es.urjc.code.ecommmerce.controller;

import static es.urjc.code.ecommmerce.controller.dto.ProductResponseDTO.fromFullProductDTO;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import es.urjc.code.ecommmerce.controller.dto.ProductRequestDTO;
import es.urjc.code.ecommmerce.controller.dto.ProductResponseDTO;
import es.urjc.code.ecommmerce.controller.exception.ProductNotFoundException;
import es.urjc.code.ecommmerce.service.ProductService;
import java.net.URI;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping({"/", ""})
  public ResponseEntity<ProductResponseDTO> createProduct(
      @RequestBody ProductRequestDTO productRequestDTO) {

    ProductResponseDTO productResponseDto = fromFullProductDTO(
        this.productService.save(productRequestDTO));

    URI location = fromCurrentRequest().path("/{id}").buildAndExpand(productResponseDto.getId())
        .toUri();

    return ResponseEntity.created(location).body(productResponseDto);
  }

  @GetMapping({"/", ""})
  public ResponseEntity<Collection<ProductResponseDTO>> getProducts() {
    return ResponseEntity.ok().body(this.productService
        .findAll()
        .stream()
        .map(ProductResponseDTO::fromFullProductDTO)
        .collect(Collectors.toList()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable long id) {
    return ResponseEntity.ok()
        .body(this.productService.findById(id).map(ProductResponseDTO::fromFullProductDTO)
            .orElseThrow(ProductNotFoundException::new));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
    this.productService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
