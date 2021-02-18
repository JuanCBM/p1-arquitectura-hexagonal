package es.urjc.code.ecommmerce.controller;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import es.urjc.code.ecommmerce.controller.dto.ProductRequestDTO;
import es.urjc.code.ecommmerce.controller.dto.ProductResponseDTO;
import es.urjc.code.ecommmerce.domain.FullProductDTO;
import es.urjc.code.ecommmerce.service.ProductService;
import java.net.URI;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;
  private final ModelMapper modelMapper;

  public ProductController(ProductService productService) {
    this.productService = productService;
    this.modelMapper = new ModelMapper();
  }

  @PostMapping("/")
  public ResponseEntity<ProductResponseDTO> createBook(
      @RequestBody ProductRequestDTO productRequestDto) {

    FullProductDTO fullProductDTO = this.productService.save(productRequestDto);

    ProductResponseDTO productResponseDto = this.toProductResponseDTO(fullProductDTO);

    URI location = fromCurrentRequest().path("/{id}").buildAndExpand(productResponseDto.getId())
        .toUri();

    return ResponseEntity.created(location).body(productResponseDto);
  }

  private ProductResponseDTO toProductResponseDTO(FullProductDTO fullProductDTO) {
    return this.modelMapper.map(fullProductDTO, ProductResponseDTO.class);
  }

}
