package es.urjc.code.ecommmerce.controller.dto;

import es.urjc.code.ecommmerce.domain.model.dto.FullShoppingCartDTO;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartResponseDTO {

  private static final ModelMapper modelMapper = new ModelMapper();

  Long id;
  boolean completed;
  String ownerName;
  List<ProductResponseDTO> products = new ArrayList<>();

  public static ShoppingCartResponseDTO fromFullShoppingCartDTO(
      FullShoppingCartDTO fullShoppingCartDTO) {
    return modelMapper.map(fullShoppingCartDTO, ShoppingCartResponseDTO.class);
  }
}
