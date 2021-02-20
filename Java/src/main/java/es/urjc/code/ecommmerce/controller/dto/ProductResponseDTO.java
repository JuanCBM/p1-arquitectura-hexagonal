package es.urjc.code.ecommmerce.controller.dto;

import es.urjc.code.ecommmerce.domain.model.dto.FullProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {

  private static final ModelMapper modelMapper = new ModelMapper();

  Long id;
  String name;
  String description;

  public static ProductResponseDTO fromFullProductDTO(FullProductDTO fullProductDTO) {
    return modelMapper.map(fullProductDTO, ProductResponseDTO.class);
  }

}
