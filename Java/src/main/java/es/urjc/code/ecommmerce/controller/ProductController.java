package es.urjc.code.ecommmerce.controller;

import static es.urjc.code.ecommmerce.controller.dto.ProductResponseDTO.fromFullProductDto;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import es.urjc.code.ecommmerce.controller.dto.ProductRequestDTO;
import es.urjc.code.ecommmerce.controller.dto.ProductResponseDTO;
import es.urjc.code.ecommmerce.domain.FullProductDTO;
import es.urjc.code.ecommmerce.service.ProductService;
import java.net.URI;
import java.util.Collection;
import java.util.NoSuchElementException;
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
      @RequestBody ProductRequestDTO productRequestDto) {

    FullProductDTO fullProductDTO = this.productService.save(productRequestDto);

    ProductResponseDTO productResponseDto = fromFullProductDto(fullProductDTO);

    URI location = fromCurrentRequest().path("/{id}").buildAndExpand(productResponseDto.getId())
        .toUri();

    return ResponseEntity.created(location).body(productResponseDto);
  }

  @GetMapping({"/", ""})
  public Collection<ProductResponseDTO> getProducts() {
    return this.productService
        .findAll()
        .stream()
        .map(ProductResponseDTO::fromFullProductDto)
        .collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public ProductResponseDTO getProduct(@PathVariable long id) {
    return this.productService.findById(id).map(ProductResponseDTO::fromFullProductDto)
        .orElseThrow();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
    this.productService.findById(id).orElseThrow(NoSuchElementException::new);
    this.productService.deleteById(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);

  }

}
